package com.Starfox.EsportsWiki.dto;

import integrationAPI.Game;
import integrationAPI.StreamsList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RunningMatchDto {
 
    Integer id;
    String name;
    String begin_at;
    String leagueName;
    Integer leagueId;
    String matchType;
    Integer numberOfGames;
    Game[] games;
    StreamsList[] streamList;

}
