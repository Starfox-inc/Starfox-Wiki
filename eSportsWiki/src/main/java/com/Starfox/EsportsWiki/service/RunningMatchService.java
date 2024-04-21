package com.Starfox.EsportsWiki.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Starfox.EsportsWiki.dto.RunningMatchDto;
import com.Starfox.EsportsWiki.model.RunningMatchList;
import com.Starfox.EsportsWiki.repository.RunningMatchRepo;
import com.google.gson.Gson;

import integrationAPI.Game;
import integrationAPI.League;
import integrationAPI.StreamsList;

@Service
public class RunningMatchService {

    private final RunningMatchRepo runningMatchRepo;
    
    @Autowired
    public RunningMatchService(RunningMatchRepo runningMatchRepo){
        this.runningMatchRepo = runningMatchRepo;
    }

    public List<RunningMatchDto> findAll(){
        return runningMatchRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public RunningMatchDto getRunningMatchDtoById(int id){
        RunningMatchList runningMatchList = runningMatchRepo.findFirstById(id);
        return convertEntityToDto(runningMatchList);
    }


    public RunningMatchDto convertEntityToDto(RunningMatchList runningMatch){
        Gson gson = new Gson();
        


        RunningMatchDto runningMatchDto = new RunningMatchDto();
        runningMatchDto.setId(runningMatch.getId());
        runningMatchDto.setBegin_at(runningMatch.getBegin_at());
        runningMatchDto.setName(runningMatch.getName());
        runningMatchDto.setLeagueId(runningMatch.getLeagueID());
        runningMatchDto.setLeagueName(  gson.fromJson(runningMatch.getLeague(), League.class).getName() );
        runningMatchDto.setMatchType(runningMatch.getMatchType());
        runningMatchDto.setNumberOfGames(runningMatch.getNumberOfGames());
        runningMatchDto.setGames(gson.fromJson(runningMatch.getGames(), Game[].class));
        runningMatchDto.setStreamList(gson.fromJson(runningMatch.getStreamList(), StreamsList[].class));
        
        return runningMatchDto;
    }


    
}
