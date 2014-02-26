/*
 * MM JDBC Drivers for MySQL
 *
 * $Id: Statement.java,v 1.3 2002/04/21 03:03:47 mark_matthews Exp $
 *
 * Copyright (C) 1998 Mark Matthews <mmatthew@worldserver.com>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 * 
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 *
 * See the COPYING file located in the top-level-directory of
 * the archive of this library for complete text of license.
 *
 * Some portions:
 *
 * Copyright (c) 1996 Bradley McLean / Jeffrey Medeiros
 * Modifications Copyright (c) 1996/1997 Martin Rode
 * Copyright (c) 1997 Peter T Mount
 */

/**
 * A Statement object is used for executing a static SQL statement and
 * obtaining the results produced by it.
 *
 * <p>Only one ResultSet per Statement can be open at any point in time.
 * Therefore, if the reading of one ResultSet is interleaved with the
 * reading of another, each must have been generated by different
 * Statements.  All statement execute methods implicitly close a
 * statement's current ResultSet if an open one exists.
 *
 * @see java.sql.Statement
 * @see ResultSet
 * @author Mark Matthews <mmatthew@worldserver.com>
 * @version $Id: Statement.java,v 1.3 2002/04/21 03:03:47 mark_matthews Exp $
 */

package org.gjt.mm.mysql;

import java.sql.*;

import java.util.Vector;

public class Statement {
	
	/**
	 * The connection who created us
	 */
	
	protected Connection 	_conn 					= null; 
	protected int 		_resultSetType 			= 0;
	protected int 		_resultSetConcurrency 	= 0;
	
	/**
	 * Holds batched commands
	 */

	protected Vector 		_batchedArgs;
	
	/**
	 * The current results
	 */
	
	ResultSet 				_results 			= null; 
	
	/**
	 * The next result set
	 */
	
	ResultSet 				_nextResults 		= null; 
	
	/**
	 * The warnings chain.
	 */
	
	SQLWarning 				_warnings 			= null; 
	
	/**
	 * The timeout for a query
	 */
	
	int 					_timeout 			= 0; 
	
	/**
	 * Should we process escape codes?
	 */
	
	boolean 				_escapeProcessing 	= true; 
	
	/**
	 * Processes JDBC escape codes
	 */
	
	EscapeProcessor 		_escaper 			= null; 

	int 					_maxFieldSize 		= MysqlIO.MAXBUF;
	int 					_maxRows 			= -1;

	long 					_updateCount 		= -1;
	long 					_lastInsertId 		= -1;

	String 					_catalog 			= null;

	/**
	 * Constructor for a Statement.  It simply sets the connection
	 * that created us.
	 *
	 * @param c the Connection instantation that creates us
	 */

	public Statement(Connection c, String catalog) {
		if (Driver.trace) {
			Object[] Args = { c };
			Debug.methodCall(this, "constructor", Args);
		}

		_conn = c;
		_escaper = new EscapeProcessor();
		_catalog = catalog;

		//
		// Adjust, if we know it
		//

		if (_conn != null) {
			_maxFieldSize = _conn.getMaxAllowedPacket();
		}
	}

