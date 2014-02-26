package Markets;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Markets {

    static Connection connection;

    public static ResultSet loadMarkets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
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
            query = "SELECT * FROM markets order by description";
            statement = connection.createStatement();
            hLoad = statement.executeQuery(query);

        } catch (Exception npe) {

        }

        return hLoad;    
    }

    public static void deleteMarket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        Statement statement = null;
        String query = "";
        String uName = "";

        java.sql.Connection connection;

        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            // Get record
            int i = 0;
            query = "DELETE FROM markets where mid = '" + request.getParameter("mname")+ "'";
            statement = connection.createStatement();
            i = statement.executeUpdate(query);

            i = 0;
            query = "DELETE FROM marketcrops where mid = '" + request.getParameter("mname") + "'";
            statement = connection.createStatement();
            i = statement.executeUpdate(query);

            i = 0;
            query = "DELETE FROM marketchicks where mid = '" + request.getParameter("mname") + "'";
            statement = connection.createStatement();
            i = statement.executeUpdate(query);

            i = 0;
            query = "DELETE FROM marketeggs where mid = '" + request.getParameter("mname") + "'";
            statement = connection.createStatement();
            i = statement.executeUpdate(query);

            i = 0;
            query = "DELETE FROM marketlivestock where mid = '" + request.getParameter("mname") + "'";
            statement = connection.createStatement();
            i = statement.executeUpdate(query);

            i = 0;
            query = "DELETE FROM marketmilk where mid = '" + request.getParameter("mname") + "'";
            statement = connection.createStatement();
            i = statement.executeUpdate(query);

        } catch (Exception sqle) {
            writer.println(sqle);
        }
    }

    public static String getMName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("mname");
    }

    public static String getFID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("fid");
    }

    public static String getMID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("mid");
    }
    
    public static String getFunc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("func");
    }

    public static ResultSet getMarketID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet gID = null;
        
        Statement statement = null;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");
            
            // Get record
            int i = 0;
            query = "SELECT * FROM markets where mid = '" + request.getParameter("mname") + "'";
            statement = connection.createStatement();
            gID = statement.executeQuery(query);

        } catch (Exception npe) {
            writer.println(npe);
        }

        return gID;

    }
    
    public static ResultSet getMarketID1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet gID = null;
        
        Statement statement = null;
        String query = "";

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");
            
            // Get record
            int i = 0;
            query = "SELECT * FROM markets where mid = '" + request.getParameter("mid") + "'";
            statement = connection.createStatement();
            gID = statement.executeQuery(query);

        } catch (Exception npe) {
            writer.println(npe);
        }

        return gID;

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

    public static void saveMarkets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

            try {

                request.getParameter("func").equals("edit");

                int added = 0;
                statement = connection.createStatement();
                query = "Update markets Set HSCode = '" +  request.getParameter("hscode") + "'Where mid='" + request.getParameter("mid") + "'";
                added = statement.executeUpdate(query);
                statement.close();            
                
                added = 0;
                statement = connection.createStatement();
                query = "Update markets Set Description = '" +  request.getParameter("description") + "'Where mid='" + request.getParameter("mid") + "'";
                added = statement.executeUpdate(query);
                statement.close();            
                
                added = 0;
                statement = connection.createStatement();
                query = "Update markets Set Location = '" +  request.getParameter("location") + "'Where mid='" + request.getParameter("mid") + "'";
                added = statement.executeUpdate(query);
                statement.close();
                
                added = 0;
                statement = connection.createStatement();
                query = "Update markets Set Requirement = '" +  request.getParameter("requirements") + "'Where mid='" + request.getParameter("mid") + "'";
                added = statement.executeUpdate(query);
                statement.close();            

            } catch (NullPointerException npe) {
writer.println("1");
                int added = 0;
                statement = connection.createStatement();                        
                query = "INSERT INTO markets(HSCode, Description, Location, Requirement) VALUES ('" + request.getParameter("hscode") + "','" + request.getParameter("description") + "','" + request.getParameter("location") + "','" + request.getParameter("requirements") + "')";
                added = statement.executeUpdate(query);                                     
                statement.close();

            }

        } catch(Exception e) {
            writer.println(e);
        }

    }

    public static ResultSet getProduce(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResultSet gPH = null;

        try {

            java.sql.Connection connection;
            Statement statement;
            String query = "";

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "SELECT * FROM produce order by description";
            statement = connection.createStatement();
            gPH = statement.executeQuery(query);

        } catch (Exception e) {}

        return gPH;
    }

}