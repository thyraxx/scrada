package com.thyraxx.scrada.smashgg.controller;

import com.google.gson.Gson;
import com.thyraxx.scrada.smashgg.service.SmashggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

//    @GetMapping("/test")
//    public String test(Model model)
//    {
//        smashggService.saveNewTournamentEvents();
//        return "index";
//    }

    @GetMapping("/getAllTournaments")
    @ResponseBody
    public String getAllTournaments()
    {
        return new Gson().toJson(smashggService.getAllTournamentsDTO());
    }

    @GetMapping("/")
    public String smashTournaments(Model model) {
        model.addAttribute("tournaments", smashggService.getAllTournamentsDTO());

        return "smashgg";
    }
}
