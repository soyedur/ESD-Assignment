<%-- 
    Document   : driver
    Created on : 01-Nov-2015, 15:18:08
    Author     : me-aydin
--%>

<%@page import="model.Jdbc"%>
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
<body>

    <h2>Responsive Social Login Form</h2>
    <h3><center>User's details:</center></h3>
            <%! int i = 0;
                String str = "Register";
                String url = "NewUser.do";
            %>
            <%
                if ((String) request.getAttribute("msg") == "del") {
                    str = "Delete";
                    url = "Delete.do";
                } else if ((String) request.getAttribute("msg") == "Admin") {
                    str = "Login";
                    url = "Login.do";
                } else if ((String) request.getAttribute("msg") == "Member") {
                    str = "Login";
                    url = "MLogin.do";
                } else if ((String) request.getAttribute("msg") == "Membership"){
                    str = "Process";
                    url = "Membership.do";
                } else {
                    str = "Register";
                    url = "NewUser.do";
                }
            %>
    <form method="POST" action="<%=url%>">     
        <table>
            <tr>
                <th></th>
                <th><center>Please provide your following details</center></th>
            </tr>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr> 
                <td> <input type="submit" value="<%=str%>"/></td>
            </tr>
        </table>
    </form>   
    <%
        if (i++ > 0 && request.getAttribute("message") != null) {
            out.println(request.getAttribute("message"));
            i--;
        }
    %>
    </br>
    <!--jsp:include page="foot.jsp"/-->
    <form method="link" action="index.jsp">
        <input type="submit" value="Go back"/>
    </form>
</body>

</html>
