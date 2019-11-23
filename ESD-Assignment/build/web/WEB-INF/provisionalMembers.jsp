<%-- 
    Document   : provisionalMembers
    Created on : 20-Nov-2019, 15:25:37
    Author     : Kvzary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DB Results</title>
    </head>
    <body>
        <h1>The retrieved Data :</h1>
        
        <%=(String) (request.getAttribute("queryProvMem"))%>
        
        
        <button onclick="history.go(-1)"> 
            Click here to go back 
        </button> 
        <!--jsp:include page="foot.jsp"/-->
    </body>
</html>