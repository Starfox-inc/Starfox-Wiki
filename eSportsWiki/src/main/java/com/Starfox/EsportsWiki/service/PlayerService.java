package com.Starfox.EsportsWiki.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Starfox.EsportsWiki.dto.PlayerDto;
import com.Starfox.EsportsWiki.model.Player;
import com.Starfox.EsportsWiki.repository.PlayerRepository;





@Service
public class PlayerService{

    private final PlayerRepository playerRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;}


    // public List<PlayerDto> findAll(){
    //     List<integrationAPI.Player> playerList = playerRepository.findAll();
            
    //     return playerList.stream()
    //         .map(integrationAPI.Player -> modelMapper.map(integrationAPI.Player, PlayerDto.class))
    //         .collect(Collectors.toList());
              
    // }
 
// /* 
//     public PlayerDto getPlayerDtoById(Long id){
//         integrationAPI.Player playerEntity = playerRepository.findFirstById(id);
//         return PlayerDto.builder()
//         .id(playerEntity.getId())
//         .teamName(playerEntity.getTeamName())
//         .playerName(playerEntity.getPlayerName())
//         .currentVideogame(playerEntity.getCurrentVideogame())
//         .build();
//     }
//    */ 

//    public PlayerDto getPlayerDtoById(Integer id){
//     /* 
//     Optional<integrationAPI.Player> playe = playerRepository.findAll();
//     integrationAPI.Player player = playe.get();
//     */
//     PlayerDto playerDto = modelMapper.map(playerRepository.find(id), PlayerDto.class);

//     return playerDto;
//    }
    
}
