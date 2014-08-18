/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.com.t2.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tr.com.t2.dao.T2TFUserDAO;
import tr.com.t2.domain.T2TFProject;
import tr.com.t2.domain.T2TFTestSuite;
import tr.com.t2.domain.T2TFUser;

/**
 *
 * @author CAKIN
 */
@Repository
public class T2TFUserJdbcDAO extends BasejdbcDAO implements T2TFUserDAO {

    private SimpleJdbcInsert insertUser;
    private SimpleJdbcInsert insertProject;
    private SimpleJdbcInsert insertTestSuite;
    
    @PostConstruct
    public void aftePropertiesSet(){
    
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
         this.insertProject = new SimpleJdbcInsert(jdbcTemplate).withTableName("projects");
         this.insertTestSuite = new SimpleJdbcInsert(jdbcTemplate).withTableName("testsuites");
        
    }
    private final RowMapper<T2TFTestSuite> rowMapperTestSuite = new RowMapper<T2TFTestSuite>(){

        @Override
        public T2TFTestSuite mapRow(ResultSet rs, int i) throws SQLException {
            T2TFTestSuite testSuite = new T2TFTestSuite();
            testSuite.setUserName(rs.getString("userName"));
            testSuite.setProjectName(rs.getString("projectName"));
            testSuite.setTestSuiteName(rs.getString("testSuiteName"));
            return testSuite;
        }        
            
    };
    
    private final RowMapper<T2TFProject> rowMapperProject = new RowMapper<T2TFProject>(){

        @Override
        public T2TFProject mapRow(ResultSet rs, int i) throws SQLException {
            T2TFProject project = new T2TFProject();
            project.setUserName(rs.getString("userName"));
            project.setProjectName(rs.getString("projectName"));
            return project;
        }        
            
    };
    
    private final RowMapper<T2TFUser> rowMapperUser = new RowMapper<T2TFUser>(){

        @Override
        public T2TFUser mapRow(ResultSet rs, int i) throws SQLException {
            T2TFUser user = new T2TFUser();
            user.setUserName(rs.getString("userName"));
            user.setPassword(rs.getString("password"));
            return user;
        }        
            
    };
    
    @Override
    public List<T2TFUser> listAllUsers() {
    
        Map<String,Object> parameters = new HashMap<String,Object>();
        List<T2TFUser> result = namedParameterJdbcTemplate.query("SELECT * FROM users", parameters, rowMapperUser);
        return result;
    }

    @Override
    public void createUser(T2TFUser user) {
        
        MapSqlParameterSource userParameters = new MapSqlParameterSource();
        userParameters.addValue("userName", user.getUserName());
        userParameters.addValue("password", user.getPassword());
        this.insertUser.execute(userParameters);
        
    }

    @Override
    public void updateUser(T2TFUser user) {
    
        jdbcTemplate.update("UPDATE users SET "+
                "userName = ?"+
                "password = ?"+
                "where userName = ?"
                ,new Object[]{user.getUserName(),user.getPassword(),user.getUserName()}
                ,new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
        
    }

    @Override
    public void deleteUser(T2TFUser user) {
        jdbcTemplate.update("DELETE FROM users "
                + "where userName = ? "
                ,new Object[]{user.getUserName()},new int[]{Types.VARCHAR});
    }

    @Override
    public T2TFUser findByUsername(String userName) {
        
        Map<String,Object> parameters = new HashMap<String,Object>();
        parameters.put("userName", userName);
        try{
            T2TFUser result = namedParameterJdbcTemplate.queryForObject("SELECT * FROM users WHERE userName = :userName", parameters, rowMapperUser);
            return result;
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public void createProject(T2TFProject project) {
               MapSqlParameterSource projectParameters = new MapSqlParameterSource();
               projectParameters.addValue("userName", project.getUserName());
               projectParameters.addValue("projectName",project.getProjectName());
               this.insertProject.execute(projectParameters);
        
        
    }
    
    @Override
    public void createTestSuite(T2TFTestSuite testSuite){
            MapSqlParameterSource projectParameters = new MapSqlParameterSource();
               projectParameters.addValue("userName", testSuite.getUserName());
               projectParameters.addValue("projectName",testSuite.getProjectName());
               projectParameters.addValue("testSuiteName",testSuite.getTestSuiteName());
               this.insertTestSuite.execute(projectParameters);
    }

    @Override
    public void deleteProject(T2TFProject project) {
               jdbcTemplate.update("DELETE FROM projects "
                + "where userName = ? AND "
                + "projectName = ?"
                ,new Object[]{project.getUserName(),project.getProjectName()},new int[]{Types.VARCHAR,Types.VARCHAR});
    }


    @Override
    public void updateProject(T2TFProject oldProject, T2TFProject newProject) {
        jdbcTemplate.update("UPDATE projects SET "+
                "userName = ?,"+
                "projectName = ? "+
                "where userName = ? "+
                "AND projectName = ?"
                ,new Object[]{newProject.getUserName(),newProject.getProjectName(),oldProject.getUserName(),oldProject.getProjectName()}
                ,new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
    }

    @Override
    public List<T2TFProject> listAllProjects() {
        Map<String,Object> parameters = new HashMap<String,Object>();
        List<T2TFProject> result = namedParameterJdbcTemplate.query("SELECT * FROM projects", parameters, rowMapperProject);
        return result;
    }

    @Override
    public void deleteTestSuite(T2TFTestSuite suite) {
        jdbcTemplate.update("DELETE FROM testsuites "
                + "where userName = ? AND "
                + "projectName = ? AND "
                + "testSuiteName = ?"
                ,new Object[]{suite.getUserName(),suite.getProjectName(),suite.getTestSuiteName()},new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR});
    }
    
}
