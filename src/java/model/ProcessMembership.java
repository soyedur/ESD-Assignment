/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;

/**
 *
 * @author Kvzary
 */
@WebServlet(name = "ProcessMembership", urlPatterns = {"/Membership.do"})
public class ProcessMembership extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);

        String[] query = new String[2];
        query[0] = (String) request.getParameter("Username");
        query[1] = (String) request.getParameter("Password");
        //String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('";

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        if (jdbc == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }

        if (query[0].equals("")) {
            request.setAttribute("message", "Username cannot be NULL");
            
        } else if (jdbc.validate(query[0], query[1])) {
            // CHECK IF THE DATE IS WITHIN THIS YEAR
            if (jdbc.retrieveStatus(query[0], query[1]).equals("APPLIED")) {
                jdbc.processMembershipUser(query);
                jdbc.processMembershipMember(query);
                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("message", "No user called " + query[0]);
            request.getRequestDispatcher("/WEB-INF/AdminPage.jsp").forward(request, response);
        }
        
        //request.setAttribute("msg", "Admin");
        //request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);    
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

