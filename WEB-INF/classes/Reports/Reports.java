package Reports;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Reports {

    static Connection connection;

    public static ResultSet loadMarketCrops(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet hLoad = null;

        java.sql.Connection connection;
        Statement statement;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;

            if (request.getParameter("type").equals("Crops")) {
                query = "SELECT * FROM marketcrops order by availableto desc";
            } else if (request.getParameter("type").equals("Chicks")) {
                query = "SELECT * FROM marketchicks order by availableto desc";
            } else if (request.getParameter("type").equals("Eggs")) {
                query = "SELECT * FROM marketeggs order by availableto desc";
            } else if (request.getParameter("type").equals("Livestock")) {
                query = "SELECT * FROM marketlivestock order by availableto desc";
            } else if (request.getParameter("type").equals("Weaners")) {
                query = "SELECT * FROM marketweaners order by availableto desc";
            } else if (request.getParameter("type").equals("Milk")) {
                query = "SELECT * FROM marketmilk order by availableto desc";
            }

            statement = connection.createStatement();
            hLoad = statement.executeQuery(query);

        } catch (Exception npe) {

        }

        return hLoad;
    }

    public static String getType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("type");
    }
    
    public static String getGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("group1");
    }

    public static String getFID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("fid");
    }
    
    public static String getPID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("pid");
    }

    public static String getFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("func");
    }

    public static ResultSet getProduce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet hLoad = null;

        java.sql.Connection connection;
        Statement statement;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "SELECT pid, Description FROM produce order by description asc";
            statement = connection.createStatement();
            hLoad = statement.executeQuery(query);

        } catch (Exception npe) {

        }

        return hLoad;
    }

    public static ResultSet getAllProduce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet hLoad = null;

        java.sql.Connection connection;
        Statement statement;
        String query = "";
        
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "SELECT * FROM produce where pid = '" + request.getParameter("type") + "'";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

        } catch (Exception e) {

        }

        return rs;

    }

    public static ResultSet getInputSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet hLoad = null;

        java.sql.Connection connection;
        Statement statement;
        String query = "";
        
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "SELECT * FROM inputsupplier order by name";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

        } catch (Exception e) {

        }

        return rs;

    }
    
    public static ResultSet getInputSupplierID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet hLoad = null;

        java.sql.Connection connection;
        Statement statement;
        String query = "";
        
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "SELECT * FROM inputsupplier where isid = '" + request.getParameter("pid") + "'";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

        } catch (Exception e) {

        }

        return rs;

    }

    public static void deleteInputSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        java.sql.Connection connection;
        Statement statement;
        String query = "";
        
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "DELETE FROM inputsupplier where isid = '" + request.getParameter("pid") + "'";
            statement = connection.createStatement();
            i = statement.executeUpdate(query);

        } catch (Exception e) {

        }

    }
    
    public static void saveInputSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        java.sql.Connection connection;
        Statement statement;
        String query = "";
        
        ResultSet rs = null;
        
        try {

            //Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            Class.forName("com.mysql.jdbc.Driver").newInstance();

           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            //if (request.getParameter("func").equals("edit")) {
            try {    

                request.getParameter("func").equals("edit");

                int added = 0;
                statement = connection.createStatement();
                query = "Update inputsupplier Set Name = '" +  request.getParameter("name") + "'Where isid='" + request.getParameter("isid") + "'";
                added = statement.executeUpdate(query);
                statement.close();            
                
                added = 0;
                statement = connection.createStatement();
                query = "Update inputsupplier Set Telephone = '" +  request.getParameter("tel") + "'Where isid='" + request.getParameter("isid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update inputsupplier Set Cellphone = '" +  request.getParameter("cell") + "'Where isid='" + request.getParameter("isid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                
                
                added = 0;
                statement = connection.createStatement();
                query = "Update inputsupplier Set Fax = '" +  request.getParameter("fax") + "'Where isid='" + request.getParameter("isid") + "'";
                added = statement.executeUpdate(query);
                statement.close();         
                
                added = 0;
                statement = connection.createStatement();
                query = "Update inputsupplier Set Email = '" +  request.getParameter("email") + "'Where isid='" + request.getParameter("isid") + "'";
                added = statement.executeUpdate(query);
                statement.close();

            } catch (NullPointerException npe) {

             try {
                    // Insert Record
                    int added = 0;
                    statement = connection.createStatement();                        
                    query = "INSERT INTO inputsupplier(Name, Telephone, Cellphone, Fax, Email) VALUES ('" + request.getParameter("name") + "','" + request.getParameter("tel") + "','" + request.getParameter("cell") + "','" + request.getParameter("fax") + "','" + request.getParameter("email") + "')";
                    added = statement.executeUpdate(query);                                     

                    statement.close();

                } catch (Exception sqle) {
                    writer.println("ERROR: " + sqle);   
                }

            }

        } catch (SQLException sqle) {
            writer.println(sqle);
        } catch (Exception e) {
            writer.println("EER " + e);        
        }

        
        
        
        
    }

}