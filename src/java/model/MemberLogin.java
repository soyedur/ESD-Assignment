/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.UserServLet;
import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.AdminLogin;
import model.Jdbc;

/**
 *
 * @author Kvzary
 */
@WebServlet(name = "Member", urlPatterns = {"/MLogin.do"})
public class MemberLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);

        String[] query = new String[2];
        query[0] = (String) request.getParameter("username");
        query[1] = (String) request.getParameter("password");

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        model.AdminLogin.astatus = jdbc.retrieveStatus(query[0], query[1]);
        if (jdbc == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }

        if (query[0].equals("")) {
            session.setAttribute("message", "Username cannot be NULL");

        } else if (jdbc.validate(query[0], query[1])) {
            if (jdbc.retrieveStatus(query[0], query[1]).equals("APPROVED")) {
                //if (membership of user has not expired) do
                    double q = jdbc.RetrieveFromMembers(query[0]);
                    String clm = jdbc.RetrieveClaims(query[0]);
                    String pymnt = jdbc.RetrievePayments(query[0]);

                    session.setAttribute("nameofuser", query[0]);
                    session.setAttribute("userType",jdbc.retrieveStatus(query[0], query[1]));
                    request.setAttribute("msg", "Member");
                    session.setAttribute("balance", q);
                    session.setAttribute("claim", clm);
                    session.setAttribute("payment", pymnt);
                    request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
                //else 
                //    Status becomes applied
                //    request.getRequestDispatcher("/WEB-INF/memPayments.jsp").forward(request, response);
            } else if (jdbc.retrieveStatus(query[0], query[1]).equals("APPLIED")) {
                double Balance = jdbc.RetrieveBalance(query[0]);
                if (Balance == 10.0) {
                    session.setAttribute("nameofuser", query[0]);
                    System.out.println("negative");
                    System.out.println(Balance);
                    session.setAttribute("userType",jdbc.retrieveStatus(query[0], query[1]));
                    request.setAttribute("msg", "Member");
                    session.setAttribute("balance", Balance);
                    request.getRequestDispatcher("/WEB-INF/memPayments.jsp").forward(request, response);  
                } else {
                    session.setAttribute("nameofuser", query[0]);
                    System.out.println("Awaiting admin approval");
                    System.out.println(Balance);
                    session.setAttribute("userType",jdbc.retrieveStatus(query[0], query[1]));
                    request.getRequestDispatcher("/WEB-INF/ApprovalPage.jsp").forward(request, response);  
                }
            } //            else if (jdbc.retrieveStatus(query[0], query[1]).equals("APPLIED") && balance.equals("0.00")) {
            //                System.out.println("Awaiting admin approval");
            //            }
            else if (jdbc.retrieveStatus(query[0], query[1]).equals("SUSPENDED")) {
                request.setAttribute("msg", "Member");
                session.setAttribute("nameOfUser", query[0]);
                double k = jdbc.RetrieveFromMembers(query[0]);
                session.setAttribute("statusofuser", "suspended");
                session.setAttribute("balance", k);
                request.getRequestDispatcher("/WEB-INF/inactive.jsp").forward(request, response);
            } else if (jdbc.retrieveStatus(query[0], query[1]).equals("DEACTIVATED")) {
                request.setAttribute("msg", "Member");
                session.setAttribute("nameOfUser", query[0]);
                session.setAttribute("statusofuser", "deactivated");
                request.getRequestDispatcher("/WEB-INF/inactive.jsp").forward(request, response);
            } else {
                System.out.println(jdbc.retrieveStatus(query[0], query[1]));
                request.setAttribute("message", "Not a member");
                request.setAttribute("msg", "member");
                request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("message", "The details don't match");
            request.setAttribute("msg", "Member");
            request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
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
