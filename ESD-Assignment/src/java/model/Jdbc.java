/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.util.Date;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import pages.MemberLogin;


/**
 *
 * @author me-aydin
 */
public class Jdbc {

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    //String query = null;

    public Jdbc(String query) {
        //this.query = query;
    }

    public Jdbc() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void connect(Connection con) {
        connection = con;
    }

    private ArrayList rsToList(String results) throws SQLException {
        ArrayList aList = new ArrayList();

        int cols = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            String[] s = new String[cols];
            for (int i = 1; i <= cols; i++) {
                s[i - 1] = rs.getString(i);
            }
            aList.add(s);
        } // while    
        return aList;
    } //rsToList

    private String makeTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        for (Object s : list) {
            b.append("<tr>");
            row = (String[]) s;
            for (String row1 : row) {
                b.append("<td>");
                b.append(row1);
                b.append("</td>");
            }
            b.append("</tr>\n");
        } // for
        b.append("</table>");
        return b.toString();
    }//makeHtmlTable

    private void select(String query) {
        //Statement statement = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            //statement.close();
        } catch (SQLException e) {
            System.out.println("way way" + e);
            //results = e.toString();
        }
    }

    public String retrieve(String query) throws SQLException {
        String results = "";
        select(query);
//        try {
//            
//            if (rs==null)
//                System.out.println("rs is null");
//            else
//                results = makeTable(rsToList());
//        } catch (SQLException ex) {
//            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return makeTable(rsToList(results));//results;
    }

    public boolean exists(String user) {
        boolean bool = false;
        try {
            select("select id from users where id='" + user + "'");
            if (rs.next()) {
                System.out.println("TRUE");
                bool = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bool;
    }

    public boolean validate(String name, String pass) {
        boolean status = false;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * from USERS WHERE id=? and password=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            status = rs.next();
            ps.close();
            System.out.println("Valid");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    
    public String retrieveStatus(String name, String pass) {
        String status = null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT status from USERS WHERE id=? and password=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                status = rs.getString("status");
            }
            System.out.println(status);
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    // CHECK FOR OUTSTANDING BALANCE
    public double RetrieveFromMembers(String name, String column, String table) {
        double balance = 0.0;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * from "+table+ " WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                balance = rs.getFloat(column);
            }
            System.out.println(column);
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }
    // LIST ALL CLAIMS AND PAYMENTS TO DATE
    public void makeClaim(String[] str, Double amount) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO claims(mem_id, dateofpurchase, rationale, status, amount) VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
           // ps.setInt(1, 39);
            ps.setString(1, MemberLogin.name);
            ps.setDate(2, new java.sql.Date(new Date().getTime()));
            ps.setString(3, str[0]);
            ps.setString(4, "APPROVED");
            ps.setDouble(5, amount);
            ps.executeUpdate();
            
           
            ps.close();
            System.out.println("1 claim row added payment");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void makePayment(Double amount) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO payments(mem_id, type_of_payment, amount, dateofpurchase) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
           // ps.setInt(1, 21);
           
            ps.setString(1, MemberLogin.name);
            ps.setString(2, "FEE");
            ps.setDouble(3, amount);
            ps.setDate(4, new java.sql.Date(new Date().getTime()));
            ps.executeUpdate();
            
            //request.setAttribute("nameofuser",str[0])
            ps.close();
            System.out.println("1 row added payment");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String RetrieveClaimsAndPayments(String name,String table1, String column1,String column2,String column3) {
        String[] Retrieval = new String[3];
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * from " + table1 + " WHERE mem_id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();         
            while (rs.next()) {
                Retrieval[0] = rs.getString(column1);
                Retrieval[1] = rs.getString(column2);
                Retrieval[2] = rs.getString(column3);                
            }
            System.out.println(Arrays.toString(Retrieval));
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Arrays.toString(Retrieval);
    }
    
    public void insert(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO Users(id, password, status) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim());
            ps.setString(2, str[1]);
            ps.setString(3, "APPLIED");
            ps.executeUpdate();

            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePassword(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update Users Set password=? where id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[1].trim());
            ps.setString(2, str[0].trim());
            ps.executeUpdate();

            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // SUSPEND A MEMBERSHIP ACCOUNT
    public void suspend(String user){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update USERS Set STATUS='SUSPENDED' where id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user);
            ps.executeUpdate();

            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // RESUME A SUSPENDED ACCOUNT
    public void resume(String user){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update USERS Set STATUS='APPROVED' where id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user);
            ps.executeUpdate();

            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(String user) {
        String query = "DELETE FROM Users "
                + "WHERE id = '" + user.trim() + "'";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("way way" + e);
            //results = e.toString();
        }
    }
    
    public void insertNewRegistration(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO Members(id, membername, address, dob, dor, status, balance) VALUES (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0]);
            ps.setString(2, str[1]);
            ps.setString(3, str[2]);
            ps.setString(4, str[3]);
            ps.setDate(5, new java.sql.Date(new Date().getTime()));
            ps.setString(6, "APPLIED");
            ps.setDouble(7, 0.00);
            ps.executeUpdate();

            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void makePayment(String[] str, Double amount) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO payments(id, mem_id, type_of_payment, amount, dateofpurchase) VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 21);
            ps.setString(2, str[0]);
            ps.setString(3, "FEE");
            ps.setDouble(4, amount);
            ps.setDate(5, new java.sql.Date(new Date().getTime()));
            ps.executeUpdate();
            
           
            ps.close();
            System.out.println("1 row added payment");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void provisionalMembership(String[] str){
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement("INSERT INTO USERS(ID, password) VALUES(?,?) WHERE MEMBERS.ID = ?, MEMBERS.STATUS ='APPLIED'");
            ps.setString(1, str[1]);
            ps.setString(2, str[0]);
            ps.executeQuery();
            
            ps.close();
            
        } catch (SQLException ex){
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void processMembership(){
        PreparedStatement ps = null;
        try{
           ps = connection.prepareStatement("UPDATE USERS.STATUS ='APPROVED' WHERE USERS.ID = PAYMENTS.MEM_ID");
           ps.executeQuery();
           ps.close();
           
        }catch (SQLException ex){
           Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);

        }
    } 
            
    public void closeAll() {
        try {
            rs.close();
            statement.close();
            //connection.close();                                         
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        String str = "select * from users";
        String insert = "INSERT INTO `Users` (`id`, `password`) VALUES ('meaydin', 'meaydin')";
        String update = "UPDATE `Users` SET `password`='eaydin' WHERE `username`='meaydin' ";
        String db = "MyDB";

        Jdbc jdbc = new Jdbc(str);
        Connection conn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
//Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Users", "meaydin", "aydinme");
//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "root", "");
        } catch (ClassNotFoundException | SQLException e) {

        }
        jdbc.connect(conn);
        String[] users = {"eaydin", "benim", "benim"};
        System.out.println(jdbc.retrieve(str));
        if (!jdbc.exists(users[0])) {
            jdbc.insert(users);
        } else {
            jdbc.updatePassword(users);
            System.out.println("user name exists, change to another");
        }
        jdbc.delete("aydinme");

        System.out.println(jdbc.retrieve(str));
        jdbc.closeAll();
    }
}
