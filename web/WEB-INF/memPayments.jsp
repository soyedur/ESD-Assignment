<%-- 
    Document   : memPayments
    Created on : 16-Nov-2019, 01:12:31
    Author     : Soyedur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "model.Payment" %>
<%@ page import = "model.AdminLogin" %>
<%@ page import = "model.Jdbc" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
    </head> 

    <body>
        <% if (model.AdminLogin.astatus.equals("ADMIN")) {%>
        <%=(String) (session.getAttribute("queryPayments"))%>
        <form method="link" action="backServlet.do">
            <input type="submit" name="BackBtn" value="Dashboard"/>
        </form>
        <% 
        }
            else {
        %>
        <form method="POST" action="Payment.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Pay outstanding Balance: </td>
                    <td><input type="number" name="amount" value=<%=(Double) (session.getAttribute("balance"))%> readonly></td>
                </tr>

                <tr> 
                    <td> <input type="submit" value="Make payment"/></td>
                </tr>
            </table>
        </form> 
                <button class="exit" onclick="history.go(-1)"> 
            Click here to go back 
        </button>
        <%
        }
        %>

        

    </body>
</html>