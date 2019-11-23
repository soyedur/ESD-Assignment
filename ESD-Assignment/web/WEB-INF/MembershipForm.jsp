<%-- 
    Document   : MembershipForm
    Created on : 16-Nov-2019, 13:32:11
    Author     : Kvzary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<form method="POST" action="NewUser.do">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="Name"><b>Name</b></label>
        <input type="text" placeholder="Enter name" name="name" required>

        <label for="address"><b>Address</b></label>
        <input type="email" placeholder="Enter address" name="address" required>

        <label for="DOB"><b>Date of Birth</b></label>
        <input type="date" placeholder="Enter date of birth" name="DOB" required>
        
        
        
        <label for="DOR"><b>Date of Registration</b></label>
        <input type="date" value=<%= new java.util.Date()%> name="DOR" readonly>
        <hr>
        
        <p>Date/Time: <span id="datetime"></span></p>

        <script>
            var dt = new Date();
            document.getElementById("datetime").innerHTML = dt.toLocaleDateString();
        </script>
        <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>
        <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin">
        <p>Already have an account? <a href="#">Sign in</a>.</p>
    </div>
</form> 
