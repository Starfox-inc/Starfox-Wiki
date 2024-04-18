package com.Starfox.EsportsWiki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Starfox.EsportsWiki.dto.CurrentTeamDto;
import com.Starfox.EsportsWiki.service.CurrentTeamService;

@Controller
@RequestMapping("/teams")
public class CurrentTeamController {
    
    CurrentTeamService currentTeamService;

    @Autowired
    public CurrentTeamController(CurrentTeamService currentTeamService){
        this.currentTeamService = currentTeamService;
    }

    @GetMapping
    @ResponseBody
    public List<CurrentTeamDto> findAll(){
        return currentTeamService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CurrentTeamDto getTeamById(@PathVariable("id") int id){
        return currentTeamService.getTeamById(id);
         
    }

}
