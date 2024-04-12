package com.Starfox.EsportsWiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/controllers")
public class Controllers {
    

    @GetMapping()
    public String getHello(){
        return "index.html";
    }
}
