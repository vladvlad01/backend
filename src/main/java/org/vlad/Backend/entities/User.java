package org.vlad.Backend.entities;

import javax.persistence.*;

import java.util.List;

@NamedQueries({
    @NamedQuery(name = "User.findByUsername", query = "select u from User u where u.username = :username"), 
    @NamedQuery(name = "User.findById", query = "select u from User u where u.id = :id"),
})


@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private List<Playlist> playlist;

    public User(){
    }

    public User(String username, String password) {		
		this.username = username;
		this.password = password;
	}



	public User(String username, String password, List<Playlist> playlist) {
        
        this.username = username;
        this.password = password;

        this.playlist = playlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Playlist> playlist) {
        this.playlist = playlist;
    }
}
