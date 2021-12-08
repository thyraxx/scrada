package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

    private int eventId;
    private String name;
    private String slug;
    private TeamRosterSize teamRosterSize;
    private int valueLimit;
    private double fee;

    @JsonProperty("id")
    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    @JsonProperty("teamRosterSize")
    public TeamRosterSize getTeamRosterSize() {
        return this.teamRosterSize;
    }

    public void setTeamRosterSize(TeamRosterSize teamRosterSize) {
        this.teamRosterSize = teamRosterSize;
    }

    @JsonProperty("valueLimit")
    public int getValueLimit() {
        return valueLimit;
    }

    public void setValueLimit(int valueLimit) {
        this.valueLimit = valueLimit;
    }

    @JsonProperty("fee")
    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