	/**
	 * Execute a SQL statement that retruns a single ResultSet
	 *
	 * @param Sql typically a static SQL SELECT statement
	 * @return a ResulSet that contains the data produced by the query
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  java.sql.ResultSet executeQuery(String sql)
		throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = { sql };
			Debug.methodCall(this, "executeQuery", Args);
		}

		if (_escapeProcessing) {
			sql = _escaper.escapeSQL(sql);
		}

		
		if (_results != null) {
			_results.close();
		}

		// If there isn't a limit clause in the SQL
		// then limit the number of rows to return in 
		// an efficient manner. Only do this if
		// setMaxRows() hasn't been used on any Statements
		// generated from the current Connection (saves
		// a query, and network traffic).

		 synchronized (_conn.getMutex()) {
			String oldCatalog = null;

			if (!_conn.getCatalog().equals(_catalog)) {
				oldCatalog = _conn.getCatalog();
				_conn.setCatalog(_catalog);
			}

			if (_conn.useMaxRows()) {

				// We need to execute this all together
				// So synchronize on the Connection's mutex (because 
				// even queries going through there synchronize
				// on the connection

				if (sql.toUpperCase().indexOf("LIMIT") != -1) {
					_results = _conn.execSQL(sql, _maxRows);
				}
				else {
					if (_maxRows <= 0) {
						_conn.execSQL("SET OPTION SQL_SELECT_LIMIT=DEFAULT", -1);
					}
					else {
						_conn.execSQL("SET OPTION SQL_SELECT_LIMIT=" + _maxRows, -1);
					}

					_results = _conn.execSQL(sql, -1);

					if (oldCatalog != null) {
						_conn.setCatalog(oldCatalog);
					}
				}
			}
			else {
				_results = _conn.execSQL(sql, -1);
			}

			if (oldCatalog != null) {
				_conn.setCatalog(oldCatalog);
			}
		}

		_lastInsertId = _results.getUpdateID();
		_nextResults = _results;

		_results.setConnection(_conn);
		_results.setResultSetType(_resultSetType);
		_results.setResultSetConcurrency(_resultSetConcurrency);
		_results.setStatement(this);

		return (java.sql.ResultSet) _results;
	}

	/**
	 * Execute a SQL INSERT, UPDATE or DELETE statement.  In addition
	 * SQL statements that return nothing such as SQL DDL statements
	 * can be executed
	 *
	 * Any IDs generated for AUTO_INCREMENT fields can be retrieved
	 * by casting this Statement to org.gjt.mm.mysql.Statement and
	 * calling the getLastInsertID() method.
	 *
	 * @param Sql a SQL statement
	 * @return either a row count, or 0 for SQL commands
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  int executeUpdate(String sql) throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = { sql };
			Debug.methodCall(this, "executeUpdate", Args);
		}

		if (_escapeProcessing) {
			sql = _escaper.escapeSQL(sql);
		}

		

		// The checking and changing of catalogs
		// must happen in sequence, so synchronize
		// on the same mutex that _conn is using

		ResultSet rs = null;

		synchronized (_conn.getMutex()) {
			String oldCatalog = null;

			if (!_conn.getCatalog().equals(_catalog)) {
				oldCatalog = _conn.getCatalog();
				_conn.setCatalog(_catalog);
			}

			// 
			// Only apply max_rows to selects
			//

			if (_conn.useMaxRows()) {
				_conn.execSQL("SET OPTION SQL_SELECT_LIMIT=DEFAULT", -1);
			}

			rs = _conn.execSQL(sql, -1);

			rs.setConnection(_conn);

			if (oldCatalog != null) {
				_conn.setCatalog(oldCatalog);
			}
		}

		if (rs.reallyResult()) {
			throw new java.sql.SQLException("Results returned for UPDATE ONLY.", "01S03");
		}
		else {
			_updateCount = rs.getUpdateCount();

			int truncated_updateCount = 0;

			if (_updateCount > Integer.MAX_VALUE) {
				truncated_updateCount = Integer.MAX_VALUE;
			}
			else {
				truncated_updateCount = (int) _updateCount;
			}

			_lastInsertId = rs.getUpdateID();

			return truncated_updateCount;
		}
	}

	/**
	 * In many cases, it is desirable to immediately release a
	 * Statement's database and JDBC resources instead of waiting
	 * for this to happen when it is automatically closed.  The
	 * close method provides this immediate release.
	 *
	 * <p><B>Note:</B> A Statement is automatically closed when it is
	 * garbage collected.  When a Statement is closed, its current
	 * ResultSet, if one exists, is also closed.
	 *
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  void close() throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "close", Args);
		}

		if (_results != null) {
			try
			{
				_results.close();
			}
			catch (Exception ex) {
			}
		}
		
		_results = null;
		_conn = null;
		_warnings = null;
		_escaper = null;
	}

	/**
	 * The maxFieldSize limit (in bytes) is the maximum amount of
	 * data returned for any column value; it only applies to
	 * BINARY, VARBINARY, LONGVARBINARY, CHAR, VARCHAR and LONGVARCHAR
	 * columns.  If the limit is exceeded, the excess data is silently
	 * discarded.
	 *
	 * @return the current max column size limit; zero means unlimited
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  int getMaxFieldSize() throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "getMaxFieldSize", Args);
		}

		return _maxFieldSize; // Init. set to MAXBUFFER in MysqlIO
	}

	/**
	 * Sets the maxFieldSize
	 *
	 * @param max the new max column size limit; zero means unlimited
	 * @exception java.sql.SQLException if size exceeds buffer size
	 */

