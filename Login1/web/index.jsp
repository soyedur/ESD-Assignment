<%-- 
    Document   : index
    Created on : 09-Mar-2016, 16:52:19
    Author     : me-aydin
--%>

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
    <body>
        <h1><center>This is expected to serve as a proper Web Page</center></h1>
        <form method="POST" action="UserService.do">
            <label class="container">
                <input type="radio" name="tbl" value="Admin">Admin<br />
                <span class="checkmark"></span>
            </label>
            <label class="container">
                <input type="radio" name="tbl" value="Member">Member<br />
                <span class="checkmark"></span>
            </label>
            <label class="container">
                <input type="radio" name="tbl" value="List">List Users<br />
                <span class="checkmark"></span>
            </label>
            <label class="container">
                <input type="radio" name="tbl" value="NewUser">New User<br />
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
            <button class="button button1">Submit</button>
            <input type=submit value="Action"> <br />
    </body>
</html>
