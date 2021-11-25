package com.thyraxx.scrada.scheduler;

import com.thyraxx.scrada.smashgg.service.SmashggService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class Scheduler {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final SmashggService smashggService;

    @Autowired
    public Scheduler(SmashggService smashggService) {
        this.smashggService = smashggService;
    }

    @Scheduled(fixedDelay = 90000)
    public void retrieveSmashTournamentData()
    {
        logger.debug("Retrieving data on: " + dateFormat  + " -> using method retrieveSmashTournamentData");
        smashggService.saveNewTournamentEvents();
    }
}
