package com.Starfox.EsportsWiki.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Starfox.EsportsWiki.service.CurrentTeamService;
import com.Starfox.EsportsWiki.service.MatchService;

import integrationAPI.UpdateDatabase;
@Controller
public class WebController {

    MatchService matchService;
    CurrentTeamService currentTeamService;

    @Autowired
    public WebController(MatchService matchService, CurrentTeamService currentTeamService){
        this.matchService = matchService;
        this.currentTeamService = currentTeamService;
    }

    @GetMapping("/teams")
    public String teams(){
        return "teams";
    }

    @GetMapping("/matches")
    public String matches(){
        return "matches";
    }

    @GetMapping("/live")
    public String live(){
        return "live";
    }

    @GetMapping("/")
    public String home(Model model) throws IOException, InterruptedException, URISyntaxException{
        UpdateDatabase.updateTable("runningMatchList", "https://api.pandascore.co/matches/running" );
        model.addAttribute("runningMatchDtoList", matchService.findAllRunningMatches());
        model.addAttribute("lolMatchDtoList", matchService.findAllLolPastMatches());
        model.addAttribute("csMatchDtoList", matchService.findAllCsPastMatches());    
        model.addAttribute("codMatchDtoList", matchService.findAllCodPastMatches());
        model.addAttribute("valMatchDtoList", matchService.findAllValPastMatches());
        model.addAttribute("currentTeamDtoList", currentTeamService.findAllTeams()) ;   
        return "home";
    }

    @GetMapping("//{game}")
    public String tohome(){
        return "home";
    }

    @GetMapping("/settings")
    public String settings(){
        return "settings";
    }

    @GetMapping("/user/changepass")
    public String changepass(){
        return "/changepass";
    }
}
