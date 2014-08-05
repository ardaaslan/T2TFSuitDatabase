/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.t2.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author OnurYilmaz
 */
public class ValidateUserInfo {

    public static boolean checkUser(String name, String pass) {
        boolean st = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/test",
                    "root", "CAKIN");

            PreparedStatement ps = con.prepareStatement("select * from users where userName='" + name
                    + "' and password='" + pass + "'");

            ResultSet rs = ps.executeQuery();
            st = rs.next();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }

        return st;
    }

}
