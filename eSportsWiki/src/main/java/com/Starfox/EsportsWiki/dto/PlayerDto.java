package com.Starfox.EsportsWiki.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerDto{
    int playerId;
    String name;
    Integer teamId;
    boolean active;
    String currentGame;

    @Override
    public String toString(){
        return playerId + " " + name + " " + teamId + " " + active + " " + currentGame;
    }
}


