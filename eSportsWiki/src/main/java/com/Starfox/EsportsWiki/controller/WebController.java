package com.Starfox.EsportsWiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
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
    public String home(){
        return "home";
    }
}
