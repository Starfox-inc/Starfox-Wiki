package com.Starfox.EsportsWiki.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Starfox.EsportsWiki.dto.MatchDto;
import com.Starfox.EsportsWiki.model.Matches;
import com.Starfox.EsportsWiki.model.RunningMatchList;
import com.Starfox.EsportsWiki.repository.CodMatchesRepo;
import com.Starfox.EsportsWiki.repository.CodPastMatchesRepo;
import com.Starfox.EsportsWiki.repository.CsMatchesRepo;
import com.Starfox.EsportsWiki.repository.CsPastMatchesRepo;
import com.Starfox.EsportsWiki.repository.LolMatchesRepo;
import com.Starfox.EsportsWiki.repository.LolPastMatchesRepo;
import com.Starfox.EsportsWiki.repository.RunningMatchRepo;
import com.Starfox.EsportsWiki.repository.ValMatchesRepo;
import com.Starfox.EsportsWiki.repository.ValPastMatchesRepo;
import com.google.gson.Gson;

import integrationAPI.Game;
import integrationAPI.League;
import integrationAPI.StreamsList;

@Service
public class MatchService {

    private final RunningMatchRepo runningMatchRepo;
    private final CodMatchesRepo codMatchesRepo;
    private final CodPastMatchesRepo codPastMatchesRepo;
    private final CsMatchesRepo csMatchesRepo;
    private final CsPastMatchesRepo csPastMatchesRepo;
    private final ValMatchesRepo valMatchesRepo;
    private final ValPastMatchesRepo valPastMatchesRepo;
    private final LolMatchesRepo lolMatchesRepo;
    private final LolPastMatchesRepo lolPastMatchesRepo;

    @Autowired
    public MatchService(RunningMatchRepo runningMatchRepo, CodMatchesRepo codMatchesRepo, CodPastMatchesRepo codPastMatchesRepo,
                    CsMatchesRepo csMatchesRepo, CsPastMatchesRepo csPastMatchesRepo, ValMatchesRepo valMatchesRepo,
                    ValPastMatchesRepo valPastMatchesRepo, LolMatchesRepo lolMatchesRepo, LolPastMatchesRepo lolPastMatchesRepo){
        this.runningMatchRepo = runningMatchRepo;
        this.codMatchesRepo = codMatchesRepo;
        this. codPastMatchesRepo = codPastMatchesRepo;
        this.csMatchesRepo = csMatchesRepo;
        this.csPastMatchesRepo = csPastMatchesRepo;
        this.valMatchesRepo = valMatchesRepo;
        this.valPastMatchesRepo = valPastMatchesRepo;
        this.lolMatchesRepo = lolMatchesRepo;
        this.lolPastMatchesRepo = lolPastMatchesRepo;
    }

    public List<MatchDto> findAllRunningMatches(){
        return runningMatchRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public MatchDto getRunningMatchDtoById(int id){
        RunningMatchList runningMatchList = runningMatchRepo.findFirstById(id);
        return convertEntityToDto(runningMatchList);
    }

    public List<MatchDto> findAllCsMatches(){
        return csMatchesRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public MatchDto getCsMatchDtoById(int id){
        return convertEntityToDto(csMatchesRepo.findFirstById(id));
    }

    public List<MatchDto> findAllCsPastMatches(){
        return csPastMatchesRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public MatchDto getCsPastMatchDtoById(int id){
        return convertEntityToDto(csPastMatchesRepo.findFirstById(id));
    }

    public List<MatchDto> findAllCodMatches(){
        return codMatchesRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public MatchDto getCodMatchDtoById(int id){
        return convertEntityToDto(codMatchesRepo.findFirstById(id));
    }

    public List<MatchDto> findAllCodPastMatches(){
        return codPastMatchesRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public MatchDto getCodPastMatchDtoById(int id){
        return convertEntityToDto(codPastMatchesRepo.findFirstById(id));
    }

    public List<MatchDto> findAllLolMatches(){
        return lolMatchesRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public MatchDto getLolMatchDtoById(int id){
        return convertEntityToDto(lolMatchesRepo.findFirstById(id));
    }

    public List<MatchDto> findAllLolPastMatches(){
        return lolPastMatchesRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public MatchDto getLolPastMatchDtoById(int id){
        return convertEntityToDto(lolPastMatchesRepo.findFirstById(id));
    }

    public List<MatchDto> findAllValMatches(){
        return valMatchesRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public MatchDto getValMatchDtoById(int id){
        return convertEntityToDto(valMatchesRepo.findFirstById(id));
    }

    public List<MatchDto> findAllValPastMatches(){
        return valPastMatchesRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public MatchDto getValPastMatchDtoById(int id){
        return convertEntityToDto(valPastMatchesRepo.findFirstById(id));
    }

    public MatchDto convertEntityToDto(Matches matches){
        Gson gson = new Gson();

        MatchDto matchDto = new MatchDto();
        matchDto.setId(matches.getId());
        matchDto.setBegin_at(matches.getBegin_at());
        matchDto.setName(matches.getName());
        matchDto.setLeagueId(matches.getLeagueID());
        matchDto.setLeagueName(  gson.fromJson(matches.getLeague(), League.class).getName() );
        matchDto.setMatchType(matches.getMatchType());
        matchDto.setNumberOfGames(matches.getNumberOfGames());
        matchDto.setGames(gson.fromJson(matches.getGames(), Game[].class));
        matchDto.setStreamList(gson.fromJson(matches.getStreamList(), StreamsList[].class));
        
        return matchDto;
    }


    
}
