<%-- 
    Document   : memDetails
    Created on : 16-Nov-2019, 00:45:36
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
        <h1>Member Details</h1>
        <%=(String) (request.getAttribute("queryMem"))%>
        
        <button onclick="history.go(-1)"> 
            Click here to go back 
        </button> 
    </body>
</html>
