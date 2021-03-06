/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.com.t2.dao;

import java.util.List;
import tr.com.t2.domain.T2TFProject;
import tr.com.t2.domain.T2TFTestCase;
import tr.com.t2.domain.T2TFTestSuite;
import tr.com.t2.domain.T2TFUser;

/**
 *
 * @author CAKIN
 */
public interface T2TFUserDAO {
    List<T2TFUser> listAllUsers();
    
    List<T2TFProject> listAllProjects();
    
    List<T2TFProject> getUsersProjects(String userName);
    
    List<T2TFTestSuite> getProjectsTestSuites(T2TFProject project);
    
    List<T2TFTestCase> getTestSuitesTestCases(T2TFTestSuite testSuite);
    
    void createUser(T2TFUser user);
    
    void updateUser(T2TFUser user);
    
    void deleteUser(T2TFUser user);
    
    T2TFUser findByUsername(String userName);
    
    void createProject(T2TFProject project);
    
    void createTestSuite(T2TFTestSuite testSuite);
    
    void deleteProject(T2TFProject project);
    
    void updateProject(T2TFProject oldProject, T2TFProject newProject);
    
     void deleteTestSuite (T2TFTestSuite suite);
     
      void updateTestSuite(T2TFTestSuite oldTestSuite, T2TFTestSuite newTestSuite);
      
       void createTestCase(T2TFTestCase testCase);
       
       void deleteTestCase(T2TFTestCase testCase);
       
       void updateTestCase(T2TFTestCase oldTestCase,T2TFTestCase newTestCase);
   
}
