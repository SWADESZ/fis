package Messaging;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.mail.*;
import javax.mail.internet.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Messaging {

    static Connection connection;

    public static void contactUsPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        try {
            // SEND EMAIL
    //Set the host smtp address
    Properties props = new Properties();
    props.put("mail.smtp.host", "mail.swade.co.sz");

    // create a message
    Message msg = new MimeMessage(Session.getDefaultInstance(props, null));

    // set the from and to address
    InternetAddress addressFrom = new InternetAddress("fis@swade.co.sz");
    msg.setFrom(addressFrom);

    //InternetAddress addressTo = new InternetAddress("sms@messaging.clickatell.com"); 
    InternetAddress addressTo = new InternetAddress("wandile@swade.co.sz"); 

    // Optional : You can also set your custom headers in the Email if you Want
    msg.addHeader("MyHeaderName", "myHeaderValue");

            
    
                String text= "BUYER " + request.getParameter("market") + ',' + " PRODUCT " + request.getParameter("prod") + ',' + " PRICE " + request.getParameter("price") + " FROM " + request.getParameter("from") + " TO " + request.getParameter("to") + " SENT FROM SWADE INTRANET DEMO APP";
                String message = "user:maestroit" + '\n' + "password:2TNr5ZGI" + '\n' + "api_id:3428084" + '\n' + "text:" + text + '\n' + "to:" + request.getParameter("msgto");
                msg.setContent(message, "text/plain");        
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sms@messaging.clickatell.com"));
                //msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("wandile@swade.co.sz"));
                Transport.send(msg);

                try {

                    java.sql.Connection connection;
                    Statement statement;
                    String query = "";

                    Class.forName("com.mysql.jdbc.Driver").newInstance();

                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");

                    try {

                        // Insert Record
                        int added = 0;
                        statement = connection.createStatement();                        
                        query = "INSERT INTO smslog(Produce, Market, aFrom, aTo, MsgTo) VALUES ('" + request.getParameter("prod") + "','" + request.getParameter("market") + "','" + request.getParameter("from") + "','" + request.getParameter("to") + "','" + request.getParameter("msgto") + "')";
                        added = statement.executeUpdate(query);                                     
            
                        statement.close();
            
                    } catch (Exception sqle) {
                          writer.println("ERROR: " + sqle);   
                    }
              
                    connection.close();

            } catch (SQLException sqle) {
                writer.println(sqle);
            } catch (Exception e) {
                writer.println(e);        
            }

                writer.println("Message sent to " + request.getParameter("msgto"));
                //out.println(message);

            } catch(Exception e) {
                writer.println(e);
            }

    }
    
    public static void smsAvailableProdPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        
        try {
        // SEND EMAIL
    //Set the host smtp address
    Properties props = new Properties();
    props.put("mail.smtp.host", "mail.swade.co.sz");

    // create a message
    Message msg = new MimeMessage(Session.getDefaultInstance(props, null));

    // set the from and to address
    InternetAddress addressFrom = new InternetAddress("fis@swade.co.sz");
    msg.setFrom(addressFrom);

    //InternetAddress addressTo = new InternetAddress("sms@messaging.clickatell.com"); 
    InternetAddress addressTo = new InternetAddress("wandile@swade.co.sz"); 

    // Optional : You can also set your custom headers in the Email if you Want
    msg.addHeader("MyHeaderName", "myHeaderValue");

            
    
                String text= request.getParameter("fname") + ',' + " PRODUCT " + request.getParameter("pdesc") + ',' + " AVAILABLE FROM " + request.getParameter("from") + " AVAILABLE TO " + request.getParameter("to") + " SENT FROM SWADE INTRANET DEMO APP";
                String message = "user:maestroit" + '\n' + "password:2TNr5ZGI" + '\n' + "api_id:3428084" + '\n' + "text:" + text + '\n' + "to:" + request.getParameter("msgto");
                msg.setContent(message, "text/plain");        
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sms@messaging.clickatell.com"));
                //msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("wandile@swade.co.sz"));
                Transport.send(msg);
                
                try {
                    
                    java.sql.Connection connection;
                    Statement statement;
                    String query = "";

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
        
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");
            
                    try {
                        
                        // Insert Record
                        int added = 0;
                        statement = connection.createStatement();                        
                        query = "INSERT INTO smslog(Produce, Market, aFrom, aTo, MsgTo) VALUES ('" + request.getParameter("prod") + "','" + request.getParameter("market") + "','" + request.getParameter("from") + "','" + request.getParameter("to") + "','" + request.getParameter("msgto") + "')";
                        added = statement.executeUpdate(query);                                     
            
                        statement.close();
            
                    } catch (Exception sqle) {
                        writer.println("ERROR: " + sqle);   
                    }
              
                    connection.close();

            } catch (SQLException sqle) {
                writer.println(sqle);
            } catch (Exception e) {
                writer.println(e);        
            }

                writer.println("Message sent to " + request.getParameter("msgto"));
                //out.println(message);

            } catch(Exception e) {
                writer.println(e);
            }

        
    }
    
    public static void smsProdMarketPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        
        try {
        
        // SEND EMAIL
    //Set the host smtp address
    Properties props = new Properties();
    props.put("mail.smtp.host", "mail.swade.co.sz");

    // create a message
    Message msg = new MimeMessage(Session.getDefaultInstance(props, null));

    // set the from and to address
    InternetAddress addressFrom = new InternetAddress("fis@swade.co.sz");
    msg.setFrom(addressFrom);

    //InternetAddress addressTo = new InternetAddress("sms@messaging.clickatell.com"); 
    InternetAddress addressTo = new InternetAddress("wandile@swade.co.sz"); 

    // Optional : You can also set your custom headers in the Email if you Want
    msg.addHeader("MyHeaderName", "myHeaderValue");
    
                String text= "BUYER " + request.getParameter("market") + ',' + " PRODUCT " + request.getParameter("prod") + ',' + " PRICE " + request.getParameter("price") + " FROM " + request.getParameter("from") + " TO " + request.getParameter("to") + " SENT FROM SWADE INTRANET DEMO APP";
                String message = "user:maestroit" + '\n' + "password:2TNr5ZGI" + '\n' + "api_id:3428084" + '\n' + "text:" + text + '\n' + "to:" + request.getParameter("msgto");
                msg.setContent(message, "text/plain");        
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sms@messaging.clickatell.com"));
                //msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("wandile@swade.co.sz"));
                Transport.send(msg);
                
                try {
                    
                    java.sql.Connection connection;
                    Statement statement;
                    String query = "";

                    Class.forName("com.mysql.jdbc.Driver").newInstance();
        
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");
            
                    try {
                        
                        // Insert Record
                        int added = 0;
                        statement = connection.createStatement();                        
                        query = "INSERT INTO smslog(Produce, Market, aFrom, aTo, MsgTo) VALUES ('" + request.getParameter("prod") + "','" + request.getParameter("market") + "','" + request.getParameter("from") + "','" + request.getParameter("to") + "','" + request.getParameter("msgto") + "')";
                        added = statement.executeUpdate(query);                                     
            
                        statement.close();
            
                    } catch (Exception sqle) {
                          writer.println("ERROR: " + sqle);   
                    }
              
                    connection.close();

            } catch (SQLException sqle) {
                writer.println(sqle);
            } catch (Exception e) {
                writer.println(e);        
            }

                writer.println("Message sent to " + request.getParameter("msgto"));
                //out.println(message);

            } catch(Exception e) {
                writer.println(e);
            }

        
    }

    public static String getFName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("type");
    }
    
    public static String getFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("from");
    }

    public static String getTo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("to");
    }
    
    public static String getPType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("ptype");
    }

    public static String getPDesc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("pdesc");
    }
    
    public static String getProd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("prod");
    }
    
    public static String getMarket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("market");
    }
    
    public static String getPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("price");
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