	public  void setMaxFieldSize(int max) throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = { new Integer(max)};
			Debug.methodCall(this, "setMaxFieldSize", Args);
		}

		int max_buf = (_conn != null) ? _conn.getMaxAllowedPacket() : MysqlIO.MAXBUF;

		if (max > max_buf) {
			throw new java.sql.SQLException(
				"Can not set max field size > max allowed packet: " + max_buf,
				"S1009");
		}
		else {
			_maxFieldSize = max;
		}
	}

	/**
	 * The maxRows limit is set to limit the number of rows that
	 * any ResultSet can contain.  If the limit is exceeded, the
	 * excess rows are silently dropped.
	 *
	 * @return the current maximum row limit; zero means unlimited
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  int getMaxRows() throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "getMaxRows", Args);
		}

		if (_maxRows <= 0) {
			return 0;
		}
		else {
			return _maxRows;
		}
	}

	/**
	 * Set the maximum number of rows
	 *
	 * @param max the new max rows limit; zero means unlimited
	 * @exception java.sql.SQLException if a database access error occurs
	 * @see getMaxRows
	 */

	public  void setMaxRows(int max) throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = { new Integer(max)};
			Debug.methodCall(this, "setMaxRows", Args);
		}

		if (max > MysqlDefs.MAX_ROWS) {
			throw new java.sql.SQLException(
				"setMaxRows() out of range. " + max + " > " + MysqlDefs.MAX_ROWS + ".",
				"S1009");
		}

		if (max == 0) {
			max = -1;
		}

		_maxRows = max;

		// Most people don't use setMaxRows()
		// so don't penalize them
		// with the extra query it takes
		// to do it efficiently unless we need
		// to.

		_conn.maxRowsChanged();
	}

	/**
	 * If escape scanning is on (the default), the driver will do escape
	 * substitution before sending the SQL to the database.
	 *
	 * @param enable true to enable; false to disable
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  void setEscapeProcessing(boolean enable) throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = { new Boolean(enable)};
			Debug.methodCall(this, "setEscapeProcessing", Args);
		}

		_escapeProcessing = enable;
	}

	/**
	 * The queryTimeout limit is the number of seconds the driver
	 * will wait for a Statement to execute.  If the limit is
	 * exceeded, a java.sql.SQLException is thrown.
	 *
	 * @return the current query timeout limit in seconds; 0 = unlimited
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  int getQueryTimeout() throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "getQueryTimeout", Args);
		}

		return _timeout;
	}

	/**
	 * Sets the queryTimeout limit
	 *
	 * @param seconds - the new query timeout limit in seconds
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  void setQueryTimeout(int seconds) throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = { new Integer(seconds)};
			Debug.methodCall(this, "setQueryTimeout", Args);
		}

		_timeout = seconds;
	}

	/**
	 * Cancel can be used by one thread to cancel a statement that
	 * is being executed by another thread.  However this driver
	 * is synchronous, so this really has no meaning - we
	 * define it as a no-op (i.e. you can't cancel, but there is no
	 * error if you try.)
	 *
	 * @exception java.sql.SQLException only because thats the spec.
	 */

	public  void cancel() throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "cancel", Args);
		}

		// No-op
	}

	/**
	 * The first warning reported by calls on this Statement is
	 * returned.  A Statement's execute methods clear its java.sql.SQLWarning
	 * chain.  Subsequent Statement warnings will be chained to this
	 * java.sql.SQLWarning.
	 *
	 * <p>The Warning chain is automatically cleared each time a statement
	 * is (re)executed.
	 *
	 * <p><B>Note:</B>  If you are processing a ResultSet then any warnings
	 * associated with ResultSet reads will be chained on the ResultSet
	 * object.
	 *
	 * @return the first java.sql.SQLWarning on null
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  java.sql.SQLWarning getWarnings() throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "getWarnings", Args);
		}

		return _warnings;
	}

	/**
	 * After this call, getWarnings returns null until a new warning
	 * is reported for this Statement.
	 *
	 * @exception java.sql.SQLException if a database access error occurs (why?)
	 */

	public  void clearWarnings() throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "clearWarnings", Args);
		}

		_warnings = null;
	}

	/**
	 * setCursorName defines the SQL cursor name that will be used by
	 * subsequent execute methods.  This name can then be used in SQL
	 * positioned update/delete statements to identify the current row
	 * in the ResultSet generated by this statement.  If a database
	 * doesn't support positioned update/delete, this method is a
	 * no-op.
	 *
	 * <p><b>Note:</b> This MySQL driver does not support cursors.
	 *
	 *
	 * @param name the new cursor name
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  void setCursorName(String name) throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = { name };
			Debug.methodCall(this, "setCursorName", Args);
		}

		// No-op
	}

	/**
	 * Execute a SQL statement that may return multiple results. We
	 * don't have to worry about this since we do not support multiple
	 * ResultSets.   You can use getResultSet or getUpdateCount to
	 * retrieve the result.
	 *
	 * @param sql any SQL statement
	 * @return true if the next result is a ResulSet, false if it is
	 *      an update count or there are no more results
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  boolean execute(String sql) throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = { sql };
			Debug.methodCall(this, "execute", Args);
		}

		if (_escapeProcessing) {
			sql = _escaper.escapeSQL(sql);
		}


		if (_results != null) {
			_results.close();
		}

		ResultSet rs = null;

		// If there isn't a limit clause in the SQL
		// then limit the number of rows to return in 
		// an efficient manner. Only do this if
		// setMaxRows() hasn't been used on any Statements
		// generated from the current Connection (saves
		// a query, and network traffic).

		 synchronized (_conn.getMutex()) {
			String oldCatalog = null;

			if (!_conn.getCatalog().equals(_catalog)) {
				oldCatalog = _conn.getCatalog();
				_conn.setCatalog(_catalog);
			}

			// 
			// Only apply max_rows to selects
			//

			if (_conn.useMaxRows()) {

				char firstChar = Character.toUpperCase(sql.charAt(0));

				if (firstChar == 'S') {
					if (sql.toUpperCase().indexOf("LIMIT") != -1) {
						rs = _conn.execSQL(sql, _maxRows);
					}
					else {
						if (_maxRows <= 0) {
							_conn.execSQL("SET OPTION SQL_SELECT_LIMIT=DEFAULT", -1);
						}
						else {
							_conn.execSQL("SET OPTION SQL_SELECT_LIMIT=" + _maxRows, -1);
						}

					}
				}
				else {
					_conn.execSQL("SET OPTION SQL_SELECT_LIMIT=DEFAULT", -1);
				}

				// Finally, execute the query
				rs = _conn.execSQL(sql, -1);
			}
			else {
				rs = _conn.execSQL(sql, -1);
			}

			if (oldCatalog != null) {
				_conn.setCatalog(oldCatalog);
			}
		}

		_lastInsertId = rs.getUpdateID();

		if (rs != null) {
			_results = rs;

		}

		rs.setConnection(_conn);
		rs.setResultSetType(_resultSetType);
		rs.setResultSetConcurrency(_resultSetConcurrency);

		return (rs != null && rs.reallyResult());
	}

	/**
	 * getResultSet returns the current result as a ResultSet.  It
	 * should only be called once per result.
	 *
	 * @return the current result set; null if there are no more
	 * @exception java.sql.SQLException if a database access error occurs (why?)
	 */

	public  java.sql.ResultSet getResultSet() throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "getResultSet", Args);
		}

		return (_results != null && _results.reallyResult())
			? (java.sql.ResultSet) _results
			: null;
	}

	/**
	 * getUpdateCount returns the current result as an update count,
	 * if the result is a ResultSet or there are no more results, -1
	 * is returned.  It should only be called once per result.
	 *
	 * @return the current result as an update count.
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  int getUpdateCount() throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "getUpdateCount", Args);
		}

		if (_results == null) {
			return -1;
		}
		if (_results.reallyResult()) {
			return -1;
		}

		int truncated_updateCount = 0;

		if (_results.getUpdateCount() > Integer.MAX_VALUE) {
			truncated_updateCount = Integer.MAX_VALUE;
		}
		else {
			truncated_updateCount = (int) _results.getUpdateCount();
		}

		return truncated_updateCount;
	}

	/**
	 * getLongUpdateCount returns the current result as an update count,
	 * if the result is a ResultSet or there are no more results, -1
	 * is returned.  It should only be called once per result.
	 *
	 * <p>
	 * This method returns longs as MySQL server versions newer than 
	 * 3.22.4 return 64-bit values for update counts
	 *
	 * @return the current result as an update count.
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  long getLongUpdateCount() {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "getLongUpdateCount", Args);
		}

		if (_results == null) {
			return -1;
		}

		if (_results.reallyResult()) {
			return -1;
		}

		return _updateCount;
	}

	/**
	 * getLastInsertID returns the value of the auto_incremented key
	 * after an executeQuery() or excute() call.
	 *
	 * <p>
	 * This gets around the un-threadsafe behavior of
	 * "select LAST_INSERT_ID()" which is tied to the Connection
	 * that created this Statement, and therefore could have had
	 * many INSERTS performed before one gets a chance to call
	 * "select LAST_INSERT_ID()".
	 *
	 * @return the last update ID.
	 */

	public  long getLastInsertID() {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "getLastInsertID", Args);
		}

		return _lastInsertId;
	}

	/**
	 * getMoreResults moves to a Statement's next result.  If it returns
	 * true, this result is a ResulSet.
	 *
	 * @return true if the next ResultSet is valid
	 * @exception java.sql.SQLException if a database access error occurs
	 */

	public  boolean getMoreResults() throws java.sql.SQLException {
		if (Driver.trace) {
			Object[] Args = new Object[0];
			Debug.methodCall(this, "getMoreResults", Args);
		}
		if (_results != null) {
			_results.close();
		}

		_results = _nextResults;
		_nextResults = null;

		return (_results != null && _results.reallyResult()) ? true : false;
	}

	//--------------------------JDBC 2.0-----------------------------

	/**
	 * JDBC 2.0
	 *
	 * Give a hint as to the direction in which the rows in a result set
	 * will be processed. The hint applies only to result sets created 
	 * using this Statement object.  The default value is 
	 * ResultSet.FETCH_FORWARD.
	 *
	 * @param direction the initial direction for processing rows
	 * @exception SQLException if a database-access error occurs or direction
	 * is not one of ResultSet.FETCH_FORWARD, ResultSet.FETCH_REVERSE, or
	 * ResultSet.FETCH_UNKNOWN
	 */

	public  void setFetchDirection(int direction) throws SQLException {

	}

	/**
	 * JDBC 2.0
	 *
	 * Determine the fetch direction.
	 *
	 * @return the default fetch direction
	 * @exception SQLException if a database-access error occurs
	 */

	public  int getFetchDirection() throws SQLException {
		return 0;
	}

	/**
	 * JDBC 2.0
	 *
	 * Give the JDBC driver a hint as to the number of rows that should 
	 * be fetched from the database when more rows are needed.  The number 
	 * of rows specified only affects result sets created using this 
	 * statement. If the value specified is zero, then the hint is ignored.
	 * The default value is zero.
	 *
	 * @param rows the number of rows to fetch
	 * @exception SQLException if a database-access error occurs, or the
	 * condition 0 <= rows <= this.getMaxRows() is not satisfied.
	 */

	public  void setFetchSize(int rows) throws SQLException {
	}

	/**
	 * JDBC 2.0
	 *
	 * Determine the default fetch size.
	 */

	public  int getFetchSize() throws SQLException {
		return 0;
	}

	/**
	 * JDBC 2.0
	 *
	 * Determine the result set concurrency.
	 */

	public  int getResultSetConcurrency() throws SQLException {

		return _resultSetConcurrency;
	}

	/**
	 * JDBC 2.0
	 *
	 * Determine the result set type.
	 */

	public int getResultSetType() throws SQLException {
		return _resultSetType;
	}

	public  void addBatch(String sql) throws SQLException {

		if (_batchedArgs == null) {

			_batchedArgs = new Vector();

		}

		if (sql != null) {
			_batchedArgs.addElement(sql);
		}

	}

	/**
	 * JDBC 2.0
	 *
	 * Make the set of commands in the current batch empty.
	 * This method is optional.
	 *
	 * @exception SQLException if a database-access error occurs, or the
	 * driver does not support batch statements
	 */

	public  void clearBatch() throws SQLException {
		if (_batchedArgs != null) {
			_batchedArgs.setSize(0);
		}
	}

	/**
	 * JDBC 2.0
	 * 
	 * Return the Connection that produced the Statement.
	 */

	public java.sql.Connection getConnection() throws SQLException {
		return (java.sql.Connection) _conn;
	}
}
