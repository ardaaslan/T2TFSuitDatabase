

<%@page import="tr.com.t2.dao.jdbc.T2TFUserJdbcDAO"%>
<%@page contentType="text/plain"%>
<%@ page import="tr.com.t2.domain.T2TFProject"%>
<%@ page import="tr.com.t2.service.impl.UserService"%>
<%@ page import="tr.com.t2.domain.T2TFUser"%>

<%
    
  String userName = "Arda";
  String projectName = "" ;
  try{
      projectName = request.getParameter("project");
  }
  catch(Exception e){
      
  }
  T2TFUser user = new T2TFUser();
  user.setUserName("deneme1");
  user.setPassword("deneme1");
  T2TFProject project = new T2TFProject();
  project.setProjectName(projectName);
  project.setUserName(userName);
  T2TFUserJdbcDAO dao = new T2TFUserJdbcDAO();
  dao.createUser(user);
  //service.createUser(user);
  //service.createProject(project);
  %>
