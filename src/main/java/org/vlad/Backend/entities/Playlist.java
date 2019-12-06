package org.vlad.Backend.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@NamedQueries({
    
    @NamedQuery(name = "Playlist.findByID", query = "select p from Playlist p where p.id = :id"), 
    @NamedQuery(name = "Playlist.findByName", query = "select p from Playlist p where p.name = :name"),
    @NamedQuery(name = "Playlist.deleteAll", query = "delete from Playlist p "),
    @NamedQuery(name = "Playlist.getAll", query = "select p from Playlist p"),

})

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private List<Track> trackList = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(String name, List<Track> trackList){
        this.name = name;

        this.trackList = trackList;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Track> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<Track> trackList) {
		this.trackList = trackList;
	}

    

    
    
}
