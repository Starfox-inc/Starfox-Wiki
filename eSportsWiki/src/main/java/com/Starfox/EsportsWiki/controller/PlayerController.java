package com.Starfox.EsportsWiki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Starfox.EsportsWiki.dto.PlayerDto;
import com.Starfox.EsportsWiki.service.PlayerService;


@Controller
@RequestMapping("/player")
public class PlayerController{
    
    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) { this.playerService = playerService;}

    @GetMapping()
    @ResponseBody
    public List<PlayerDto> findAll(){
        return playerService.findAll();
    }

    @GetMapping("/{id}")
    public String getPlayerById(@PathVariable("id") int id, Model model){
        PlayerDto playerDto = playerService.getPlayerDtoById(id);
        model.addAttribute("playerDto", playerDto);
        return "home";

    }

    // @GetMapping("/{id}")
    // @ResponseBody
    // public ResponseEntity<PlayerDto> getPlayerById(@PathVariable("id") int id){
    //     PlayerDto playerDto = playerService.getPlayerDtoById(id);

    //     return ResponseEntity.ok(playerDto);
    // }
    
    // @GetMapping(value="/hey/{hello}" )
    // public String welcome(@PathVariable("hello") Integer id, Model model){
    //     PlayerDto playerDto = playerService.getPlayerDtoById(id);
    //     model.addAttribute("playerDto", playerDto);

         
    //     return "index";
    // }
}