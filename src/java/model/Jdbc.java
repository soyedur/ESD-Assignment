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
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        return makeTable(rsToList(results));//results;
    }

    
    // Checks if a user exists within the database
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
    public double RetrieveFromMembers(String name) {
        double balance = 0.0;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * from MEMBERS WHERE id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                balance = rs.getFloat("BALANCE");
            }
            System.out.println("BALANCE");
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }

    // LIST ALL CLAIMS AND PAYMENTS TO DATE
    public void makeClaim(String[] str, Double amount, String name) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO claims(mem_id, dateofpurchase, rationale, status, amount) VALUES (?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            // ps.setInt(1, 39);
            ps.setString(1, name);
            ps.setDate(2, new java.sql.Date(new Date().getTime()));
            ps.setString(3, str[0]);
            ps.setString(4, "APPLIED");
            ps.setDouble(5, amount);
            ps.executeUpdate();

            ps.close();
            System.out.println("1 claim row added payment");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void makePayment(Double amount,String name) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO payments(mem_id, type_of_payment, amount, dateofpurchase) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            // ps.setInt(1, 21);

            ps.setString(1, name);
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

    public String RetrieveClaims(String name) {
        PreparedStatement ps = null;
        ArrayList<String> claims = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        try {
            ps = connection.prepareStatement("SELECT * from CLAIMS WHERE mem_id=? AND STATUS='APPROVED'", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                claims.add(rs.getString("DATEOFPURCHASE"));
                claims.add(" ");
                claims.add(rs.getString("RATIONALE"));
                claims.add(" ");
                claims.add(rs.getString("AMOUNT"));
                claims.add("\n");
            }
                for (String s : claims) {
                    sb.append(s);
                }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
    
    public int retrieveCheckTwoClaims(String name, Date date, Date expiryDate) {
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = connection.prepareStatement("SELECT * from CLAIMS WHERE mem_id=? AND STATUS='APPROVED' AND DATEOFPURCHASE between ? AND ?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setDate(2, (java.sql.Date) date);
            ps.setDate(3, (java.sql.Date) expiryDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
                System.out.println("aaaaaaaa "+count);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public String RetrievePayments(String name) {
        PreparedStatement ps = null;
        ArrayList<String> payments = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        try {
            ps = connection.prepareStatement("SELECT * from PAYMENTS WHERE mem_id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                payments.add(rs.getString("DATEOFPURCHASE"));
                payments.add(" ");
                payments.add(rs.getString("TYPE_OF_PAYMENT"));
                payments.add(" ");
                payments.add(rs.getString("AMOUNT"));
                payments.add("\n");
            }
            for (String s : payments) {
                    sb.append(s);
                }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
    
    public double RetrieveBalance(String name) {
        PreparedStatement ps = null;
        double balance = 0.0;
        try {
            ps = connection.prepareStatement("SELECT * from MEMBERS WHERE ID=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                balance = rs.getFloat("BALANCE");
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }
    
    public Date RetrieveDATEOFMEMBERSHIP(String name) {
        PreparedStatement ps = null;
        Date userDOR = null;
        try {
            ps = connection.prepareStatement("SELECT DATEOFPURCHASE FROM PAYMENTS WHERE DATEOFPURCHASE=(SELECT MAX(DATEOFPURCHASE) FROM MEMBERS WHERE MEM_ID = ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userDOR = rs.getDate("DATEOFPURCHASE");
                System.out.println("sdgsf "+userDOR);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userDOR;
    }
    
    public boolean retrieveMORETHANONEFEE(String name) {
        PreparedStatement ps = null;
        boolean moreThanOne = false;
        int count = 0;
        try {
            ps = connection.prepareStatement("SELECT DATEOFPURCHASE FROM PAYMENTS WHERE DATEOFPURCHASE=(SELECT MAX(DATEOFPURCHASE) FROM MEMBERS WHERE MEM_ID = ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
                System.out.println("How many FEEs: "+count);
            }
            if (count > 1) {
                System.out.println("Has had more than 1 membership");
                moreThanOne = true;
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return moreThanOne;
    }
    // TESTING
    public double retrieveSumOfClaims(Date startDate, Date endDate){
        PreparedStatement ps = null;
        double totalAmount = 0;
        double amount = 0;
        try {
            ps = connection.prepareStatement("SELECT AMOUNT FROM CLAIMS WHERE STATUS='APPROVED' AND DATEOFPURCHASE BETWEEN ? AND ?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, (java.sql.Date) startDate);
            ps.setDate(2, (java.sql.Date) endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("1 " + startDate);
                System.out.println("2 "+endDate);
                System.out.println("3 "+rs);
                amount = rs.getFloat("AMOUNT");
                totalAmount = totalAmount + amount;
                System.out.println("this is the amount: "+totalAmount);
            }   
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalAmount;
    }
    // TESTING
    
    public int retrieveUniqueClaims(Date startDate, Date endDate){
        PreparedStatement ps = null;
        int numberOfClaimers = 0;
        try {
            ps = connection.prepareStatement("SELECT DISTINCT PAYMENTS.MEM_ID, MEMBERS.STATUS FROM PAYMENTS, MEMBERS WHERE MEMBERS.STATUS='APPROVED' AND PAYMENTS.DATEOFPURCHASE BETWEEN ? AND ?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, (java.sql.Date) startDate);
            ps.setDate(2, (java.sql.Date) endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("1 " + startDate);
                System.out.println("2 "+endDate);
                System.out.println("3 "+rs);
                numberOfClaimers++;
                System.out.println("this is the amount: "+numberOfClaimers);
            }   
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numberOfClaimers;
    }
    
    public String[] retrieveClaimerNames(Date startDate, Date endDate){
        PreparedStatement ps = null;
        ArrayList<String> numberOfClaimers = new ArrayList<String>();
        try {
            ps = connection.prepareStatement("SELECT DISTINCT MEM_ID FROM PAYMENTS WHERE DATEOFPURCHASE BETWEEN ? AND ?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, (java.sql.Date) startDate);
            ps.setDate(2, (java.sql.Date) endDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("MEM_ID");
                numberOfClaimers.add(name);
                System.out.println("beepboop "+numberOfClaimers);
            }   
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (String[]) numberOfClaimers.toArray(new String[numberOfClaimers.size()]);
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

    // SUSPEND A USER ACCOUNT
    public void suspend(String user) {
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
    
        // SUSPEND A MEMBERSHIP ACCOUNT
    public void suspendMember(String user) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update MEMBERS Set STATUS='SUSPENDED' where id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user);
            ps.executeUpdate();

            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // DEACTIVATE ACCOUNT
    public void deactivate(String user) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update USERS Set STATUS='DEACTIVATED' where id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user);
            ps.executeUpdate();

            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // RESUME A SUSPENDED ACCOUNT
    public void resumeUser(String user) {
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
    
    public void resumeMember(String user) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update MEMBERS Set STATUS='APPROVED' where id=?", PreparedStatement.RETURN_GENERATED_KEYS);
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

    public void insertNewRegistration(String[] str, Date date) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO Members(id, membername, address, dob, dor, status, balance) VALUES (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0]);
            ps.setString(2, str[1]);
            ps.setString(3, str[2]);
            ps.setDate(4, (java.sql.Date) date);
            ps.setDate(5, new java.sql.Date(new Date().getTime()));
            ps.setString(6, "APPLIED");
            ps.setDouble(7, 10.00);
            ps.executeUpdate();

            ps.close();
            System.out.println("1 row added.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void updateBalance(String str, double amount){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update MEMBERS Set BALANCE=? where id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, amount);
            ps.setString(2, str);
            ps.executeUpdate();

            ps.close();
            System.out.println("1 row added payment");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void processMembershipUser(String[] query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update USERS Set STATUS='APPROVED' where id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, query[0]);
            ps.executeUpdate();
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void processMembershipMember(String[] query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("Update MEMBERS Set STATUS='APPROVED' where id=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, query[0]);
            ps.executeUpdate();
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void processClaims(String[] query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE CLAIMS SET STATUS='APPROVED' WHERE MEM_ID=? AND RATIONALE=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, query[0]);
            ps.setString(2, query[1]);
            ps.executeUpdate();
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteClaims(String[] query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("UPDATE CLAIMS SET STATUS='REJECTED' WHERE MEM_ID=? AND RATIONALE=?", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, query[0]);
            ps.setString(2, query[1]);
            ps.executeUpdate();
            ps.close();
            System.out.println("1 rows updated.");
        } catch (SQLException ex) {
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

