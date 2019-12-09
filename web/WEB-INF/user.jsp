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
        /* Style the body */
        body {
            font-family: Arial;
            margin: 0;
        }
        /* The container */
        .container {
            display: block;
            position: relative;
            padding-left: 35px;
            margin-bottom: 12px;
            cursor: pointer;
            font-size: 22px;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        /* Hide the browser's default checkbox */
        .container input {
            position: absolute;
            opacity: 0;
            cursor: pointer;
            height: 0;
            width: 0;
        }

        /* Create a custom checkbox */
        .checkmark {
            position: absolute;
            top: 0;
            left: 0;
            height: 25px;
            width: 25px;
            background-color: #eee;
        }

        /* On mouse-over, add a grey background color */
        .container:hover input ~ .checkmark {
            background-color: #ccc;
        }

        /* When the checkbox is checked, add a blue background */
        .container input:checked ~ .checkmark {
            background-color: #2196F3;
        }

        /* Create the checkmark/indicator (hidden when not checked) */
        .checkmark:after {
            content: "";
            position: absolute;
            display: none;
        }

        /* Show the checkmark when checked */
        .container input:checked ~ .checkmark:after {
            display: block;
        }

        /* Style the checkmark/indicator */
        .container .checkmark:after {
            left: 9px;
            top: 5px;
            width: 5px;
            height: 10px;
            border: solid white;
            border-width: 0 3px 3px 0;
            -webkit-transform: rotate(45deg);
            -ms-transform: rotate(45deg);
            transform: rotate(45deg);
        }
        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 16px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            -webkit-transition-duration: 0.4s; /* Safari */
            transition-duration: 0.4s;
            cursor: pointer;
        }
        .button1 {
            background-color: white; 
            color: black; 
            border: 2px solid #4CAF50;
        }

        .button1:hover {
            background-color: #4CAF50;
            color: white;
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
                <td><input class="container" type="text" name="username"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input class="container" type="password" name="password"/></td>
            </tr>
            <tr> 
                <td> <input class="button button1" type="submit" value="<%=str%>"/></td>
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
        <button class="button button1">Go back</button>
    </form>
</body>

</html>
