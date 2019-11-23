<%-- 
    Document   : suspendedPage
    Created on : 20-Nov-2019, 15:15:04
    Author     : Kvzary
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
            <h1>Suspended Page</h1>
        </div>



        <div class="main">
            <h2>TITLE HEADING</h2>
            <p>Reapply for membership<input type="submit" action="Payment.do"></p>
        </div>

        <form method="link" action="LogoutServlet.do">
            <input type="submit" name="LogoutBtn" value="Logout"/>
        </form>
    </body>
