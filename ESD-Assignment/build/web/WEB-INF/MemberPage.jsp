<%-- 
    Document   : MemberPage
    Created on : 14-Nov-2019, 16:42:44
    Author     : Kvzary
--%>
<%@page import="java.sql.Connection"%>
<%@ page import = "model.Jdbc" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Page Title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            * {
                box-sizing: border-box;
            }

            /* Style the body */
            body {
                font-family: Arial, Helvetica, sans-serif;
                margin: 0;
            }

            /* Header/logo Title */
            .header {
                padding: 80px;
                text-align: center;
                background: #1abc9c;
                color: white;
            }

            /* Increase the font size of the heading */
            .header h1 {
                font-size: 40px;
            }

            /* Sticky navbar - toggles between relative and fixed, depending on the scroll position. It is positioned relative until a given offset position is met in the viewport - then it "sticks" in place (like position:fixed). The sticky value is not supported in IE or Edge 15 and earlier versions. However, for these versions the navbar will inherit default position */
            .navbar {
                overflow: hidden;
                background-color: #333;
                position: sticky;
                position: -webkit-sticky;
                top: 0;
            }

            /* Style the navigation bar links */
            .navbar a {
                float: left;
                display: block;
                color: white;
                text-align: center;
                padding: 14px 20px;
                text-decoration: none;
            }


            /* Right-aligned link */
            .navbar a.right {
                float: right;
            }

            /* Change color on hover */
            .navbar a:hover {
                background-color: #ddd;
                color: black;
            }

            /* Active/current link */
            .navbar a.active {
                background-color: #666;
                color: white;
            }

            /* Column container */
            .row {  
                display: -ms-flexbox; /* IE10 */
                display: flex;
                -ms-flex-wrap: wrap; /* IE10 */
                flex-wrap: wrap;
            }

            /* Create two unequal columns that sits next to each other */
            /* Sidebar/left column */
            .side {
                -ms-flex: 30%; /* IE10 */
                flex: 30%;
                background-color: #f1f1f1;
                padding: 20px;
            }

            /* Main column */
            .main {   
                -ms-flex: 70%; /* IE10 */
                flex: 70%;
                background-color: white;
                padding: 20px;
            }

            /* Fake image, just for this example */
            .fakeimg {
                background-color: #aaa;
                width: 100%;
                padding: 20px;
            }

            /* Footer */
            .footer {
                padding: 20px;
                text-align: center;
                background: #ddd;
            }
            .dashboard {
                padding: 20px;
                text-align: center;
                background: #ddd;
            }

            /* Responsive layout - when the screen is less than 700px wide, make the two columns stack on top of each other instead of next to each other */
            @media screen and (max-width: 700px) {
                .row {   
                    flex-direction: column;
                }
            }

            /* Responsive layout - when the screen is less than 400px wide, make the navigation links stack on top of each other instead of next to each other */
            @media screen and (max-width: 400px) {
                .navbar a {
                    float: none;
                    width: 100%;
                }
            }
        </style>
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
            <h1>Member Page</h1>
        </div>

        <div class="navbar">
            <a href="#" class="active">Home</a>
            <a href="#">Link</a>
            <a href="#">Link</a>
            <a href="#" class="right">Link</a>
        </div>

        <form method="POST" action="MemberServlet.do">
            <label class="container">
                <input type="radio" name="tbl" value="memPayments">Pay fee<br />
                <span class="checkmark"></span>
            </label>
            <label class="container">
                <input type="radio" name="tbl" value="memClaims">Make Claim<br />
                <span class="checkmark"></span>
            </label>
            <label class="container">
                <input type="radio" name="tbl" value="Update">Password Change<br />
                <span class="checkmark"></span>
            </label>
            <label class="container">
                <input type="radio" name="tbl" value="Delete">Delete a User<br />
                <span class="checkmark"></span>
            </label>
            <label class="container">
                <input type="radio" name="tbl" value="Membership">Apply for membership<br />
                <span class="checkmark"></span>
            </label>
            <button class="button button1">Submit</button>

        </form>
        <div class="dashboard">
        Balance:<input type="text" value=<%=(Double) (request.getAttribute("balance"))%> readonly><br/>
        <!--Make a claim:<input type="submit" action="Claim.do">!-->
        Claims:<textarea rows="4" cols="50" readonly><%=(String) (request.getAttribute("claim"))%></textarea><br/>
        Payments:<textarea rows="4" cols="50" readonly><%=(String) (request.getAttribute("payment"))%></textarea>
        </div>
        <form method="link" action="LogoutServlet.do">
            <input type="submit" name="LogoutBtn" value="Logout"/>
        </form>

    </body>