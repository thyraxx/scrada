package com.thyraxx.scrada.smashgg.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thyraxx.scrada.smashgg.SmashggApi;
import com.thyraxx.scrada.smashgg.model.EventsModel;
import com.thyraxx.scrada.smashgg.model.Tournament;
import com.thyraxx.scrada.smashgg.repository.SmashggRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SmashggService {

    private final SmashggRepository smashggRepository;

    @Autowired
    public SmashggService(SmashggRepository smashggRepository) {
        this.smashggRepository = smashggRepository;
    }

    public List<Tournament> getAllTournaments()
    {
        return smashggRepository.findAll();
    }

    public List<Object> getTournamentsEvents(String variables)
    {
        return SmashggApi.runAPIQuery(variables);
    }
}
