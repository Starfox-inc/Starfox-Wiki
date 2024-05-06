package com.Starfox.EsportsWiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Starfox.EsportsWiki.service.PlayerService;


@Controller
@RequestMapping("/player")
public class PlayerController{
    
    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) { this.playerService = playerService;}

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("playerDtoList",playerService.findAll());
        return "players";
    }

    @GetMapping("/{id}")
    public String getPlayerById(@PathVariable("id") int id, Model model){
        model.addAttribute("playerDto", playerService.getPlayerDtoById(id));        
        return "players";
        
    }

    

  
}