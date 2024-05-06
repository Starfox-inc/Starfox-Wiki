package com.Starfox.EsportsWiki.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.Starfox.EsportsWiki.service.MatchService;

import integrationAPI.UpdateDatabase;


@Controller
public class MatchController {
    
    MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService){
        this.matchService = matchService;
    }

    @GetMapping("/matches/{game}")
    public String matches(@PathVariable("game") String game, Model model)throws IOException, InterruptedException, URISyntaxException{
        switch (game) {
            case "lol":
                model.addAttribute("teamMatchDtoList", matchService.findAllLolPastMatches());
                System.out.println("LOL Matches fetched");
                break;
            case "cs":
                model.addAttribute("teamMatchDtoList", matchService.findAllCsPastMatches());    
                System.out.println("CS Matches fetched");
                break;
            case "cod":
                model.addAttribute("teamMatchDtoList", matchService.findAllCodPastMatches());
                System.out.println("COD Matches fetched");    
                break;
            case "val":
                model.addAttribute("teamMatchDtoList", matchService.findAllValPastMatches());
                System.out.println("VAL Matches fetched");        
                break;
            default:
                break;
        }
        return "matches";
    }

    @GetMapping("/live/{game}")
    public String findAllRunningMatches(Model model) throws IOException, InterruptedException, URISyntaxException{
        UpdateDatabase.updateTable("runningMatchList", "https://api.pandascore.co/matches/running" );
        System.out.println("Running Matches fetched");
        model.addAttribute("runningMatchDtoList", matchService.findAllRunningMatches());
        return "live";
    }

    @GetMapping("/runningmatches/{id}")
    public String getRunningMatchById(@PathVariable("id") int id, Model model) throws IOException, InterruptedException, URISyntaxException {
        UpdateDatabase.updateTable("runningMatchList", "https://api.pandascore.co/matches/running" );
        model.addAttribute("runningMatchDto", matchService.getRunningMatchDtoById(id));
        return "matches";
    }

    @GetMapping("/codmatches")
    public String findAllCodMatches(Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("codmatches", "https://api.pandascore.co/codmw/matches" );
        model.addAttribute("teamMatchDtoList", matchService.findAllCodMatches());
        return "matches";
    }

    @GetMapping("/codmatches/{id}")
    public String getCodMatchById(@PathVariable("id") int id, Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("codMatches", "https://api.pandascore.co/codmw/matches" );       
        model.addAttribute("teamMatchDto", matchService.getCodMatchDtoById(id));
        return "matches";    
    }

    @GetMapping("/codpastmatches")
    public String findAllCodPastMatches(Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("codPastMatches", "https://api.pandascore.co/codmw/matches/past" );       
        
        model.addAttribute("teamMatchDtoList", matchService.findAllCodPastMatches());
        return "matches";
    }

    @GetMapping("/codpastmatches/{id}")
    public String getCodPastMatchById(@PathVariable("id") int id, Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("codPastMatches", "https://api.pandascore.co/codmw/matches/past" );
        model.addAttribute("teamMatchDto", matchService.getCodPastMatchDtoById(id));
        return "matches";    
    }

    @GetMapping("/csmatches")
    public String findAllCsMatches(Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("csMatches", "https://api.pandascore.co/csgo/matches" );
        model.addAttribute("teamMatchDtoList", matchService.findAllCsMatches());
        return "matches";
    }

    @GetMapping("/csmatches/{id}")
    public String getCsMatchById(@PathVariable("id") int id, Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("csMatches", "https://api.pandascore.co/csgo/matches" );
        model.addAttribute("teamMatchDto", matchService.getCsMatchDtoById(id));
        return "matches";    
    }

    @GetMapping("/cspastmatches")
    public String findAllCsPastMatches(Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("csPastMatches", "https://api.pandascore.co/csgo/matches/past" );
        model.addAttribute("teamMatchDtoList", matchService.findAllCsPastMatches());
        return "matches";
    }

    @GetMapping("/cspastmatches/{id}")
    public String getCsPastMatchById(@PathVariable("id") int id, Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("csPastMatches", "https://api.pandascore.co/csgo/matches/past" );
        model.addAttribute("teamMatchDto", matchService.getCsPastMatchDtoById(id));
        return "matches";    
    }

    @GetMapping("/valmatches")
    public String findAllValMatches(Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("valMatches",  "https://api.pandascore.co/valorant/matches");
        model.addAttribute("teamMatchDtoList", matchService.findAllValMatches());
        return "matches";
    }

    @GetMapping("/valmatches/{id}")
    public String getValMatchById(@PathVariable("id") int id, Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("valMatches",  "https://api.pandascore.co/valorant/matches");
        model.addAttribute("teamMatchDto", matchService.getValMatchDtoById(id));
        return "matches";    
    }

    @GetMapping("/valpastmatches")
    public String findAllValPastMatches(Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("valPastMatches",  "https://api.pandascore.co/valorant/matches/past");
        model.addAttribute("teamMatchDtoList", matchService.findAllValPastMatches());
        return "matches";
    }

    @GetMapping("/valpastmatches/{id}")
    public String getValPastMatchById(@PathVariable("id") int id, Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("valPastMatches",  "https://api.pandascore.co/valorant/matches/past");
        model.addAttribute("teamMatchDto", matchService.getValPastMatchDtoById(id));
        return "matches";    
    }

    @GetMapping("/lolmatches")
    public String findAllLolMatches(Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("lolMatches",  "https://api.pandascore.co/lol/matches");
        model.addAttribute("teamMatchDtoList", matchService.findAllLolMatches());
        return "matches";
    }

    @GetMapping("/lolmatches/{id}")
    public String getLolMatchById(@PathVariable("id") int id, Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("lolMatches",  "https://api.pandascore.co/lol/matches");
        model.addAttribute("teamMatchDto", matchService.getLolMatchDtoById(id));
        return "matches";    
    }

    @GetMapping("/lolpastmatches")
    public String findAllLolPastMatches(Model model) throws IOException, InterruptedException{
        //UpdateDatabase.updateTable("lolPastMatches",  "https://api.pandascore.co/lol/matches/past");
        
        model.addAttribute("teamMatchDtoList", matchService.findAllLolPastMatches());
        return "matches";
    }

    @GetMapping("/lolpastmatches/{id}")
    public String getLolPastMatchById(@PathVariable("id") int id, Model model) throws IOException, InterruptedException{
       // UpdateDatabase.updateTable("lolPastMatches",  "https://api.pandascore.co/lol/matches/past");
        
        model.addAttribute("teamMatchDto", matchService.getLolPastMatchDtoById(id));
        return "matches";    
    }

    
}
