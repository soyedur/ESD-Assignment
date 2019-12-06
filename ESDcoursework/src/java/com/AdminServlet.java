/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;
//////
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Jdbc;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kvzary
 */
public class AdminServlet extends HttpServlet {

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
        String qry = "select * from users WHERE STATUS='APPROVED'";
        String qryMem = "select * from members";
        String qryExpiredMem = "select id, membername, DOR, status from members";
        String qryClaims = "select * from claims";
        String qryPayments = "select * from payments";
        String qryProvisionalMemberApplications = "select * from users WHERE STATUS='APPLIED'";
        String qryMemApplied = "select * from payments";
        String qryClaimsPayments = "select * from claims WHERE STATUS='APPLIED'";
        String retrieveSuspendUsers = "select * from users";

        HttpSession session = request.getSession();

        response.setContentType("text/html;charset=UTF-8");

        Jdbc dbBean = new Jdbc();
        dbBean.connect((Connection) request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);

        if ((Connection) request.getServletContext().getAttribute("connection") == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }

        if (request.getParameter("tbl").equals("List")) {
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qry);
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("query", msg);
            request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
        }
        else if (request.getParameter("tbl").equals("provMem")) {
            String msg = "No users";
            String msg2 = "No users";
            try {
                msg = dbBean.retrieve(qryProvisionalMemberApplications);
                msg2 = dbBean.retrieve(qryMemApplied);
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("queryProvMem", msg);
            request.setAttribute("queryPayment", msg2);
            request.setAttribute("info", "provisional");
            request.setAttribute("nameofuser", "admin");
            request.getRequestDispatcher("/WEB-INF/memberinfo.jsp").forward(request, response);
        }
        else if (request.getParameter("tbl").equals("processMembership")) {
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qryProvisionalMemberApplications);
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("queryProvMem", msg);
            request.getRequestDispatcher("/WEB-INF/MembershipForm.jsp").forward(request, response);
        }
        else if (request.getParameter("tbl").equals("processClaims")) {
            String msg = "No users";
            try {
                msg = dbBean.retrieve(qryClaimsPayments);
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("queryProcessClaims", msg);
            request.getRequestDispatcher("/WEB-INF/processClaims.jsp").forward(request, response);
        } ///////////////////////////////////////////////////////////
        else if (request.getParameter("tbl").equals("memDetails")) {
            String msg = "No members";
            try {
                msg = dbBean.retrieve(qryMem);
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("queryMem", msg);
            request.setAttribute("info", "details");
            request.setAttribute("nameofuser", "admin");
            request.getRequestDispatcher("/WEB-INF/memberinfo.jsp").forward(request, response);
        } //////////////////////////////////////////////////////////
        else if (request.getParameter("tbl").equals("suspendUser")) {
            String msg = "No members";
            try {
                msg = dbBean.retrieve(qryExpiredMem);
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("hi i am here");
            request.setAttribute("queryMem", msg);
            request.setAttribute("info", "details");
            request.setAttribute("nameofuser", "admin");
            request.getRequestDispatcher("/WEB-INF/suspendUser.jsp").forward(request, response);
        } //////////////////////////////////////////////////////////        
        else if (request.getParameter("tbl").equals("memClaims")) {
            String msg = "No Claims";
            try {
                msg = dbBean.retrieve(qryClaims);
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("queryClaims", msg);
            request.getRequestDispatcher("/WEB-INF/memClaims.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("memPayments")) {
            String msg = "No Payments";
            try {
                msg = dbBean.retrieve(qryPayments);
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("queryPayments", msg);
            request.getRequestDispatcher("/WEB-INF/memPayments.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("manageAccounts")) {
            String msg = "No Payments";
            try {
                msg = dbBean.retrieve(retrieveSuspendUsers);
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("hi there");
            request.setAttribute("querySuspend", msg);
            request.getRequestDispatcher("/WEB-INF/manageAccounts.jsp").forward(request, response);
        } else if (request.getParameter("tbl").equals("turnoverReport")) {
            request.getRequestDispatcher("/WEB-INF/turnoverReport.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "del");
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
