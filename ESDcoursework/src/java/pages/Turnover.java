/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages;

import com.UserServLet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Jdbc;

/**
 *
 * @author me-aydin
 */
@WebServlet(name = "Turnover", urlPatterns = {"/Turnover.do"})
public class Turnover extends HttpServlet {

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
        dbBean.connect((Connection)request.getServletContext().getAttribute("connection"));
        session.setAttribute("dbbean", dbBean);

        //String query = "";
        String query = request.getParameter("Year");
        String qryIncomeDetails = "select * from payments where DATEOFPURCHASE between '"+query+"-01-01' AND '"+query+"-12-31'";
        String qryIncome = "select SUM(amount) from payments where DATEOFPURCHASE between '"+query+"-01-01' AND '"+query+"-12-31'";
        String qryPayoutDetails = "select * from claims where DATEOFPURCHASE between '"+query+"-01-01' AND '"+query+"-12-31'";
        String qryPayout = "select SUM(amount) from claims where DATEOFPURCHASE between '"+query+"-01-01' AND '"+query+"-12-31'";
        Jdbc jdbc = (Jdbc)session.getAttribute("dbbean"); 
        
        if (jdbc == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        if(qryIncome == null) {
            request.setAttribute("message", "cannot be NULL");
        } 
        else {
            
            String msg1="No Data";
            String msg2="No Data";
            String msg3="No Data";
            String msg4="No Data";
            try {
                msg1 = dbBean.retrieve(qryIncomeDetails);
                msg2 = dbBean.retrieve(qryIncome);
                msg3 = dbBean.retrieve(qryPayoutDetails);
                msg4 = dbBean.retrieve(qryPayout);
                
            
            } catch (SQLException ex) {
                Logger.getLogger(UserServLet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("queryIncomeDetails", msg1);
            request.setAttribute("queryIncome", msg2);
            request.setAttribute("queryPayoutDetails", msg3);
            request.setAttribute("queryPayout", msg4);
            request.getRequestDispatcher("/WEB-INF/turnoverReport.jsp").forward(request, response);
       
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
