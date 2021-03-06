package com.thyraxx.scrada.scheduler;

import com.thyraxx.scrada.smashgg.configuration.SmashggConfig;
import com.thyraxx.scrada.smashgg.service.SmashggService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
public class Scheduler {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    private final SmashggService smashggService;
    private final Clock clock;

    @Autowired
    public Scheduler(SmashggService smashggService, Clock clock) {
        this.smashggService = smashggService;
        this.clock = clock;
    }

    @Scheduled(fixedDelay = SmashggConfig.SMASHGG_API_CALL_DELAY) // 60 seconds
    public void retrieveSmashTournamentData()
    {
        logger.debug("Retrieving data on: {} -> using method 'retrieveSmashTournamentData'", clock.instant());
        smashggService.saveNewTournamentEvents();

//        logger.debug("Updating data on: " + dateFormat  + " -> using method 'retrieveSmashTournamentData'");
//        smashggService.updateExistingTournaments();

        logger.debug("Sending notification on: {} -> using method 'retrieveSmashTournamentData'", clock.instant());
        smashggService.sendNotification();
    }

    @Scheduled(fixedDelay = 43200000) // 12 hours
    public void deleteFinishedTournaments()
    {
       logger.debug("Deleting expired/finished tournaments data on: {} -> using method 'deleteFinishedTournaments'", clock.instant());
        smashggService.deleteFinishedTournaments();
    }
}
