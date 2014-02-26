package AvailableProduce;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AvailableProduce {

    static Connection connection;

    public static ResultSet loadAvailableProduce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        
        ResultSet apLoad = null;
        
        java.sql.Connection connection;
        Statement statement;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");
            
            // Get record
            int i = 0;
            query = "SELECT * FROM availableproduce where fid = '" + request.getParameter("fid") + "'";
            statement = connection.createStatement();
            apLoad = statement.executeQuery(query);

        } catch (Exception npe) {

        }

        return apLoad;    
    }

    public static void deleteAvailableProduce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        Statement statement = null;
        String query = "";
        String uName = "";

        java.sql.Connection connection;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int added = 0;
            query = "Delete From availableproduce where apid = '" + request.getParameter("apid") + "'";
            Statement st = connection.createStatement();
            added = st.executeUpdate(query);

        } catch (Exception sqle) {
            writer.println(sqle);
        }
    }

    public static String getFName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("fname");
    }

    public static String getFID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("fid");
    }

    public static String getHAProduceID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("apid");
    }

    public static ResultSet getAPID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet gAPID = null;

        java.sql.Connection connection;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "Select * From availableproduce where apid = '" + request.getParameter("apid") + "'";
            Statement st = connection.createStatement();
            gAPID = st.executeQuery(query);

        } catch (Exception sqle) {
            writer.println(sqle);
        }

        return gAPID;
    }
    
    public static String getUserView1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("reguserview1");
    }

    public static String getUserView2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("reguserview2");
    }
    
    public static String getUserView3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("reguserview3");
    }

    public static String getGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("group1");
    }

    public static String getPlanting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("getplanting");
    }

    public static String getPlanting1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("getplanting1");
    }

    public static ResultSet getSoil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet gSoil = null;
        
        java.sql.Connection connection;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "SELECT sid, description FROM soils order by description";
            Statement statement = connection.createStatement();
            gSoil = statement.executeQuery(query);
        } catch (Exception sqle) {
            writer.println(sqle);
        }
        
        return gSoil;
    }

    public static void saveAvailableProduce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        java.sql.Connection connection;
        Statement statement;
        String query = "";

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        String ip = request.getRemoteAddr();

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");
            
        if (AvailableProduce.getUserView3(request, response) != null) {

            try {
        
                int added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set AvailableFrom = '" +  request.getParameter("afrom") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set AvailableTo = '" +  request.getParameter("ato") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set ProduceType = '" +  request.getParameter("ptype") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set Description = '" +  request.getParameter("desc") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();

                added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set Quantity = '" +  request.getParameter("quantity") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                
                
                added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set Variety = '" +  request.getParameter("variety") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                
                
                added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set Packaging = '" +  request.getParameter("packaging") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                                

                added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set CropGrowthArea = '" +  request.getParameter("aplanted") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                                                
                
                added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set Unit = '" +  request.getParameter("unit") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                                                                

                added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set UnitPrice = '" +  request.getParameter("uprice") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                                                                

                added = 0;
                statement = connection.createStatement();
                query = "Update availableproduce Set Quality = '" +  request.getParameter("quality") + "'Where apid='" + request.getParameter("apid") + "'";
                added = statement.executeUpdate(query);
                statement.close();

            } catch (Exception e) {
                writer.println(e);    
            }

        } else {
            String pid = "";
            String pdesc = "";
                String pdesc1 = "";                
              
            try {                

                request.getParameter("pID").equals("");

                int c1 = 0;
                
                pdesc1 = request.getParameter("desc");
                
            for (int x=0;x<pdesc1.length();x++) {
                
                if (pdesc1.charAt(x) == ',') {
                     
                    pdesc = pdesc = pdesc1;
                    
                    c1 = 1;
                    
                }
                
                if (c1 == 1) {
                    
                    pid = pid + pdesc1;
                    
                }

            }

             try {

                 // Insert Record
                int added = 0;
                statement = connection.createStatement();                        
                query = "INSERT INTO availableproduce(fid, pid, AvailableFrom, AvailableTo, ProduceType, Description, Quantity, Variety, Packaging, CropGrowthArea, Unit, UnitPrice) VALUES ('" + request.getParameter("fid") + "','" + request.getParameter("pID") + "','" + request.getParameter("afrom") + "','" + request.getParameter("ato") + "','" + request.getParameter("ptype") + "','" + request.getParameter("desc") + "','" + request.getParameter("quantity") + "','" + request.getParameter("variety") + "','" + request.getParameter("packaging") + "','" + request.getParameter("aplanted") + "','" + request.getParameter("uquant") + "','" + request.getParameter("uprice") + "')";
                added = statement.executeUpdate(query);                                     
            
                statement.close();

            } catch (Exception sqle) {
                writer.println("ERROR: " + sqle);   
            }                
                
            } catch (NullPointerException npe) {
                
                int c1 = 0;
                
                pdesc1 = request.getParameter("desc") ;

            for (int x=0;x<pdesc1.length();x++) {
                
                if ((c1 == 0) & (pdesc1.charAt(x) != ',')) {
                     
                    pdesc = pdesc + pdesc1.charAt(x);
                    
                }
                
                if ((c1 == 1) & (pdesc1.charAt(x) != ',')) {
                    
                    pid = pid + pdesc1.charAt(x);
                    
                }
                
                if ((c1 == 0) & (pdesc1.charAt(x) == ',')) {
                    
                    c1 = 1;
                    
                }
            }

             try {
                    // Insert Record
                    int added = 0;
                    statement = connection.createStatement();                        
                    query = "INSERT INTO availableproduce(fid, pid, AvailableFrom, AvailableTo, ProduceType, Description, Quantity, Variety, Packaging, CropGrowthArea, Unit, UnitPrice, quality) VALUES ('" + request.getParameter("fid") + "','" + request.getParameter("pid") + "','" + request.getParameter("afrom") + "','" + request.getParameter("ato") + "','" + request.getParameter("ptype") + "','" + pdesc + "','" + request.getParameter("quantity") + "','" + request.getParameter("variety") + "','" + request.getParameter("packaging") + "','" + request.getParameter("aplanted") + "','" + request.getParameter("unit") + "','" + request.getParameter("uprice") + "','" + request.getParameter("quality") + "')";
                    added = statement.executeUpdate(query);                                     

                    statement.close();

                } catch (Exception sqle) {
                      writer.println("ERROR: " + sqle);   
                }
            }
        
        }
        
        } catch (SQLException sqle) {
            writer.println("1: " + sqle);
        } catch (Exception e) {
            writer.println("2: " + e);        
        }


    }

    public static ResultSet getHarvesting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResultSet gPH = null;
        String none = "null";

        try {

            java.sql.Connection connection;
            Statement statement;
            String query = "";

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "SELECT * FROM harvesting where fid = '" + request.getParameter("fid") + "'";
            statement = connection.createStatement();
            gPH = statement.executeQuery(query);

        } catch (Exception e) {}

        return gPH;
    }
    
    public static ResultSet getHarvestingByIDs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        java.sql.Connection connection;
        Statement statement;
        String query = "";
        
        ResultSet gHBID = null;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            String none = "null";

            int i = 0;
            query = "SELECT * FROM harvesting where fid = '" + request.getParameter("fid") + "' and hid = '" + request.getParameter("pid") + "'";
            statement = connection.createStatement();
            gHBID = statement.executeQuery(query);

        } catch (Exception e) {
        }

        return gHBID;
    }
    
    public static ResultSet getHID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ResultSet hid = null;

        try {

            java.sql.Connection connection;
            Statement statement;
            String query = "";

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "SELECT * FROM harvesting where hid = '" + request.getParameter("pid") + "'";
            statement = connection.createStatement();
             hid = statement.executeQuery(query);

        } catch (Exception e) {
        }
        
        return hid;
    }

}