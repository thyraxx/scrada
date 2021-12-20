package com.thyraxx.scrada.smashgg.service;

import com.thyraxx.scrada.smashgg.SmashggApi;
import com.thyraxx.scrada.smashgg.configuration.SmashggConfig;
import com.thyraxx.scrada.smashgg.model.Event;
import com.thyraxx.scrada.smashgg.model.Tournament;
import com.thyraxx.scrada.smashgg.model.TournamentDTO;
import com.thyraxx.scrada.smashgg.repository.SmashggRepository;
import com.thyraxx.scrada.telegrambot.SmashggTelegramBot;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
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

    public List<Tournament> getAllTournaments()
    {
        return smashggRepository.findAll();
    }

    // TODO: change name into API call?
    public List<Tournament> getSmashTournamentsEvents(String variables)
    {
        return SmashggApi.getSmashTournamentEvents(variables);
    }

    public void saveNewTournamentEvents()
    {
        // TODO: better var name or separate into it's own config?
        Set<Tournament> newTournaments = getSmashTournamentsEvents(SmashggConfig.searchTournamentsAfterEpochTime).stream()
                .filter(tournament -> !tournament.getEvents().isEmpty() && !smashggRepository.existsByTournamentId(tournament.getTournamentId()))
                .collect(Collectors.toSet());

        smashggRepository.saveAll(newTournaments);
    }

    public void updateExistingTournaments()
    {
        smashggRepository.findAll().forEach(tournament -> {
            Tournament updatedTournamentData = SmashggApi.updateExistingTournaments(tournament.getTournamentId());
            smashggRepository.save(updatedTournamentData);
        });
    }

    // TODO: optimize query, let database decide instead of retrieving first all entities?
    public void deleteFinishedTournaments()
    {
        List<Tournament> tournaments = smashggRepository.findAll().stream()
                        .filter(tournament -> Instant.now().getEpochSecond() > tournament.getEndAt() )
                        .collect(Collectors.toList());

        smashggRepository.deleteAll(tournaments);
    }

    public void sendNotification()
    {
        List<Tournament> tournaments = smashggRepository.findAll();

        tournaments.forEach(tournament -> {
            if(tournament.isRegistrationOpen() && !tournament.isUserNotifiedAfterOpen())
            {
                SmashggTelegramBot.sendMessage(-477115672,
                        "<b>" + tournament.getName() + "</b> \n\n" +
                        "<b>Location:</b> " + tournament.getCity() + "\n\n" +
                        "<b>Date:</b> " + LocalDateTime.ofEpochSecond(tournament.getStartAt(), 0, ZoneOffset.ofHours(1)) + " \n\n" +
                        "<b>Events:</b> \n\n" +
                            for(Event event : tournament.getEvents())
                            {
                                event.getValueLimit()
                            }
                + "https://smash.gg/" + tournament.getSlug()

                );
            }
        });
    }

    public void save(Tournament tournament)
    {
        smashggRepository.save(tournament);
    }
}