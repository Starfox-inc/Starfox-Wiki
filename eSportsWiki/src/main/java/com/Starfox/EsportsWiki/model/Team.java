package com.Starfox.EsportsWiki.model;

import java.util.List;


public class Team{
    private String acronym;
    private CurrentVideoGame current_videogame;
    private int id;
    private String image_url;
    private String location;
    private String name;

    private List<Player> players;
    private String slug;

    // Constructor
    public Team(String acronym, CurrentVideoGame current_videogame, int id, String image_url, String location, String name, String slug) {
        this.acronym = acronym;
        this.current_videogame = current_videogame;
        this.id = id;
        this.image_url = image_url;
        this.location = location;
        this.name = name;
        this.slug = slug;
//        this.players = players; // Set the list of players
    }

    // Getters
    public String getAcronym() {
        return acronym;
    }

    public int getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getLocation() {
        return location;
    }


    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public CurrentVideoGame getCurrent_videogame() {
        return current_videogame;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public void setCurrent_videogame(CurrentVideoGame current_videogame) {
        this.current_videogame = current_videogame;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
