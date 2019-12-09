/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;
/**
 *
 * @author me-aydin
 */
public class NewUser extends HttpServlet {
    
    public static String name = NewUser.name;
    public static String pass = NewUser.pass;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
         
        Date query3 = null;
        String [] MEMBERquery = new String[4];
        MEMBERquery[0] = (char)request.getParameter("Forename").charAt(0) + "-" + (String)request.getParameter("Surname");
        MEMBERquery[1] = (String)request.getParameter("Forename") + " " + (String)request.getParameter("Surname");
        MEMBERquery[2] = (String)request.getParameter("address");
        query3 = Date.valueOf(request.getParameter("year") + "-" + 
                request.getParameter("month") + "-" +  request.getParameter("day"));
        
        String pass = request.getParameter("day") + request.getParameter("month") + 
                request.getParameter("year").charAt(2) + request.getParameter("year").charAt(3);

        String[] USERquery = new String[2];
        USERquery[0] = MEMBERquery[0];
        USERquery[1] = pass;
        
        name = USERquery[0];
        pass =  USERquery[1];
        
        Jdbc jdbc = (Jdbc)session.getAttribute("dbbean"); 
        
        if (jdbc == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        if(MEMBERquery[0].equals("") ) {
            request.setAttribute("message", "Username cannot be NULL");
        } 
        else if(jdbc.exists(MEMBERquery[0])){
            request.setAttribute("message", MEMBERquery[0]+" is already taken as username");
        }
        else {       
            jdbc.insertNewRegistration(MEMBERquery, query3);
            request.setAttribute("message", MEMBERquery[0]+" is added");
            jdbc.insert(USERquery);
            
        }
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("index.jsp").include(request, response);
        out.println("<br/>" + "Your username:" + name + "<br/>" + "Your password:" + pass);
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
