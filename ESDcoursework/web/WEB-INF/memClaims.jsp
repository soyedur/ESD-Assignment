<%-- 
    Document   : memCliams
    Created on : 16-Nov-2019, 01:07:39
    Author     : Soyedur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box;
        }

        /* style the container */
        .container {
            position: relative;
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px 0 30px 0;
        } 

        /* style inputs and link buttons */
        input,
        .btn {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 4px;
            margin: 5px 0;
            opacity: 0.85;
            display: inline-block;
            font-size: 17px;
            line-height: 20px;
            text-decoration: none; /* remove underline from anchors */
        }

        input:hover,
        .btn:hover {
            opacity: 1;
        }

        /* add appropriate colors to fb, twitter and google buttons */
        .fb {
            background-color: #3B5998;
            color: white;
        }

        .twitter {
            background-color: #55ACEE;
            color: white;
        }

        .google {
            background-color: #dd4b39;
            color: white;
        }

        /* style the submit button */
        input[type=submit] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        /* Two-column layout */
        .col {
            float: left;
            width: 50%;
            margin: auto;
            padding: 0 50px;
            margin-top: 6px;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }

        /* vertical line */
        .vl {
            position: absolute;
            left: 50%;
            transform: translate(-50%);
            border: 2px solid #ddd;
            height: 175px;
        }

        /* text inside the vertical line */
        .vl-innertext {
            position: absolute;
            top: 50%;
            transform: translate(-50%, -50%);
            background-color: #f1f1f1;
            border: 1px solid #ccc;
            border-radius: 50%;
            padding: 8px 10px;
        }

        /* hide some text on medium and large screens */
        .hide-md-lg {
            display: none;
        }

        /* bottom container */
        .bottom-container {
            text-align: center;
            background-color: #666;
            border-radius: 0px 0px 4px 4px;
        }

        /* Responsive layout - when the screen is less than 650px wide, make the two columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 650px) {
            .col {
                width: 100%;
                margin-top: 0;
            }
            /* hide the vertical line */
            .vl {
                display: none;
            }
            /* show the hidden text on small screens */
            .hide-md-lg {
                display: block;
                text-align: center;
            }
        }
    </style>
</head>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Member Payments</h1>

        <%! int i = 0;%>

        <% if (pages.AdminLogin.astatus.equals("ADMIN")) {%>
        <%=(String) (request.getAttribute("queryClaims"))%>
        <button onclick="history.go(-1)"> 
            Click here to go back 
        </button> 
        <%
        } else {
        %>
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
                    <td> <input type="submit" value="Submit Claim"/></td>
                </tr>
            </table>
        </form>
        <button onclick="history.go(-2)"> 
            Click here to go back 
        </button> 
        <%
            }
        %>
    </body>
</html>
