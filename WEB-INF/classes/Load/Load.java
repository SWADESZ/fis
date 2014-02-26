package Load;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Load {

    public static String loadIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection;
        Statement statement;
        String query = "";
        String uName = "";
        boolean proceed = false;

        try {
            PrintWriter writer = response.getWriter();
            response.setContentType("text/html");
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");
                try {
                    // Check login details
                    int i = 0;
                    query = "SELECT * FROM login where username = '" + request.getParameter("uname") + "' and password = '" + request.getParameter("pword") + "'";
                    statement = connection.createStatement();
                    ResultSet cfs = statement.executeQuery(query);
                    
                    while (cfs.next()) {
                        uName = cfs.getString(1);
                    }

                    if (uName.equals(request.getParameter("uname"))) {
                        uName = request.getParameter("uname");
                        writer.println("<div align=right><b>LOGGED IN: " + uName + "</b></div>");
                        proceed = true;                        
                    } else {
                        writer.println("<div align=right><b>NOT LOGGED IN</b></div>");
                    }

                } catch (Exception sqle) {
                    writer.println("<div align=right>" + sqle + "</div>");
                }

                connection.close();

            } catch (SQLException sqle) {
                writer.println("<div align=right>" + sqle + "</div>");
            } catch (ClassNotFoundException cnfe) {
                writer.println("<div align=right>" + cnfe + "</div>");
            } catch (Exception e) {
                writer.println("<div align=right>" + e + "</div>");
            }

        } catch (NullPointerException npe) {

        } catch (Exception e) {

        }

        return uName;

    }

    public static String getUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uName = "";

        uName = request.getParameter("uname");

        return uName;

    }

}
