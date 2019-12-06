<%@page import="org.vlad.Backend.modelDTO.TrackDTO"%>
<%@page import="org.vlad.Backend.modelDTO.PlaylistDTO"%>
<%@page import="org.vlad.Backend.modelDTO.UserDTO"%>
<%@page import="com.google.gson.Gson"%>
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
		
		
		String serialized = RestController.getFromURL("http://localhost:8080/WebDevAssignment/webapi/rest/login/" + username + "/" + password);
		
		if (serialized.contains("invalid user")) {
	out.println(serialized);
	return;
		}
		
		UserDTO user = new Gson().fromJson(serialized, UserDTO.class);
		
		out.println("=======================");
		out.println("UserName: " + user.getUsername());
		out.println("Password: " + user.getPassword());
		for (PlaylistDTO playlist : user.getPlaylist()) {
	for(TrackDTO track : playlist.getTrackList()) {
		out.println("Track " + track.getName() + " by " + track.getArtist());
	}
		}
		out.println("=======================");
		
		return;
	}
%>
<body>
    <h2>Login!</h2>

    <form action="" method="post">  
		Username:<input type="text" name="username"/><br/><br/>  
		Password:<input type="password" name="password"/><br/><br/>  
		<input type="submit" value="register"/>"  
	</form>  



    
    
</body>
</html>
