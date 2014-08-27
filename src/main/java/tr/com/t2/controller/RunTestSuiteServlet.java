/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.com.t2.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tr.com.t2.domain.T2TFProject;
import tr.com.t2.service.IUserService;

/**
 *
 * @author Arda
 */
public class RunTestSuiteServlet extends HttpServlet {
       
    @Autowired
    IUserService userService;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
   
    }
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
    
    Process p;
        p = Runtime.getRuntime().exec("cmd /c mycmd.bat",null,new File("C:\\Users\\Arda\\Desktop\\Selenium"));
        try {
            p.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(RunTestSuiteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    p.destroy();

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */



}
}