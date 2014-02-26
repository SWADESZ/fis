package Connections;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connections {
    
    static Connection connection = null;

    public static void setCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fis", "root", "Makaveli1978");
        } catch (Exception npe) {
        }
    }
    
    public static void closeCon() {
        try {
            connection.close();
        } catch (Exception npe) {
        }
    }
}