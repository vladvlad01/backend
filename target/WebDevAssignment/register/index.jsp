<%@page import="org.vlad.Backend.RestController"%>
<%@page import="java.net.URL"%>
<html>
<%
	if (request.getMethod().equalsIgnoreCase("POST")) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (username == null || password == null) {
	out.println("INVALID!");
	return;
		}
		
		
		out.println(RestController.getFromURL("http://localhost:8080/WebDevAssignment/webapi/rest/register/" + username + "/" + password));
		
		return;
	}
%>
<body>
    <h2>Register!</h2>

    <form action="" method="post">  
		Username:<input type="text" name="username"/><br/><br/>  
		Password:<input type="password" name="password"/><br/><br/>  
		<input type="submit" value="register"/>"  
	</form>  



    
    
</body>
</html>
