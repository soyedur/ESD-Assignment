/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author k2-banh
 */
import java.sql.*;

public class LoginDao {

    public static boolean validate(String name, String pass) {
        boolean status = false;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/XYZ_assoc", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                    "select * from USERS where id=? and password=? and status=?");
            ps.setString(1, name);
            ps.setString(2, pass);
            String wow = null;
            ps.setString(3,wow);
            
            if(wow == "unApproved"){
                System.out.println("This acc is unApproved");
            }
            

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
}
