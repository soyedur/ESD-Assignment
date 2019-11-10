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

public class RegisterDao {

    public static boolean validate(String name, String pass, String status2) {
        boolean status = false;
        try {
            // create a mysql database connection
            String myDriver = "org.apache.derby.jdbc.ClientDriver";
            String myUrl = "jdbc:derby://localhost:1527/XYZ_assoc";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "password");

            // the mysql insert statement
            String query = " insert into USERS (id, password, status)"
                    + " values (?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, pass);
            preparedStmt.setString(3, status2);
            
            preparedStmt.execute();

            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return status;
    }
}
