/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.com.t2.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import tr.com.t2.dao.T2TFUserDAO;
import tr.com.t2.domain.T2TFProject;
import tr.com.t2.domain.T2TFTestSuite;
import tr.com.t2.domain.T2TFUser;
import tr.com.t2.service.IUserService;

/**
 *
 * @author CAKIN
 */
@Service
public class UserService implements IUserService{

    @Autowired
    T2TFUserDAO userDAO;
    
    @Override
    public List<T2TFUser> listAllUsers() {
        return userDAO.listAllUsers();
    }

    @Override
    public void createUser(T2TFUser user) {
        userDAO.createUser(user);
    }

    @Override
    public void updateUser(T2TFUser user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(T2TFUser user) {
        userDAO.deleteUser(user);
    }

    @Override
    public T2TFUser findByUsername(String userName) {
        return userDAO.findByUsername(userName);
    }

    @Override
    public boolean checkUser(String username, String password) {
        T2TFUser user = userDAO.findByUsername(username);
        if(user == null){return false;}
        if(user.getPassword().equals(password)){return true;}
        else
            return false;
            
    }

    @Override
    public boolean ifUserExists(String username) {
        T2TFUser user = userDAO.findByUsername(username);
        if(user == null){return false;}
        else{return true;}
    }

    @Override
    public void createProject(T2TFProject project) {
       userDAO.createProject(project);
    }

    @Override
    public void createTestSuite(T2TFTestSuite testSuite) {
      userDAO.createTestSuite(testSuite);
    }

    @Override
    public void deleteProject(T2TFProject project) {
        userDAO.deleteProject(project);
    }

    @Override
    public void updateProject(T2TFProject oldProject, T2TFProject newProject) {
       userDAO.updateProject(oldProject, newProject);
    }

    @Override
    public List<T2TFProject> listAllProjects() {
        return userDAO.listAllProjects();
    }

    @Override
    public void deleteTestSuite(T2TFTestSuite suite) {
        userDAO.deleteTestSuite(suite);
    }

    @Override
    public void updateTestSuite(T2TFTestSuite oldTestSuite, T2TFTestSuite newTestSuite) {
       userDAO.updateTestSuite(oldTestSuite, newTestSuite);
    }
        
}
