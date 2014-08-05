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
import tr.com.t2.domain.T2TFUser;

/**
 *
 * @author CAKIN
 */
@Repository
public class T2TFUserJdbcDAO extends BasejdbcDAO implements T2TFUserDAO {

    private SimpleJdbcInsert insertUser;
    
    @PostConstruct
    public void aftePropertiesSet(){
    
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
        
    }
    
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
        T2TFUser result = namedParameterJdbcTemplate.queryForObject("SELECT * FROM users WHERE userName = :userName", parameters, rowMapperUser);
        return result;
        
    }
    
    
    
}
