package com.thyraxx.scrada.smashgg.service;

import com.thyraxx.scrada.smashgg.SmashggApi;
import com.thyraxx.scrada.smashgg.model.Tournament;
import com.thyraxx.scrada.smashgg.model.TournamentDTO;
import com.thyraxx.scrada.smashgg.model.generated.Node;
import com.thyraxx.scrada.smashgg.repository.SmashggRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SmashggService {

    private final SmashggRepository smashggRepository;

    @Autowired
    public SmashggService(SmashggRepository smashggRepository) {
        this.smashggRepository = smashggRepository;
    }

    public List<TournamentDTO> getAllTournamentsDTO()
    {
        ModelMapper modelMapper = new ModelMapper();
        return smashggRepository.findAll().stream()
                .map(tournament -> modelMapper.map(tournament, TournamentDTO.class))
                .collect(Collectors.toList());
    }

    public List<Tournament> getTournamentsEvents(String variables)
    {
        return SmashggApi.getSmashTournamentEvents(variables);
    }

    public void saveNewTournamentEvents()
    {
        // TODO: better var name or separate into it's own config?
        String epochSecondsParameter = "{ \"lastDate\":" + Instant.now().getEpochSecond() +"}";
        smashggRepository.saveAll(getTournamentsEvents(epochSecondsParameter));
    }

    public void save(Tournament tournament)
    {
        smashggRepository.save(tournament);
    }
}
