package GetFarms;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFarms {

    public static ResultSet loadFarms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ResultSet cfs = null;

        try {

            PrintWriter writer = response.getWriter();
            response.setContentType("text/html");

            Connection connection;
            Statement statement;
            String query = "";
            String uName = "";

            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

            try {
            
                int i = 0;
                query = "SELECT * FROM farms order by name";
                statement = connection.createStatement();
                cfs = statement.executeQuery(query);
                boolean proceed = false;

            } catch (SQLException sqle) {
                writer.println("<td align=right>" + sqle + "</td>");
            } catch (Exception e) {
                writer.println("<td align=right>" + e + "</td></p>");
            }

        } catch (Exception npe) {

        }
        
        return cfs;    
    }
    
}