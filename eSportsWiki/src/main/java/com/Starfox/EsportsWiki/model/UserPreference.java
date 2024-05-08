package com.Starfox.EsportsWiki.model;

public class UserPreference {
    private int id;
    private int userId;
    private boolean liveData;
    private boolean teamData;
    private boolean playerData;
    
    public UserPreference() {
    }
    
    public UserPreference(int userId, boolean liveData, boolean teamData, boolean playerData) {
        this.userId = userId;
        this.liveData = liveData;
        this.teamData = teamData;
        this.playerData = playerData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isLiveData() {
        return liveData;
    }

    public void setLiveData(boolean liveData) {
        this.liveData = liveData;
    }

    public boolean isTeamData() {
        return teamData;
    }

    public void setTeamData(boolean teamData) {
        this.teamData = teamData;
    }

    public boolean isPlayerData() {
        return playerData;
    }

    public void setPlayerData(boolean playerData) {
        this.playerData = playerData;
    }
}