package com.Starfox.EsportsWiki.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Starfox.EsportsWiki.dto.CurrentVideoGameDTO;
import com.Starfox.EsportsWiki.model.CurrentVideoGame;
import com.Starfox.EsportsWiki.repository.CurrentVideoGameRepository;

@Service
public class CurrentVideoGameService{
    
    private final CurrentVideoGameRepository currentVideoGameRepository;

    @Autowired
    public CurrentVideoGameService(CurrentVideoGameRepository CVGR ){
        this.currentVideoGameRepository = CVGR;
    }

    public List<CurrentVideoGameDTO> findAll(){
        return currentVideoGameRepository.findAll()
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    public CurrentVideoGameDTO getGameById(int id){
        CurrentVideoGame gameEntity = currentVideoGameRepository.findFirstById(id);
        return convertEntityToDto(gameEntity);
    }

    public CurrentVideoGameDTO convertEntityToDto(CurrentVideoGame cvg){
        CurrentVideoGameDTO cvgDto = new CurrentVideoGameDTO();
        cvgDto.setId(cvg.getId());
        cvgDto.setName(cvg.getName());
        cvgDto.setSlug(cvg.getSlug());
        return cvgDto;
    }


}
