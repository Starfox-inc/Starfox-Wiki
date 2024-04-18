package com.Starfox.EsportsWiki.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Starfox.EsportsWiki.dto.CurrentTeamDto;
import com.Starfox.EsportsWiki.model.CurrentTeam;
import com.Starfox.EsportsWiki.repository.CurrentTeamRepository;

@Service
public class CurrentTeamService {
    private final CurrentTeamRepository currentTeamRepository;

    @Autowired
    public CurrentTeamService(CurrentTeamRepository currentTeamRepository){
        this.currentTeamRepository = currentTeamRepository;
    }

    public List<CurrentTeamDto> findAll(){
        return currentTeamRepository.findAll()
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    public CurrentTeamDto getTeamById(int id){
        CurrentTeam teamEntity = currentTeamRepository.findFirstById(id);
        return convertEntityToDto(teamEntity);
    }

    private CurrentTeamDto convertEntityToDto(CurrentTeam currentTeam){
        CurrentTeamDto currentTeamDto = new CurrentTeamDto();
        currentTeamDto.setAcronym(currentTeam.getAcronym());
        currentTeamDto.setCurrentVideoGameName(currentTeam.getCurrent_videogame_name());
        currentTeamDto.setId(currentTeam.getId());
        currentTeamDto.setName(currentTeam.getName());
        return currentTeamDto;
    }
}
