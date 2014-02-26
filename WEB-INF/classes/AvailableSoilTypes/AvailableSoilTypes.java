package AvailableSoilTypes;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AvailableSoilTypes {

        static java.sql.Connection connection;
        static Statement statement;
        static String query = "";

    public static ResultSet loadAvailableSoilTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet asLoad = null;

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            query = "SELECT * FROM availablesoiltypes where fid = '" + request.getParameter("fid") + "'";
            statement = connection.createStatement();
            asLoad = statement.executeQuery(query);                

           // connection.close();

        } catch (SQLException sqle) {
            writer.println("<td align=right>" + sqle + "</td>");
        } catch (ClassNotFoundException cnfe) {
            writer.println("<td align=right>" + cnfe + "</td>");
        } catch (Exception e) {
            writer.println("<td align=right>" + e + "</td>");
        }    

        return asLoad;
    }

    public static void saveAvailableSoil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        try {

        Class.forName("com.mysql.jdbc.Driver").newInstance();

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

        if (request.getParameter("reguserview3") != null) {

            try {

                int added = 0;
                statement = connection.createStatement();
                query = "Update availablesoiltypes Set Hectares = '" +  request.getParameter("hectares") + "'Where astid='" + request.getParameter("astid") + "'";
                added = statement.executeUpdate(query);
                statement.close();

            } catch (Exception e) {
                writer.println(e);    
            }
        
        } else {
                
            String sid = "";
            String desc = "";
            String sdesc = "";
            String stype = "";
            int count = 0;
        
            desc = request.getParameter("soils");
            
            for (int x=0;x<desc.length();x++) {
                
                if ((count == 0) & (desc.charAt(x) != ',')) {
                    sid = sid + desc.charAt(x);
                }
            
                if ((count == 1) & (desc.charAt(x) != ',')) {
                    sdesc = sdesc + desc.charAt(x);
                }            
            
                if (desc.charAt(x) == ',') {
                    count=1;
                }
            }
        
            int i = 0;
            query = "SELECT soil_type FROM soils where sid = '" + sid + "'";
            statement = connection.createStatement();
            ResultSet cfs = statement.executeQuery(query);
        
            int x = 0;
        
            while (cfs.next()) {

                stype = cfs.getString(1);

            }
        
            try {
            
                // Insert Record
                int added = 0;
                statement = connection.createStatement();                        
                query = "INSERT INTO availablesoiltypes(fid, SoilType, Description, Hectares) VALUES ('" + request.getParameter("fid") + "','" + stype + "','" + sdesc + "','" + request.getParameter("hectares") + "')";
                added = statement.executeUpdate(query);                                     
            
                statement.close();

            } catch (Exception sqle) {
                
                writer.println("ERROR: " + sqle);   
                
            }
                  
        }
        
        connection.close();
        
    } catch (SQLException sqle) {
        writer.println(sqle);
    } catch (Exception e) {
        writer.println(e);        
    }

    }

    public static ResultSet editAvailableSoil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet editSoil = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            String query = "";
            query = "Select * From availablesoiltypes where astid = '" + request.getParameter("astid") + "'";                
            Statement st = connection.createStatement();
            editSoil = st.executeQuery(query);

        } catch (Exception sqle) {
            writer.println(sqle);
        }
        
        return editSoil;
    }

    public static ResultSet getSoils(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        ResultSet getSoils = null;

        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            int i = 0;
            String query = "";
            query = "SELECT sid, description FROM soils order by description";
            Statement st = connection.createStatement();
            getSoils = st.executeQuery(query);

        } catch (Exception sqle) {
            writer.println(sqle);
        }

        return getSoils;
    }

    public static void deleteAvailableSoil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        try {

            int added = 0;
            String query = "Delete From availablesoiltypes where astid = '" + request.getParameter("astid") + "'";
            Statement st = connection.createStatement();
            added = st.executeUpdate(query);

        } catch (Exception sqle) {
            writer.println(sqle);
        }
    }

    public static String getASTID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("astid");
    }
    
    public static String getFID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("fid");
    }

    public static String getFName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("fname");
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

}