package Weeding;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Weeding {

    static Connection connection;

    public static ResultSet loadWeeding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        
        ResultSet pLoad = null;
        
        java.sql.Connection connection;
        Statement statement;
        String query = "";
    
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "SELECT * FROM weeding where fid = '" + request.getParameter("fid") + "'";
            statement = connection.createStatement();
            pLoad = statement.executeQuery(query);

        } catch (Exception npe) {

        }

        return pLoad;    
    }

    public static void deleteWeeding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        Statement statement;
        String query = "";
        String uName = "";

        java.sql.Connection connection;

         try {
             
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int added = 0;
            query = "Delete From weeding where wid = '" + request.getParameter("wid") + "'";
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

    public static String getWeedID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("wid");
    }

    public static ResultSet getWID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet gWID = null;

        java.sql.Connection connection;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

                int i = 0;
                query = "Select * From weeding where wid = '" + request.getParameter("wid") + "'";                
                Statement st = connection.createStatement();
                gWID = st.executeQuery(query);

        } catch (Exception sqle) {
            writer.println(sqle);
        }
        
        return gWID;
    }
    
    public static String getUserView1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("reguserview1");
    }

    public static String getUserView2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("reguserview2");
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

    public static void saveWeeding(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        java.sql.Connection connection;
        Statement statement;
        String query = "";

        String id = request.getParameter("id");
        String[] entryFields = { "pdate", "ctype", "cvariety", "crops", "aplanted", "stype", "eyields", "ahdate" };
        String[] entry = new String[8];

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        String ip = request.getRemoteAddr();

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            if (request.getParameter("reguserview3") != null) {

                int added = 0;
                statement = connection.createStatement();
                query = "Update weeding Set wDate = '" +  request.getParameter("wdate") + "'Where wid='" + request.getParameter("wid") + "'";
                added = statement.executeUpdate(query);
                statement.close();

                added = 0;
                statement = connection.createStatement();
                query = "Update weeding Set AreaWeeded = '" +  request.getParameter("aWeeded") + "'Where wid='" + request.getParameter("wid") + "'";
                added = statement.executeUpdate(query);
                statement.close();

            } else {

                String pID = "";
                try {
                    Integer.parseInt(request.getParameter("pid"));
                    pID = request.getParameter("pid");
                } catch (Exception e) {
                    pID = "0";
                }

                int added = 0;
                statement = connection.createStatement();                        
                query = "INSERT INTO weeding(fid, wDate, AreaWeeded, pid) VALUES ('" + request.getParameter("fid") + "','" + request.getParameter("wdate") + "','" + request.getParameter("aWeeded")+ "','" + pID + "')";
                added = statement.executeUpdate(query);                                     
            
                statement.close();
            }
            
        } catch(Exception e) {
            writer.println(e);
        }

    }

    public static ResultSet getPlantingHarvesting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResultSet gPH = null;

        try {

            java.sql.Connection connection;
            Statement statement;
            String query = "";

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            String none = "null";

            int i = 0;
            query = "SELECT * FROM planting where fid = '" + request.getParameter("fid") + "' and harvested is null";
            statement = connection.createStatement();
            gPH = statement.executeQuery(query);

        } catch (Exception e) {}

        return gPH;
    }

}