package com.Starfox.EsportsWiki.model;

import java.io.Serializable;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "teaminfo")
public class CurrentTeam implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamID")
    private Integer id;
    @Column(name = "acronym")
    private String acronym;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "location")
    private String location;
    @Column(name = "name")
    private String name;
    @Column(name = "slug")
    private String slug;
    @ManyToOne
    @JoinColumn(name = "current_videogame_id")
    private CurrentVideoGame current_videogame_id;
    @Column(name = "current_videogame_name")
    private String current_videogame_name;
    @Column(name = "current_videogame_slug")
    private String current_videogame_slug;

    public CurrentTeam() {

    }

/*
    // Getters
    public String getAcronym(){
        return acronym;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public String getLocation(){
        return location;
    }
    */
    public Integer gettId() {
        return Optional.ofNullable(id).orElse(0);
    }
/*
    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void setAcronym(String acronym){
        this.acronym = acronym;
    }
    */
    public CurrentTeam getCurrent_videoGame() {
        return this;
    }


    public CurrentTeam(String acronym, Integer id, String imageUrl, String location, String name, String slug, CurrentVideoGame currVG, String currVGN, String currVGS) {
        this.acronym = acronym;
        this.id = id;
        this.imageUrl = imageUrl;
        this.location = location;
        this.name = name;
        this.slug = slug;
        this.current_videogame_id = currVG;
        this.current_videogame_name = currVGN;
        this.current_videogame_slug = currVGS;
    }

    public CurrentTeam(String acronym, Integer id, String imageUrl, String location, String name, String slug) {
        this.acronym = acronym;
        this.id = id;
        this.imageUrl = imageUrl;
        this.location = location;
        this.name = name;
        this.slug = slug;
    }
    public static CurrentTeam parseCurrentTeam(JSONObject teamObject) throws JSONException {
        return new CurrentTeam(
                teamObject.optString("acronym"),
                teamObject.optInt("id"),
                teamObject.optString("image_url"),
                teamObject.optString("location"),
                teamObject.getString("name"),
                teamObject.getString("slug")
        );
    }
}

