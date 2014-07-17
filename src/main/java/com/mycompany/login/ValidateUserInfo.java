/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author OnurYilmaz
 */
public class ValidateUserInfo {

    public static boolean checkUser(String name, String pass) {
        boolean st = false;

        try {
            Class.forName("com.mysql.jdbc.Driver");
                        
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                    "root", "30081992");

            PreparedStatement ps = conn.prepareStatement("select * from users where userName=" + name
                    + " and password=" + pass);

            ResultSet rs = ps.executeQuery();
            st = rs.next();

        } catch (Exception e) {
        }

        return st;
    }

}
