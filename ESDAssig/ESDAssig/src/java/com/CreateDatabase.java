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

public class CreateDatabase {
    // JDBC driver name and database URL

    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static final String DB_URL = "jdbc:derby://localhost:1527/";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "password";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE xyz_Assoc";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
            
            sql = "CREATE TABLE IF NOT EXISTS `Claims` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `mem_id` text NOT NULL,\n" +
                    "  `date` date NOT NULL,\n" +
                    "  `rationale` text NOT NULL,\n" +
                    "  `status` varchar(10) NOT NULL,\n" +
                    "  `amount` float NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ")";
            stmt.executeUpdate(sql);
            
            sql = "CREATE TABLE IF NOT EXISTS `Members` (\n" +
                    "  `id` text CHARACTER SET ascii NOT NULL,\n" +
                    "  `name` text CHARACTER SET ascii,\n" +
                    "  `address` text CHARACTER SET ascii,\n" +
                    "  `dob` date DEFAULT NULL,\n" +
                    "  `dor` date DEFAULT NULL,\n" +
                    "  `status` text NOT NULL,\n" +
                    "  `balance` float NOT NULL,\n" +
                    "  PRIMARY KEY (`id`(10))\n" +
                    ")";
            stmt.executeUpdate(sql);
            
            sql = "CREATE TABLE IF NOT EXISTS `payments` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `mem_id` text NOT NULL,\n" +
                "  `type_of_payment` char(10) NOT NULL,\n" +
                "  `amount` float NOT NULL,\n" +
                "  `date` datetime NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ")";
            stmt.executeUpdate(sql);
            
            sql = "CREATE TABLE IF NOT EXISTS `users` (\n" +
                    "  `id` text CHARACTER SET ascii NOT NULL,\n" +
                    "  `password` text NOT NULL,\n" +
                    "  `status` text NOT NULL,\n" +
                    "  PRIMARY KEY (`id`(10))\n" +
                    ")";
            stmt.executeUpdate(sql);
            
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample
