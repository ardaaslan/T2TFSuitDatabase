/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.login;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author OnurYilmaz
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        RequestDispatcher rs = request.getRequestDispatcher("index.html");
        rs.forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // <editor-fold defaultstate="collapsed" desc="If a client do not enter valid username or password">
        if (username.length() == 0) /*If a client do not enter your user name*/ {
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
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
        } else if (password.length() == 0) /*If a client do not enter your password*/ {
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
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
        }// </editor-fold>
        else
        {
            if (ValidateUserInfo.checkUser(username, password)) {
                response.sendRedirect("MainPageServlet");
            } else {
                RequestDispatcher rs = request.getRequestDispatcher("index.html");

                out.println("<center>");
                out.println("<div class=\"alert alert-danger\" role=\"alert\">");
                out.println("<a href=" + '"' + '#' + '"' + " class=" + '"' + "alert-link" + '"' + '>'
                        + "Invalid Username or Password" + "</a>");
                out.println("</div>");
                out.println("</center>");    
                
                rs.include(request, response);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
