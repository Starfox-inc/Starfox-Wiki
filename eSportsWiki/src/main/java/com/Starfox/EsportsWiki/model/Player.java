package com.Starfox.EsportsWiki.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "playerlist")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerId;
    @Column(name = "active")
    private boolean active;
    @Column(name = "age")
    private Integer age; // Change to Integer to handle null values
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "image_url")
    private String image_url;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "name")
    private String name;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "role")
    private String role;
    @Column(name = "slug")
    private String slug;
    @Column(name = "teaminfo_teamID")
    private CurrentTeam teaminfo;

    @Column(name = "current_videogame_gameID")
    private CurrentVideoGame currentGame;

    public Player(){
        
    }    
    public Player(boolean active, Integer age, String first_name, int id, String image_url, String last_name, String name, String nationality, String role, String slug, CurrentTeam teaminfo, CurrentVideoGame currentGame) {
        this.active = active;
        this.age = age;
        this.first_name = first_name;
        this.playerId = id;
        this.image_url = image_url;
        this.last_name = last_name;
        this.name = name;
        this.nationality = nationality;
        this.role = role;
        this.slug = slug;
        this.teaminfo = teaminfo;
        this.currentGame = currentGame;
    }


    /*
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int id) {
        this.playerId = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public CurrentTeam getTeamInfo() {
        return teaminfo;
    }

    public void setTeamInfo(CurrentTeam teamInfo) {
        this.teaminfo = teamInfo;
    }

    public CurrentVideoGame getCurrentVideogame() {
        return currentGame;
    }

    public void setCurrentGame(CurrentVideoGame currentGame) {
        this.currentGame = currentGame;
    }

    public CurrentTeam getTeaminfo() {
        return teaminfo;
    }

    public void setTeaminfo(CurrentTeam teaminfo) {
        this.teaminfo = teaminfo;
    }
 */

    
    

}