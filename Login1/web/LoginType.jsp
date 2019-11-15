<%-- 
    Document   : LoginType
    Created on : 13-Nov-2019, 15:59:08
    Author     : Kvzary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In</title>
    </head>
    <body>
        <h1>Log In</h1>
        <form method="POST" action="UserService.do">
        <p />
            View a table <br />
            <input type="radio" name="tbl" value="LogIn">Login as Admin<br />
            <input type="radio" name="tbl" value="Member">Login as Member<br />
            <input type="radio" name="tbl" value="Register">New User<br />
            <input type=submit value="Action"> <br />
        </form> 
    </body>
</html>
