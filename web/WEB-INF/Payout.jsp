<%-- 
    Document   : Payout
    Created on : 07-Dec-2019, 17:19:05
    Author     : k2-banh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payout</title>
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

        <%=(String) (session.getAttribute("queryPayout"))%>
        <%=(String) (session.getAttribute("queryPayoutClaimers"))%>
        <form method="POST" action="Payout.do"> 
            <div class="button">
                <input type="submit" value="Send out payment"/>
            </div>
        </form>
        <form method="link" action="backServlet.do">
            <input type="submit" name="BackBtn" value="Dashboard"/>
        </form>
    </body>
</html>

    <%-- start web service invocation --%><hr/>
    <%
    try {
	org.me.calculatepayout.CalculatePayoutWS_Service service = new org.me.calculatepayout.CalculatePayoutWS_Service();
	org.me.calculatepayout.CalculatePayoutWS port = service.getCalculatePayoutWSPort();
	 // TODO initialize WS operation arguments here
	double sumOfClaims = (Double)(session.getAttribute("sumOfClaims"));
	double numberOfClaimers = (Double)(session.getAttribute("numberOfClaimers"));
	// TODO process result here
	double calculatedPayout = port.calculatePayout(sumOfClaims, numberOfClaimers);
	out.println("Calculated Payout = "+calculatedPayout);
    } catch (Exception ex) {
	out.println("No outstanding balance!");
    }
    %>
    <%-- end web service invocation --%><hr/>
