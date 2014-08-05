/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.com.t2.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 *
 * @author CAKIN
 */
public class BasejdbcDAO {
    @Autowired
    @Qualifier("jdbcTemplate")
    protected JdbcTemplate jdbcTemplate;
    
    @Autowired
    @Qualifier("namedParameterJdbcTemplate")
    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
}
