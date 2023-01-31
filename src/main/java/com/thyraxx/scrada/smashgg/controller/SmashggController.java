package com.thyraxx.scrada.smashgg.controller;

import com.google.gson.Gson;
import com.thyraxx.scrada.smashgg.model.Tournament;
import com.thyraxx.scrada.smashgg.service.SmashggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/smashgg")
public class SmashggController {

    private final SmashggService smashggService;

    @Autowired
    public SmashggController(SmashggService smashggService) {
        this.smashggService = smashggService;
    }

    @GetMapping("/index")
    public String index(Model model)
    {
        return "index";
    }

    @GetMapping("/getAllTournaments")
    @ResponseBody
    public String getAllTournaments()
    {
        return new Gson().toJson(smashggService.getAllTournamentsDTO());
    }

    @PostMapping("/saveTournament")
    @ResponseBody
    public String saveTournament(@RequestBody Tournament tournament)
    {
        // TODO: use httpstatus?
        if(tournament.getEvents().isEmpty())
        {
            return "Tournament can't be added if there aren't any events.";
        }

        // TODO: change saving to a boolean to know if it actually is saved?
        smashggService.save(tournament);
        return "Created!";
    }

    @GetMapping("/")
    public String smashTournaments(Model model) {
        model.addAttribute("tournaments", smashggService.getAllTournamentsDTO());

        return "smashgg";
    }
}
