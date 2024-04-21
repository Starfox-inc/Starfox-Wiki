package com.Starfox.EsportsWiki.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
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

}

