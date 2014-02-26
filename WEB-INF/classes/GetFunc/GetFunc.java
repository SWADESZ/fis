package GetFunc;

import java.util.*;
import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFunc {

    static Connection connection = null;

    public static String getFunction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return request.getParameter("function");
    }

}