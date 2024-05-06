package com.Starfox.EsportsWiki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Starfox.EsportsWiki.dto.PlayerDto;
import com.Starfox.EsportsWiki.service.PlayerService;

import com.Starfox.EsportsWiki.dto.CurrentTeamDto;
import com.Starfox.EsportsWiki.service.CurrentTeamService;
import org.springframework.ui.Model;

@Controller
public class PlayerController{
    
    PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) { this.playerService = playerService;}

    @GetMapping("/players/{teamid}/{teamname}")
    @ResponseBody
    public String findAll(@PathVariable("teamid") Integer teamid, @PathVariable("teamname") String teamname, Model model){
        //System.out.println("teamstr: " + team);
        //int team = Integer.parseInt(teamstr);
        model.addAttribute("teamidsearch", teamid);
        model.addAttribute("teamnamesearch", teamname);
        model.addAttribute("playerDtoList", playerService.findAll());
        return "/players";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PlayerDto getPlayerById(@PathVariable("id") int id){
        return playerService.getPlayerDtoById(id);
        
    }

  
}