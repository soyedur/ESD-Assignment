<%-- 
    Document   : deactivatedPage
    Created on : 22-Nov-2019, 15:39:49
    Author     : k2-banh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Page Title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

    </head>
    <body>
        <script>
            function Timeout() {
                setTimeout(function () {
                    index.jsp
                }, 3000);
            }
        </script>

        <div class="header">
            <h1>Account deactivated</h1>
        </div>

        <form method="POST" action="MemberServlet.do">
            <div class="main">
                <h2>TITLE HEADING</h2>
                <p>Reactivate your account<input type="radio" name="rdi" value ="reactivate"</p>
                <input type="submit"/>
            </div>
        </form>
        <form method="link" action="LogoutServlet.do">
            <input type="submit" name="LogoutBtn" value="Logout"/>
        </form>
    </body>
