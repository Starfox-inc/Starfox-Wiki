package com.Starfox.EsportsWiki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Starfox.EsportsWiki.dto.CurrentTeamDto;
import com.Starfox.EsportsWiki.service.CurrentTeamService;

@Controller
public class CurrentTeamController {
    
    CurrentTeamService currentTeamService;

    @Autowired
    public CurrentTeamController(CurrentTeamService currentTeamService){
        this.currentTeamService = currentTeamService;
    }

    @GetMapping("/teaminfo")
    @ResponseBody
    public List<CurrentTeamDto> findAllTeams(){
        return currentTeamService.findAllTeams();
    }

    @GetMapping("/teaminfo/{id}")
    @ResponseBody
    public CurrentTeamDto getTeamById(@PathVariable("id") int id){
        return currentTeamService.getTeamById(id);    
    }

    @GetMapping("/valteams")
    @ResponseBody
    public List<CurrentTeamDto> findAllValTeams(){
        return currentTeamService.findAllValTeams();
    }

    @GetMapping("/valteams/{id}")
    @ResponseBody
    public CurrentTeamDto getValTeamById(@PathVariable("id") int id){
        return currentTeamService.getValTeamById(id);    
    }

    @GetMapping("/codteams")
    @ResponseBody
    public List<CurrentTeamDto> findAllCodTeams(){
        return currentTeamService.findAllCodTeams();
    }

    @GetMapping("/codteams/{id}")
    @ResponseBody
    public CurrentTeamDto getCodTeamById(@PathVariable("id") int id){
        return currentTeamService.getCodTeamById(id);    
    }

    @GetMapping("/csteams")
    @ResponseBody
    public List<CurrentTeamDto> findAllCsTeams(){
        return currentTeamService.findAllCsTeams();
    }
    
    @GetMapping("/csteams/{id}")
    @ResponseBody
    public CurrentTeamDto getCsTeamById(@PathVariable("id") int id){
        return currentTeamService.getCsTeamById(id);    
    }

    @GetMapping("/lolteams")
    @ResponseBody
    public List<CurrentTeamDto> findAllLoLTeams(){
        return currentTeamService.findAllLoLTeams();
    }
    
    @GetMapping("/lolteams/{id}")
    @ResponseBody
    public CurrentTeamDto getLoLTeamById(@PathVariable("id") int id){
        return currentTeamService.getLoLTeamById(id);    
    }
}
