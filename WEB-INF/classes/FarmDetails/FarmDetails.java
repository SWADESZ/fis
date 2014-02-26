package FarmDetails;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FarmDetails {
    
    static Connection connection;

    public static ResultSet loadFarm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Statement statement;
        String query = "";
        String uName = "";
        
        ResultSet fLoad = null;

        try {

            PrintWriter writer = response.getWriter();
            response.setContentType("text/html");

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            try {

                int i = 0;
                query = "SELECT * FROM farms where fid = '" + request.getParameter("fname") + "'";
                statement = connection.createStatement();
                fLoad = statement.executeQuery(query);
                boolean proceed = false;

            } catch (SQLException sqle) {
                writer.println("<td align=right>" + sqle + "</td>");
            } catch (Exception e) {
                writer.println("<td align=right>" + e + "</td></p>");
            }

        } catch (Exception npe) {

        }

        return fLoad;    
    }

    public static Boolean getUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Statement statement;
        String query = "";
        String uName = "";

        ResultSet fLoad = null;
        boolean proceed = false;

        try {

            PrintWriter writer = response.getWriter();
            response.setContentType("text/html");

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            try {

                int i = 0;
                query = "SELECT * FROM login where username = '" + request.getParameter("uname") + "'";
                statement = connection.createStatement();
                fLoad = statement.executeQuery(query);

                while(fLoad.next()) {
                    proceed = true;
                }

            } catch (SQLException sqle) {
                writer.println("<td align=right>" + sqle + "</td>");
            } catch (Exception e) {
                writer.println("<td align=right>" + e + "</td></p>");
            }

        } catch (Exception npe) {

        }

        return proceed;    
    }

    public static ResultSet loadFarmMaps(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Statement statement;
        String query = "";
        String uName = "";
        
        ResultSet fLoad = null;

        try {

            PrintWriter writer = response.getWriter();
            response.setContentType("text/html");

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            try {

                int i = 0;
                query = "SELECT * FROM farmmaps where fid = '" + request.getParameter("fid") + "'";
                statement = connection.createStatement();
                fLoad = statement.executeQuery(query);
                boolean proceed = false;

            } catch (SQLException sqle) {
                writer.println("<td align=right>" + sqle + "</td>");
            } catch (Exception e) {
                writer.println("<td align=right>" + e + "</td></p>");
            }

        } catch (Exception npe) {

        }

        return fLoad;    
    }

    public static void deleteFarm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Statement statement;
        String query = "";
        String uName = "";

        try {

            int i = 0;
            query = "DELETE FROM farms where fid = '" + request.getParameter("fname") + "'";
            statement = connection.createStatement();
            i = statement.executeUpdate(query);

        } catch(Exception e) {}
        
    }

    public static String getGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("group1");
    }

    public static String getFID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("fid");
    }

    public static String getFName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("fname");
    }

    public static String getUName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("uname");
    }
    
    public static void saveFarm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        java.sql.Connection connection;
        Statement statement;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

                if (request.getParameter("func").equals("save")) {

                    int added = 0;
                    statement = connection.createStatement();
                    query = "INSERT INTO farms(Name, MainCrop, MainCropArea, SecondaryCrop, SecondaryCropArea, AvailableArea, IrrigableArea, NettArea, Contact, Telephone, PostalAddress, PhysicalAddress) VALUES ('" + request.getParameter("name") + "','" + 
                    request.getParameter("mcrop") + "','" + request.getParameter("mcroparea") + "','" + request.getParameter("scrop") + "','" + request.getParameter("scroparea") + "','" +  request.getParameter("areaavailable") + "','" + request.getParameter("irrarea") + 
                    "','" + request.getParameter("nettarea") + "','" + request.getParameter("contacts") + "','" + request.getParameter("telephone") + "','" + request.getParameter("postaladdress") + "','" + request.getParameter("physicaladdress") + "')";
                    added = statement.executeUpdate(query);
                    statement.close();

                } else if (request.getParameter("func").equals("edit")) {

                    int added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set Name = '" + request.getParameter("name") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();
                    
                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set MainCrop = '" + request.getParameter("mcrop") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();
                    
                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set MainCropArea = '" + request.getParameter("mcroparea") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();
                    
                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set SecondaryCrop = '" + request.getParameter("scrop") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();

                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set SecondaryCropArea = '" + request.getParameter("scroparea") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();
                    
                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set AvailableArea = '" + request.getParameter("areaavailable") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();
                    
                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set IrrigableArea = '" + request.getParameter("irrarea") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();

                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set NettArea = '" + request.getParameter("nettarea") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();
                    
                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set Contact = '" + request.getParameter("contacts") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();
                    
                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set Telephone = '" + request.getParameter("telephone") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();

                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set PostalAddress = '" + request.getParameter("postaladdress") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();

                    added = 0;
                    statement = connection.createStatement();
                    query = "Update farms Set PhysicalAddress = '" + request.getParameter("physicaladdress") + "' Where fid='" + request.getParameter("fid") + "'";
                    added = statement.executeUpdate(query);
                    statement.close();

                }

                connection.close();

        } catch (SQLException sqle) {
            writer.println(sqle);
        } catch (Exception e) {
            writer.println(e);        
        }

    }

}