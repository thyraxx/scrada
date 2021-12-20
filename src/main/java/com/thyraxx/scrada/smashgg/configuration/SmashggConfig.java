package com.thyraxx.scrada.smashgg.configuration;

import java.time.Instant;

public class SmashggConfig {

    // Delay in milliseconds
    public static final long smashggAPICallDelay = 60000;
    public static final String searchTournamentsAfterEpochTime = "{ \"lastDate\":" + (Instant.now().getEpochSecond() - 262928) +"}"; // 262928 aprox 3 days

}
