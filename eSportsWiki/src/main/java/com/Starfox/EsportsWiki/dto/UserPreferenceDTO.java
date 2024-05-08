package com.Starfox.EsportsWiki.dto;

public class UserPreferenceDTO {
    private int userId;
    private boolean liveData;
    private boolean teamData;
    private boolean playerData;
    
    public UserPreferenceDTO() {
    }
    
    public UserPreferenceDTO(int userId, boolean liveData, boolean teamData, boolean playerData) {
        this.userId = userId;
        this.liveData = liveData;
        this.teamData = teamData;
        this.playerData = playerData;
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