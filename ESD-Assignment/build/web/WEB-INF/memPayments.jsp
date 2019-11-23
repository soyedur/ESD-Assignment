<%-- 
    Document   : memPayments
    Created on : 16-Nov-2019, 01:12:31
    Author     : Soyedur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "pages.Payment" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Member Payments</h1>
        <%=(String) (request.getAttribute("queryPayments"))%>
        <%! int i = 0;%>
        <form method="POST" action="Payment.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Enter Amount to pay: </td>
                    <td><input type="number" name="amount" value=10 readonly></td>
                </tr>

                <tr> 
                    <td> <input type="submit" value="Make payment"/></td>
                </tr>
            </table>
        </form>
        <button onclick="history.go(-2)"> 
            Click here to go back 
        </button> 

    </body>
</html>
