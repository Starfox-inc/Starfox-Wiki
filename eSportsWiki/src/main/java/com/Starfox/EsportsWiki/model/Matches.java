package com.Starfox.EsportsWiki.model;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Matches implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="begin_at")
    private String begin_at;
    @Column(name ="detailed_stats")
    private boolean detailed_stats;
    @Column(name ="draw")
    private boolean draw;
    @Column(name ="end_at")
    private String end_at;
    @Column(name ="forfeit")
    private boolean forfeit;

    //List of Game objects in Json String
    @Column(name = "games")
    private String games;


    //League Object in Json String
    @Column(name = "league")
    private String league;  

    @Column(name = "league_id")
    private Integer leagueID;

    //Live Object in Json String
    @Column(name = "live")
    private String livedata;

    @Column(name = "match_type")
    private String matchType;
    @Column(name = "name")
    private String name;
    @Column(name = "number_of_games")
    private Integer numberOfGames;

    //Serie object in Json String
    @Column(name = "serie")
    private String serie;
    
    @Column(name = "serie_id")
    private Integer serieID;
    
    //List of StreamList object in Json String
    @Column(name = "streams_list")
    private String streamList;

    //Tournament object in Json String
    @Column(name = "tournament")
    private String tournament;

    @Column(name = "tournament_id")
    private Integer tournament_id;

    //CurrentVideogame object in Json String
    @Column(name = "videogame")
    private String videogame;
    
    @Column(name = "game_advantage")
    private Integer game_advantage;
}

