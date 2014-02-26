package Livestock;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Livestock {

    static Connection connection;

    public static ResultSet loadLivestock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        
        ResultSet hLoad = null;
        
        java.sql.Connection connection;
        Statement statement;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");
            
            // Get record
            int i = 0;
            query = "SELECT * FROM livestock where fid = '" + request.getParameter("fid") + "'";
            statement = connection.createStatement();
            hLoad = statement.executeQuery(query);

        } catch (Exception npe) {

        }

        return hLoad;    
    }

    public static void deleteLivestock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
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
            query = "Delete From livestock where lsid = '" + request.getParameter("lsid") + "'";
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

    public static String getLivestockID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("lsid");
    }

    public static ResultSet getLSID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet gHID = null;

        java.sql.Connection connection;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "Select * From livestock where lsid = '" + request.getParameter("lsid") + "'";
            Statement st = connection.createStatement();
            gHID = st.executeQuery(query);

        } catch (Exception sqle) {
            writer.println(sqle);
        }

        return gHID;
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

    public static void saveLivestock(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                query = "Update livestock Set lsDate = '" +  request.getParameter("ldate") + "'Where lsid='" + request.getParameter("lsid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update livestock Set lsType = '" +  request.getParameter("ltype") + "'Where lsid='" + request.getParameter("lsid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update livestock Set lsNumber = '" +  request.getParameter("lnumber") + "'Where lsid='" + request.getParameter("lsid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update livestock Set esPrice = '" +  request.getParameter("esprice") + "'Where lsid='" + request.getParameter("lsid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update livestock Set asPrice = '" +  request.getParameter("asprice") + "'Where lsid='" + request.getParameter("lsid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                

                added = 0;
                statement = connection.createStatement();
                query = "Update livestock Set Diptank = '" +  request.getParameter("dtank") + "'Where lsid='" + request.getParameter("lsid") + "'";
                added = statement.executeUpdate(query);
                statement.close();                
                
                added = 0;
                statement = connection.createStatement();
                query = "Update livestock Set Quantity = '" +  request.getParameter("quantity") + "'Where lsid='" + request.getParameter("lsid") + "'";
                added = statement.executeUpdate(query);
                statement.close();

            } else {


                // Insert Record
                    int added = 0;
                    statement = connection.createStatement();                        
                    query = "INSERT INTO livestock(fid, lsDate, lsType, lsNumber, esPrice, asPrice, Diptank, Quantity) VALUES ('" + request.getParameter("fid") + "','" + request.getParameter("ldate") + "','" + request.getParameter("ltype")+ "','" + request.getParameter("lnumber") + "','" + request.getParameter("esprice") + "','" + request.getParameter("asprice") + "','" + request.getParameter("dtank") + "','" + request.getParameter("quantity") + "')";
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