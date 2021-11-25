package com.thyraxx.scrada.smashgg.controller;

import com.thyraxx.scrada.smashgg.service.SmashggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SmashggController {

    private final SmashggService smashggService;

    @Autowired
    public SmashggController(SmashggService smashggService) {
        this.smashggService = smashggService;
    }

    @GetMapping("/")
    public String index(Model model)
    {
        return "index";
    }

    @GetMapping("/test")
    public String test(Model model)
    {
        smashggService.saveNewTournamentEvents();
        return "index";
    }

    @GetMapping("/smashgg")
    public String smashTournaments(Model model) {
        model.addAttribute("tournaments", smashggService.getAllTournaments());

        return "smashgg";
    }

    @PostMapping("/smashgg")
    public String smashTournaments()
    {
        return "smashgg";
    }
}
