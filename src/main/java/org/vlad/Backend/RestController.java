package org.vlad.Backend;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.vlad.Backend.app.ParseLibrary;
import org.vlad.Backend.app.PlaylistHandler;
import org.vlad.Backend.dao.ServiceDAO;
import org.vlad.Backend.entities.Playlist;
import org.vlad.Backend.entities.Track;
import org.vlad.Backend.entities.User;



@Path("/rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestController {		
	  
	@POST
	@Path("/saveUser")
	public Response returnPlaylist(User user) {		
		PlaylistHandler playlistHandler = new PlaylistHandler();
		
		User usr = playlistHandler.saveUser(user);
		return Response.status(200).entity(usr).build();	
	}	
	  
	@POST
	@Path("/login")
	public Response login(User user) {
	
		ServiceDAO serviceDAO = new ServiceDAO();
		User usr = (User) serviceDAO.getOne("User.findByUsername", "username", user.getUsername());
		
		if(usr == null) 
			return Response.status(404).entity("NOT_FOUND").build();				
		
		if(usr.getPassword().equals(user.getPassword())) 
			return Response.status(200).entity(usr).build();	
		else 
			return Response.status(404).entity("INCORECT_PASSWORD").build();			
	}
	
	@POST
	@Path("/updateTrack")
	public Response updateTrack(Track trackToEdit) {
		
		ServiceDAO serviceDAO = new ServiceDAO();

		return Response.status(200).entity(serviceDAO.update(trackToEdit)).build();
	}
	
	@DELETE
	@Path("/deleteTrack")
	public Response deleteTrack(Track trackToDelete) {
		ServiceDAO serviceDAO = new ServiceDAO();

		return Response.status(200).entity(serviceDAO.delete(trackToDelete)).build();
	}
	

	@GET
	@Path("/viewPlaylist")
	public Response getAllPlaylist(@QueryParam("username") String username) {
		
		ServiceDAO serviceDAO = new ServiceDAO();			 
		User usr = (User) serviceDAO.getOne("User.findByUsername", "username", username);

		return Response.status(200).entity(usr.getPlaylist()).build();
		
	}
	
	@POST
	@Path("/deletePlaylist")
	public Response removePlaylist(@QueryParam ("username") String  username, @QueryParam("playlistName") String playlistName) {
		ServiceDAO serviceDAO = new ServiceDAO();
		
		User user = (User) serviceDAO.getOne("User.findByUsername", "username", username);
		
		if(user != null) {
			List<Playlist> playlists = user.getPlaylist();
			
			for(Playlist p : playlists) {
				if(p.getName().equals("playlistName")) {
					serviceDAO.delete(p);
					User usr = (User) serviceDAO.update(user);
					return Response.status(200).entity(usr).build();
					
				}else 
					return Response.status(200).entity("PLAYLIST NOT FOUND!").build();
			}
		}
		return Response.status(200).entity("ERROR!").build();
	}
	
	@POST
	@Path("/updateUser")
	public Response updateUserPlaylists(@QueryParam("username") String username, @QueryParam("playlistName") String playlistName) {
		ParseLibrary parseLibrary = new ParseLibrary();
		Playlist playlist = new Playlist();
		ServiceDAO serviceDAO = new ServiceDAO();
		
		char number = playlistName.charAt(playlistName.length()-1);
		
		User user = (User) serviceDAO.getOne("User.findByUsername", "username", username);
		
		if(user != null) {
			List<Playlist> playlists = user.getPlaylist();
			playlist.setTrackList(parseLibrary.parseTracks("C:\\Users\\Vlad\\eclipse-workspace\\Backend\\src\\main\\resources\\iTunes Music Library"+number+".xml"));
			playlists.add(playlist);
			user.setPlaylist(playlists);
			User usr = (User) serviceDAO.update(user);
			return Response.status(200).entity(usr).build();

		}else 
			return Response.status(200).entity("NO SUCH USER FOUND!").build();
	}		
}
