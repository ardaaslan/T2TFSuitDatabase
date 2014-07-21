/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GOX
 */


public class RegisterServlet extends HttpServlet {
    
    int flag = 0;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(flag == 0){
        flag++;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher rs = request.getRequestDispatcher("registerServlet.html");
        rs.forward(request, response);
        }
        else{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/test",
                    "root", "");

            String command ="insert into users values('"+username+"','"+password+"')";
            
            PreparedStatement ps = con.prepareStatement(command);
            
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
            }
        flag = 0;
        
        response.sendRedirect("LoginServlet");
        }
    }
}
