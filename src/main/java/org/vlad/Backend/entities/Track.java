package org.vlad.Backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    
    @NamedQuery(name = "Track.findByID", query = "select t from Track t where t.id = :id"),
    
})

@Entity
public class Track {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private int trackID;

    private String name;
    private String artist;
    private String composer;
    private String album;
    private String genre;
    private String kind;
    private String sortAlbum;

    public Track(){

    }

    public Track(int trackID, String name, String artist, String composer, String album, String genre, String kind, String sortAlbum) {
       this.trackID = trackID;
        this.name = name;
        this.artist = artist;
        this.composer = composer;
        this.album = album;
        this.genre = genre;
        this.kind = kind;
        this.sortAlbum = sortAlbum;
    }
    
    

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSortAlbum() {
        return sortAlbum;
    }

    public void setSortAlbum(String sortAlbum) {
        this.sortAlbum = sortAlbum;
    }

	@Override
	public String toString() {
		return "\nTrack [trackID=" + trackID + ", name=" + name + ", artist=" + artist + ", composer=" + composer
				+ ", album=" + album + ", genre=" + genre + ", kind=" + kind + ", sortAlbum=" + sortAlbum + "]";
	}
    
    
}
