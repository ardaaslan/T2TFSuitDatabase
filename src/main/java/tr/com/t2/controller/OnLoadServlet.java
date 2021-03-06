/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.com.t2.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import tr.com.t2.domain.T2TFTestCase;
import tr.com.t2.domain.T2TFTestSuite;
import tr.com.t2.domain.T2TFUser;
import tr.com.t2.service.IUserService;

/**
 *
 * @author Arda
 */
public class OnLoadServlet extends HttpServlet {
       
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
        PrintWriter out = response.getWriter();
        String userInfo = request.getParameter("userInfo");
        ObjectMapper mapper = new ObjectMapper();
        T2TFUser newUser = mapper.readValue(userInfo, T2TFUser.class);
        List<T2TFProject> allProjects =  userService.getUsersProjects(newUser.getUserName());

    Gson gson = new Gson();
String json = gson.toJson(allProjects);

response.setContentType("Application/json");
response.getWriter().write(json);

       
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
    

   
    public void test(String projectID)
    {
   
        T2TFProject a = new T2TFProject();
        a.setProjectName(projectID);
        a.setUserName("zarda");
        userService.createProject(a);
      
    }
}
