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

    private String user = "root";
    private String password = "30081992";
    private String host = "localhost";
    private String database = "test";
    private int port = 3306;

    public static boolean checkUser(String name, String pass) {
        boolean st = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                    "root", "");

            PreparedStatement ps = con.prepareStatement("select * from users where userName='" + name
                    + "' and password='" + pass + "'");

            ResultSet rs = ps.executeQuery();
            st = rs.next();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return st;
    }

}
