package com.thyraxx.scrada.scheduler;

import com.thyraxx.scrada.customproperties.CustomProperties;
import com.thyraxx.scrada.smashgg.configuration.SmashggConfig;
import com.thyraxx.scrada.smashgg.service.SmashggService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Component
public class Scheduler {

    private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    private final SmashggService smashggService;

    @Autowired
    public Scheduler(SmashggService smashggService) {
        this.smashggService = smashggService;
    }

    @Scheduled(fixedDelay = SmashggConfig.SMASHGG_API_CALL_DELAY) // 60 seconds
    public void retrieveSmashTournamentData()
    {
        logger.debug("Retrieving data on: {}-> using method 'retrieveSmashTournamentData'", CustomProperties.getDateTime());
        smashggService.saveNewTournamentEvents();

//        logger.debug("Updating data on: " + dateFormat  + " -> using method 'retrieveSmashTournamentData'");
//        smashggService.updateExistingTournaments();

        logger.debug("Sending notification on: {} -> using method 'retrieveSmashTournamentData'", CustomProperties.getDateTime());
        smashggService.sendNotification();
    }

    @Scheduled(fixedDelay = 43200000) // 12 hours
    public void deleteFinishedTournaments()
    {
       logger.debug("Deleting expired/finished tournaments data on: {} -> using method 'deleteFinishedTournaments'", CustomProperties.getDateTime());
        smashggService.deleteFinishedTournaments();
    }
}
