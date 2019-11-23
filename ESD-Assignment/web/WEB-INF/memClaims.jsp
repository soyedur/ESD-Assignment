<%-- 
    Document   : memCliams
    Created on : 16-Nov-2019, 01:07:39
    Author     : Soyedur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Member Payments</h1>
        <%=(String) (request.getAttribute("queryClaims"))%>
        <%! int i = 0; %>
        <form method="POST" action="Claim.do">     
            <table>
                <tr>
                    <th></th>
                    <th>Please provide your following details</th>
                </tr>
                <tr>
                    <td>Rationale:</td>
                    <td><input type="text" name="rationale"/></td>
                </tr>
                <tr>
                    <td>Amount:</td>
                    <td><input type="number" name="amount"/></td>
                </tr>

                <tr> 
                    <td> <input type="submit" value="Make payment"/></td>
                </tr>
            </table>
        </form>
        
        <form method="link" action="LogoutServlet.do">
            <input type="submit" name="Dash" value="Dash"/>
        </form>
        <button onclick="history.go(-2)"> 
            Click here to go back 
        </button> 

    </body>
</html>
