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
@WebServlet(name = "ProcessClaims", urlPatterns = {"/Claims.do"})
public class ProcessClaims extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);

        String[] query = new String[2];
        query[0] = (String) request.getParameter("Username");
        query[1] = (String) request.getParameter("Rationale");
        
        String[] queryDel = new String[2];
        queryDel[0] = (String) request.getParameter("UsernameDel");
        queryDel[1] = (String) request.getParameter("RationaleDel");
        
        Jdbc jdbc = (Jdbc) session.getAttribute("dbbean");

        if (jdbc == null) {
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        }

//        if (query[0].equals("")) {
//            request.setAttribute("message", "Username cannot be NULL");
//            
//        } else{ 
//                jdbc.processClaims(query);
//                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);       
//        }
         if (query[0] == null && query[1] == null && !queryDel[0].equals("") && !queryDel[1].equals("")) {
            jdbc.deleteClaims(queryDel);
            request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
            
        } else if (!query[0].equals("") && !query[1].equals("") && queryDel[0] == null && queryDel[1] == null){
                jdbc.processClaims(query);
                request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);       
        }else{
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
