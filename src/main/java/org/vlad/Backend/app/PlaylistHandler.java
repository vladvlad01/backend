package org.vlad.Backend.app;

import org.vlad.Backend.entities.User;

import java.util.List;
import java.util.ArrayList;
import org.vlad.Backend.dao.ServiceDAO;
import org.vlad.Backend.entities.Playlist;

public class PlaylistHandler {
		
	ParseLibrary parseLibrary = new ParseLibrary();

	ServiceDAO serviceDAO = new ServiceDAO();
	
	public User saveUser(User user) {
		List<Playlist> playlists = new ArrayList<>();
		Playlist playlist1 = new Playlist();
		Playlist playlist2 = new Playlist();
		Playlist playlist3 = new Playlist();
		
		try {			
			
			playlist1.setName("Playlist 1");
			playlist1.setTrackList(parseLibrary.parseTracks("C:\\Users\\Vlad\\eclipse-workspace\\Backend\\src\\main\\resources\\iTunes Music Library1.xml"));
			
			playlist2.setName("Playlist 2");
			playlist2.setTrackList(parseLibrary.parseTracks("C:\\Users\\Vlad\\eclipse-workspace\\Backend\\src\\main\\resources\\iTunes Music Library2.xml"));
			
			playlist3.setName("Playlist 3");
			playlist3.setTrackList(parseLibrary.parseTracks("C:\\Users\\Vlad\\eclipse-workspace\\Backend\\src\\main\\resources\\iTunes Music Library3.xml"));
			
			playlists.add(playlist1);
			playlists.add(playlist2);							
			playlists.add(playlist3);
			
			user.setPlaylist(playlists);
			
			
			User usr = (User) serviceDAO.save(user);	
			return usr;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

}
