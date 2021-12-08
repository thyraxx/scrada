package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeamRosterSize {

    private Integer maxPlayers;

    @JsonProperty("maxPlayers")
    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
