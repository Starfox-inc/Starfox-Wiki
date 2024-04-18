package com.Starfox.EsportsWiki.model;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "current_videogame")
public class CurrentVideoGame implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "slug")
    private String slug;

    // Constructor
    public CurrentVideoGame(Integer id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    public CurrentVideoGame() {
        this.id = null;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    // Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    // Setters
    public void setId() {
        this.id = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public CurrentVideoGame getCurrent_videoGame() {
        return this;
    }


    public static CurrentVideoGame parseCurrentGame(JSONObject gameObject) throws JSONException {
        return new CurrentVideoGame(
                gameObject.getInt("id"),
                gameObject.getString("name"),
                gameObject.getString("slug")
        );
    }

}
