package GetFarm;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFarm {

    public static void loadFarms(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            PrintWriter writer = response.getWriter();
            response.setContentType("text/html");

            Connection connection;
            Statement statement;
            String query = "";
            String uName = "";

            try {
          
                Class.forName("com.mysql.jdbc.Driver").newInstance();

                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

                try {
            
                    int i = 0;
                    query = "SELECT * FROM farms order by name";
                    statement = connection.createStatement();
                    ResultSet cfs = statement.executeQuery(query);
                    boolean proceed = false;

                    //1
                    while (cfs.next()) {
                        writer.println("<tr><td>" + cfs.getString(1) +"</td>" + "<td>" + cfs.getString(2) +"</td>" + "<td>" + cfs.getString(3) +"</td>" + "<td>" + cfs.getString(5) + "</td><td><a href=farmdetails.jsp?fname=" + cfs.getString(1) + "&uname=" + request.getParameter("uname")+ ">view</a></td></tr>");
                    }
            
                    writer.println("</table>");
                
                } catch (Exception sqle) {
                    writer.println("<td align=right>" + sqle + "</td>");
                } 

                connection.close();

            } catch (ClassNotFoundException cnfe) {
                writer.println("<td align=right>" + cnfe + "</td>");
            } catch (Exception e) {
                writer.println("<td align=right>" + e + "</td></p>");
            }
        } catch (NullPointerException npe) {

        } catch (Exception e) {

        }

    }

    
    

}
