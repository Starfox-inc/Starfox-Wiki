package com.Starfox.EsportsWiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String findAllTeams(Model model){
        model.addAttribute("currentTeamDtoList", currentTeamService.findAllTeams()) ;
        return "teams";
    }

    @GetMapping("/teaminfo/{id}")
    public String getTeamById(@PathVariable("id") int id, Model model){
        CurrentTeamDto currentTeamDto = currentTeamService.getTeamById(id);
        model.addAttribute("currentTeamDto", currentTeamDto);
        return "teams";    
    }

    @GetMapping("/valteams")
    public String findAllValTeams(Model model){
        model.addAttribute("currentTeamDtoList", currentTeamService.findAllValTeams());
        return "teams";
    }

    @GetMapping("/valteams/{id}")
    public String getValTeamById(@PathVariable("id") int id, Model model){
        model.addAttribute("currentTeamDto", currentTeamService.getValTeamById(id));    
        return "teams";
    }

    @GetMapping("/codteams")
    public String findAllCodTeams(Model model){
        model.addAttribute("currentTeamDtoList", currentTeamService.findAllCodTeams());
        return "teams";
    }

    @GetMapping("/codteams/{id}")
    public String getCodTeamById(@PathVariable("id") int id, Model model){
        model.addAttribute("currentTeamDto", currentTeamService.getCodTeamById(id));
        return "teams";    
    }

    @GetMapping("/csteams")
    public String findAllCsTeams(Model model){
        model.addAttribute("currentTeamDtoList", currentTeamService.findAllCsTeams());
        return "teams";
    }
    
    @GetMapping("/csteams/{id}")
    public String getCsTeamById(@PathVariable("id") int id, Model model){
        model.addAttribute("currentTeamDto", currentTeamService.getCsTeamById(id));
        return "teams";    
    }

    @GetMapping("/lolteams")
    public String findAllLoLTeams(Model model){
        model.addAttribute("currentTeamDtoList", currentTeamService.findAllLoLTeams());
        return "teams";
    }
    
    @GetMapping("/lolteams/{id}")
    public String getLoLTeamById(@PathVariable("id") int id, Model model){
        model.addAttribute("currentTeamDto", currentTeamService.getLoLTeamById(id));
        return "teams";    
    }
}
