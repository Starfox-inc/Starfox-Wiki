package com.Starfox.EsportsWiki.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Starfox.EsportsWiki.dto.RunningMatchDto;
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
    @ResponseBody
    public List<RunningMatchDto> findAll() throws IOException, InterruptedException{
        UpdateRunningMatches.main(null);
        System.out.println("RunningMatchTable updated");
        return runningMatchService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public RunningMatchDto getPlayerById(@PathVariable("id") int id) {
        RunningMatchDto runningMatchDto = runningMatchService.getRunningMatchDtoById(id);
        return runningMatchDto;
    }
}
