<%-- 
    Document   : turnoverReport
    Created on : 23-Nov-2019, 13:55:22
    Author     : d7-fitzgerald
--%>
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
                padding: 20px;
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
            .text {
                padding: 20px;
                text-align: center;
                font-size: 20px;
            }
            .result{
                margin: auto;
                padding: 70px 0;
                border: 3px solid green;
                text-align: center;
                width: 60%
            }
            .textBox{
                padding: 10px;
                text-align: center;
            }
            .button{
                padding:10px;
                text-align: center;
                align-content: flex-start;
            }
            .exit {
                padding:10px;
                position: fixed;
                bottom: 5px;
                right: 5px; 
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

        <div class="header">
            <h1>Turnover Report</h1> 
        </div>

        <div class="navbar">
            <a href="#" class="active"></a>
            <a href="#"></a>
            <a href="#"></a>
            <a href="#"></a>
            <a href="#"></a>
            <a href="#" class="right">Logout</a>
        </div> 
        <form method="POST" action="Turnover.do"> 
            <div class="text">
                <text>Please select which annum you wish to review</text><br/>
            </div>
            <div class="textBox">
                Year: <input type="text"  name="Year"/></input>
            </div>
            <div class="button">
                <input type="submit" value="View Report"/>
            </div>
            <div class="result">
               Total Fee Revenue:  <text><%=(String) (request.getAttribute("queryIncomeDetails"))%><%=(String) (request.getAttribute("queryIncome"))%></text><br/>
            </div>
            <div class ="result">
                Total Claims: <text><%=(String) (request.getAttribute("queryPayoutDetails"))%><%=(String) (request.getAttribute("queryPayout"))%></text><br/>
            </div>
        </form>
        <button class="exit" onclick="history.go(-1)"> 
            Click here to go back 
        </button> 
    </body>
</html>