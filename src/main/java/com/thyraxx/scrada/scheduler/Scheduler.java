package com.thyraxx.scrada.scheduler;

import com.thyraxx.scrada.smashgg.configuration.SmashggConfig;
import com.thyraxx.scrada.smashgg.service.SmashggService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;

@Component
public class Scheduler {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final SmashggService smashggService;

    @Autowired
    public Scheduler(SmashggService smashggService) {
        this.smashggService = smashggService;
    }

    @Scheduled(fixedDelay = SmashggConfig.smashggAPICallDelay) // 60 seconds
    public void retrieveSmashTournamentData()
    {
        logger.debug("Retrieving data on: " + dateFormat  + " -> using method 'retrieveSmashTournamentData'");
        smashggService.saveNewTournamentEvents();

//        logger.debug("Updating data on: " + dateFormat  + " -> using method 'retrieveSmashTournamentData'");
//        smashggService.updateExistingTournaments();

        logger.debug("Sending notification on: " + dateFormat  + " -> using method 'retrieveSmashTournamentData'");
        smashggService.sendNotification();
    }

//    @Scheduled(fixedDelay = SmashggConfig.smashggAPICallDelay) // 60 seconds
//    public void updateExistingTournamentData()
//    {
//        logger.debug("Updating data on: " + dateFormat  + " -> using method 'updateExistingTournamentData'");
//        smashggService.updateExistingTournaments();
//    }

    @Scheduled(fixedDelay = 43200000) // 12 hours
    public void deleteFinishedTournaments()
    {
        logger.debug("Deleting expired/finished tournaments data on: " + dateFormat  + " -> using method 'deleteFinishedTournaments'");
        smashggService.deleteFinishedTournaments();
    }
}
