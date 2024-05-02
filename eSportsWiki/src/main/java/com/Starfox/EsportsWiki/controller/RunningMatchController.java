package com.Starfox.EsportsWiki.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Starfox.EsportsWiki.service.RunningMatchService;

import integrationAPI.UpdateRunningMatches;

@Controller
@RequestMapping("/runningmatches")
public class RunningMatchController {
    
    RunningMatchService runningMatchService;

    @Autowired
    public RunningMatchController(RunningMatchService runningMatchService){
        this.runningMatchService = runningMatchService;
    }

    @GetMapping()
    public String findAll(Model model) throws IOException, InterruptedException{
        UpdateRunningMatches.main(null);
        System.out.println("Running Matches fetched");
        model.addAttribute("runningMatchDtoList", runningMatchService.findAll());
        return "matches";
    }

    @GetMapping("/{id}")
    public String getRunningMatchById(@PathVariable("id") int id, Model model) throws IOException, InterruptedException {
        UpdateRunningMatches.main(null);
        model.addAttribute("runningMatchDto", runningMatchService.getRunningMatchDtoById(id));
        return "matches";
    }
}
