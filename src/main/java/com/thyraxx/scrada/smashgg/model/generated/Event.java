package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
    int id;
    String name;
    String slug;
    Tournament tournament;

    @JsonProperty("id")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @JsonProperty("slug")
    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }


    @JsonProperty("tournament")
    public Tournament getTournament() {
        return this.tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
