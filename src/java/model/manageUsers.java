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
 * @author k2-banh
 */
@WebServlet(name = "manageUsers", urlPatterns = {"/manageUsers.do"})
public class manageUsers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);
        System.out.println("Here i am again again");
        String queryResume = (String) request.getParameter("Username");
        System.out.println("hello"+queryResume);
        String querySuspend = (String) request.getParameter("name");
        System.out.println("hi"+querySuspend);
        //String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('";

        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        if (jdbc == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }

        if (queryResume == null && !querySuspend.equals("")) {
            jdbc.suspend(querySuspend);
            jdbc.suspendMember(querySuspend);
            request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
            
        } else if (!queryResume.equals("") && querySuspend == null){
                jdbc.resumeUser(queryResume);
                jdbc.resumeMember(queryResume);
                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);       
        } else{
            request.setAttribute("message", "Username cannot be NULL");
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
