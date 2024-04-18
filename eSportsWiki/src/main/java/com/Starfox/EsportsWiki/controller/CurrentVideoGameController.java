package com.Starfox.EsportsWiki.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Starfox.EsportsWiki.dto.CurrentVideoGameDTO;
import com.Starfox.EsportsWiki.service.CurrentVideoGameService;

@Controller
@RequestMapping("/cvg")
public class CurrentVideoGameController{

    CurrentVideoGameService currentVideoGameService;

    @Autowired
    public CurrentVideoGameController(CurrentVideoGameService currentVideoGameService){
        this.currentVideoGameService = currentVideoGameService;
    }

    
    @GetMapping()
    @ResponseBody
    public List<CurrentVideoGameDTO> findAll(){
        return currentVideoGameService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CurrentVideoGameDTO getGameById(@PathVariable("id") int id){
        CurrentVideoGameDTO cvgDto = currentVideoGameService.getGameById(id);
        return cvgDto;
    }
}