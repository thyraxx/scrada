package com.thyraxx.scrada.smashgg.configuration;

import java.time.Instant;

public final class SmashggConfig {

    public static final long SMASHGG_API_CALL_DELAY = 60000;
    public static final String SEARCH_TOURNAMENT_AFTER_EPOCHTIME = "{ \"lastDate\":" + (Instant.now().getEpochSecond() - 262928) +"}"; // 262928 aprox 3 days

    private SmashggConfig() {}
}
