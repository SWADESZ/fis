package Planting;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Planting {

    static Connection connection;

    public static ResultSet loadPlanting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
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
            query = "SELECT * FROM planting where fid = '" + request.getParameter("fid") + "'";
            statement = connection.createStatement();
            pLoad = statement.executeQuery(query);

        } catch (Exception npe) {

        }

        return pLoad;    
    }

    public static void deletePlanting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
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
                query = "Delete From planting where pid = '" + request.getParameter("pid") + "'";
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
    
    public static ResultSet getPID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        
        ResultSet gPID = null;
        
        java.sql.Connection connection;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

                int i = 0;
                query = "Select * From planting where pid = '" + request.getParameter("pid") + "'";                
                Statement st = connection.createStatement();
                gPID = st.executeQuery(query);

        } catch (Exception sqle) {
            writer.println(sqle);
        }
        
        return gPID;
    }
    
    public static String getPlantingID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("pid");
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

    public static void savePlanting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                query = "Update planting Set pDate = '" +  request.getParameter("pdate") + "'Where pid='" + request.getParameter("pid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update planting Set Crop_Type = '" +  request.getParameter("ctype") + "'Where pid='" + request.getParameter("pid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update planting Set Crop_Variety = '" +  request.getParameter("cvariety") + "'Where pid='" + request.getParameter("pid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                
                
                added = 0;
                statement = connection.createStatement();
                query = "Update planting Set Crop = '" +  request.getParameter("crops") + "'Where pid='" + request.getParameter("pid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                                                
            
                added = 0;
                statement = connection.createStatement();
                query = "Update planting Set AreaPlanted = '" +  request.getParameter("aplanted") + "'Where pid='" + request.getParameter("pid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                                                
                
                added = 0;
                statement = connection.createStatement();
                query = "Update planting Set SoilType = '" + request.getParameter("stype") + "'Where pid='" + request.getParameter("pid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                                                                

                added = 0;
                statement = connection.createStatement();
                query = "Update planting Set ExpectedYields = '" +  request.getParameter("eyields") + "'Where pid='" + request.getParameter("pid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                                                
                
                added = 0;
                statement = connection.createStatement();
                query = "Update planting Set EstimatedHarvestingDate = '" +  request.getParameter("ahdate") + "'Where pid='" + request.getParameter("pid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
          
            } else {

                    // Insert Record
                    int added = 0;
                    statement = connection.createStatement();                        
                    query = "INSERT INTO planting(fid, pDate, Crop_Type, Crop_Variety, Crop, AreaPlanted, SoilType, ExpectedYields, EstimatedHarvestingDate) VALUES ('" + request.getParameter("fid") + "','" + request.getParameter("pdate") + "','" + request.getParameter("ctype") + "','" + request.getParameter("cvariety") + "','" + request.getParameter("crops")+ "','" + request.getParameter("aplanted") + "','" + request.getParameter("stype") + "','" + request.getParameter("eyields") + "','" + request.getParameter("ahdate") + "')";
                    added = statement.executeUpdate(query);                                     
            
                    statement.close();
            }
            
        } catch(Exception e) {
            writer.println(e);
        }

    }
}