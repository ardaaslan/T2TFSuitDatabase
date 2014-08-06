/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.t2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tr.com.t2.domain.T2TFUser;
import tr.com.t2.service.IUserService;

/**
 *
 * @author GOX
 */
public class RegisterServlet extends HttpServlet {
    
    @Autowired
    IUserService userService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
   
    }

    int flag = 0;
    boolean errorFlag = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (flag == 0) {
            flag++;

            RequestDispatcher rs = request.getRequestDispatcher("registerpage.html");
            rs.forward(request, response);
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            
            if (username.length() == 0) {
                RequestDispatcher rs = request.getRequestDispatcher("registerpage.html");
                out.println("<font color=");
                out.println('"');
                out.println("red");
                out.println('"');
                out.println('>');

                out.println("<h5>");
                out.println("<center>");
                out.println("Please Enter Your Username");
                out.println("</center>");
                out.println("</h5>");

                out.println("</font>");
                
                rs.include(request, response);
            } else if (password.length() == 0) {
                RequestDispatcher rs = request.getRequestDispatcher("registerpage.html");

                out.println("<font color=");
                out.println('"');
                out.println("red");
                out.println('"');
                out.println('>');

                out.println("<h5>");
                out.println("<center>");
                out.println("Please Enter Your Password");
                out.println("</center>");
                out.println("</h5>");

                out.println("</font>");
                rs.include(request, response);
            } else {
                
                if(userService.ifUserExists(username)){
                    errorFlag = true;
                }
                else{
                    T2TFUser user = new T2TFUser();
                    user.setUserName(username);
                    user.setPassword(password);
                    userService.createUser(user);
                    System.out.println("User created !");
                }
                
                
                if(errorFlag == false){ //SEeelam yaktın beni hain :c
                    flag = 0;
                    errorFlag = false;
                    response.sendRedirect("LoginServlet");
                }
                else{
                    errorFlag = false;
                    RequestDispatcher rs = request.getRequestDispatcher("registerpage.html");

                    out.println("<font color=");
                    out.println('"');
                    out.println("red");
                    out.println('"');
                    out.println('>');

                    out.println("<h5>");
                    out.println("<center>");
                    out.println("User Already Exists");
                    out.println("</center>");
                    out.println("</h5>");

                    out.println("</font>");
                    rs.include(request, response);
                }
            }
        }
    }
}
