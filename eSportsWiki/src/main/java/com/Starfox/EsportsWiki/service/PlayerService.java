package com.Starfox.EsportsWiki.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Starfox.EsportsWiki.dto.PlayerDto;
import com.Starfox.EsportsWiki.model.Player;
import com.Starfox.EsportsWiki.repository.PlayerRepository;

@Service
public class PlayerService{

    private final PlayerRepository playerRepository;
    
    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;}

    public List<PlayerDto> findAll(){
        return playerRepository.findAll()
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
     }
 
    public PlayerDto getPlayerDtoById(int id){
        Player playerEntity = playerRepository.findFirstByPlayerId(id);
        return convertEntityToDto(playerEntity);
    }
    
    private PlayerDto convertEntityToDto(Player player){
        PlayerDto playerDto = new PlayerDto();
        playerDto.setPlayerId(player.getPlayerId());
        playerDto.setTeamId((player.getTeaminfo() != null) ? (player.getTeaminfo().getId()):(null));
        playerDto.setName(player.getName());
        playerDto.setActive(player.isActive());
        playerDto.setCurrentGame(player.getCurrentGame().getName());
        return playerDto;
    }

    
}
