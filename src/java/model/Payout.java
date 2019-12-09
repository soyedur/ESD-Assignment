/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.UserServLet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author me-aydin
 */
@WebServlet(name = "Payout", urlPatterns = {"/Payout.do"})
public class Payout extends HttpServlet {

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

        //String query = "";
        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");
        // TESTING
//        Date startQueryDate = java.sql.Date.valueOf(query+"-01-01");
//        Date endQueryDate = java.sql.Date.valueOf(query+"-12-31");
//        float sumAmount = jdbc.retrieveSumOfClaims(startQueryDate, endQueryDate);
        // TESTING 

        int year = Calendar.getInstance().get(Calendar.YEAR);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date start = new java.sql.Date(cal.getTimeInMillis());
        System.out.println("start day " + start);

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31); // new years eve
        Date end = new java.sql.Date(cal.getTimeInMillis());
        System.out.println("start day " + end);

        int numberOfClaimers = jdbc.retrieveUniqueClaims(start, end);
        double sumOfClaims = jdbc.retrieveSumOfClaims(start, end);
        String[] nameOfAllUsers = jdbc.retrieveClaimerNames(start, end);
        System.out.println("number of claimers " + numberOfClaimers);
        System.out.println("sum of claims " + sumOfClaims);   
        
        double calculatedPayout = (double) Math.round((sumOfClaims * 0.25) / numberOfClaimers);
        System.out.println("calculated payout " + calculatedPayout);
        
        if (jdbc == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        } else {
            for (int i = 0; i < nameOfAllUsers.length; i++) {
                System.out.println("name " + nameOfAllUsers[i]);
                jdbc.updateBalance(nameOfAllUsers[i], calculatedPayout);
            }
            request.getRequestDispatcher("/WEB-INF/Payout.jsp").forward(request, response);
        }
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
