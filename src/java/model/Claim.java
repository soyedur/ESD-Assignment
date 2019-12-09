/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;
import java.util.Calendar;

/**
 *
 * @author me-aydin
 */
@WebServlet(name = "Claim", urlPatterns = {"/Claim.do"})
public class Claim extends HttpServlet {

    //public static String name = MemberLogin.name;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);
        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection) request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);

        String[] query = new String[1];
        //Double [] amounts = new Double[1];
        double amount = 0;
        //query[0] = (String)request.getParameter("username");
        query[0] = (String) request.getParameter("rationale");
        // amount = Double.parseDouble(query)

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        Date date2 = cal.getTime();
        System.out.println("date2 = " + date2);

        Date date = dbBean.RetrieveDATEOFMEMBERSHIP((String) session.getAttribute("nameofuser"));
        System.out.println((String) session.getAttribute("nameofuser"));
        System.out.println("date"+date);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);
        cal2.add(Calendar.YEAR, 1);
        Date expiryDate = new java.sql.Date(cal2.getTimeInMillis());
        System.out.println("register date = " + date + " expiryDate = " + expiryDate);

        amount = Double.parseDouble(request.getParameter("amount"));

        //String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('";
        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");
        
        int retrieveAllClaims = jdbc.retrieveCheckTwoClaims((String) session.getAttribute("nameofuser"), date, expiryDate);
        
        PrintWriter out = response.getWriter();
        String message = null;
        
        
        if (jdbc == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }

        if (query[0].equals("")) {
            request.setAttribute("message", "cannot be NULL");
        } 
        else if (request.getParameter("amount") == null) {
            request.setAttribute("message", "amount cannot be NULL");
        } 
        else if (!jdbc.retrieveMORETHANONEFEE((String) session.getAttribute("nameofuser"))) {
            if (date.before(date2) == false) {
                System.out.println("member less than 6 months");
                System.out.println("logged on as " + (String) session.getAttribute("nameofuser"));
                session.setAttribute("message", "You must wait 6 months");
//                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response); 
                message ="Cannot make a claim within 6 months of registration.";
            }
            else {
                if (retrieveAllClaims < 2) {
                    System.out.println("logged as " + (String) session.getAttribute("nameofuser"));
                    System.out.println("member 6 months+ ");
                    System.out.println("t date " + date);
                    jdbc.makeClaim(query, amount, (String) session.getAttribute("nameofuser"));
//                    request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response); 
                    message ="Claim made! Awaiting approval.";
                }
                else {
                    System.out.println("More than 2 claims this year " + retrieveAllClaims);
                    System.out.println("logged on as " + (String) session.getAttribute("nameofuser"));
                    session.setAttribute("message", "You cannot make any more claims");
//                    request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response); 
                    message ="Cannot make any more claims this year.";
                }
            }
        } 
        else if (jdbc.retrieveMORETHANONEFEE((String) session.getAttribute("nameofuser")))
        {
            System.out.println("1");
            System.out.println("number of claims "+retrieveAllClaims);
            if (retrieveAllClaims >= 2){
                System.out.println("2");
                System.out.println("More than 2 claims this year " + retrieveAllClaims);
                System.out.println("logged on as " + (String) session.getAttribute("nameofuser"));
                session.setAttribute("message", "You cannot make any more claims");
//                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response); 
                message ="Cannot make any more claims this year.";
            }
            else{
                System.out.println("3");
                 System.out.println("logged as " + (String) session.getAttribute("nameofuser"));
                 System.out.println("Less than 2 claims this year ");
                 System.out.println("t date " + date);
                 jdbc.makeClaim(query, amount , (String) session.getAttribute("nameofuser"));
//                 request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response); 
                message ="Claim made! Awaiting approval.";
            }
        } 
        else {
            System.out.println("How did i get here?");
//            request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response); 
//            System.out.println("logged as " + MemberLogin.name);
//            System.out.println("member 6 months+ ");
//            System.out.println("t date " + date);
//            jdbc.makeClaim(query, amount);
//
//            //jdbc.makePayment(query);
//            request.setAttribute("message", query[0] + " payment is added");
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);

        out.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
