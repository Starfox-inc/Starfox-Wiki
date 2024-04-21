package com.Starfox.EsportsWiki.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Starfox.EsportsWiki.dto.CurrentTeamDto;
import com.Starfox.EsportsWiki.model.CurrentTeam;
import com.Starfox.EsportsWiki.model.TeamInfo;
import com.Starfox.EsportsWiki.repository.CodTeamRepository;
import com.Starfox.EsportsWiki.repository.CsTeamRepository;
import com.Starfox.EsportsWiki.repository.LoLTeamRepository;
import com.Starfox.EsportsWiki.repository.TeamInfoRepository;
import com.Starfox.EsportsWiki.repository.ValTeamRepository;

@Service
public class CurrentTeamService {
    private final TeamInfoRepository teamInfoRepository;
    private final ValTeamRepository valTeamRepository;
    private final CsTeamRepository csTeamRepository;
    private final CodTeamRepository codTeamRepository;
    private final LoLTeamRepository loLTeamRepository;

    @Autowired
    public CurrentTeamService(TeamInfoRepository teamInfoRepository, ValTeamRepository valTeamRepository,
                 CsTeamRepository csTeamRepository, CodTeamRepository codTeamRepository, LoLTeamRepository loLTeamRepository){
        this.teamInfoRepository = teamInfoRepository;
        this.valTeamRepository = valTeamRepository;
        this.codTeamRepository = codTeamRepository;
        this.csTeamRepository = csTeamRepository;
        this.loLTeamRepository = loLTeamRepository;
    }

    //Services for teaminfo
    public List<CurrentTeamDto> findAllTeams(){
        return teamInfoRepository.findAll()
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    public CurrentTeamDto getTeamById(int id){
        TeamInfo teamEntity = teamInfoRepository.findFirstById(id);
        return convertEntityToDto(teamEntity);
    }

    //Services for Val Teams
    public List<CurrentTeamDto> findAllValTeams(){
        return valTeamRepository.findAll()
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    public CurrentTeamDto getValTeamById(int id){
        return convertEntityToDto(valTeamRepository.findFirstById(id));
    }


    //Services for Cod Teams
    public List<CurrentTeamDto> findAllCodTeams(){
        return codTeamRepository.findAll()
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }
    
    public CurrentTeamDto getCodTeamById(int id){
        return convertEntityToDto(codTeamRepository.findFirstById(id));
    }
    //Services for Cs Teams
    public List<CurrentTeamDto> findAllCsTeams(){
        return csTeamRepository.findAll()
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    public CurrentTeamDto getCsTeamById(int id){
        return convertEntityToDto(csTeamRepository.findFirstById(id));
    }

    //Services for LoL Teams
    public List<CurrentTeamDto> findAllLoLTeams(){
        return loLTeamRepository.findAll()
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    public CurrentTeamDto getLoLTeamById(int id){
        return convertEntityToDto(loLTeamRepository.findFirstById(id));
    }

    //Entity To DTO
    private CurrentTeamDto convertEntityToDto(CurrentTeam currentTeam){
        CurrentTeamDto currentTeamDto = new CurrentTeamDto();
        currentTeamDto.setAcronym(currentTeam.getAcronym());
        currentTeamDto.setCurrentVideoGameName(currentTeam.getCurrent_videogame_name());
        currentTeamDto.setId(currentTeam.getId());
        currentTeamDto.setName(currentTeam.getName());
        return currentTeamDto;
    }
}
