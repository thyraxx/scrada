package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    @JsonProperty("tournaments")
    public Tournaments getTournaments() {
        return this.tournaments;
    }

    public void setTournaments(Tournaments tournaments) {
        this.tournaments = tournaments;
    }

    Tournaments tournaments;
}
