<%-- 
    Document   : MembershipForm
    Created on : 16-Nov-2019, 13:32:11
    Author     : Kvzary
--%>
</script>
<%@page import="java.text.SimpleDateFormat"%>  
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
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
<title>Register</title>
<form method="POST" action="NewUser.do">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>

        <label for="Name"><b>Name</b></label>
        <input type="text" placeholder="Enter Forename" name="Forename" required>
        
        <input type="text" placeholder="Enter Surname" name="Surname" required>

        <label for="address"><b>Address</b></label>
        <input id="searchTextField" type="text" placeholder="2 Example Road" name="address" required>

        <label for="DOB"><b>Date of Birth</b></label>
        <input type="number" placeholder="dd" name="day" required pattern="[0-9]*">
        <input type="number" placeholder="mm" name="month" required pattern="[0-9]*">
        <input type="number" placeholder="yyyy" name="year" required pattern="[0-9]*">
        
        <%  Date date = new java.sql.Date(new Date().getTime());%>
        <label for="DOR"><b>Date of Registration</b></label>
        <input type="date" name="pInDate" value="<%= date.toString()%>"name="DOR" readonly/>     
        
        <button type="submit" class="button1">Register</button>
    </div>
<script>

function initAutocomplete() {
  // Create the autocomplete object, restricting the search predictions to
  // geographical location types.
  autocomplete = new google.maps.places.Autocomplete(
      document.getElementById('searchTextField'), {types: ['geocode']});
}
</script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMFgrROSfp5LFRZ9dxO5EwPUGLub88wSc&libraries=places&callback=initAutocomplete"
        async defer></script>
  </body>
</form>    
    <%-- start web service invocation --%><hr/>
    <%
    try {
	org.me.addresslookup.AddressLookupWS_Service service = new org.me.addresslookup.AddressLookupWS_Service();
	org.me.addresslookup.AddressLookupWS port = service.getAddressLookupWSPort();
	// TODO initialize WS operation arguments here
	String address = (String)request.getParameter("address");
	// TODO process result here
	String result = port.addressLookup(address);
	out.println("Address = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>