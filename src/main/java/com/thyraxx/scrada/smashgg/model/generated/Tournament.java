package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tournament {

    long registrationClosesAt;
    long tournamentId;
    String name;
    String shortSlug;
    String slug;
    boolean isRegistrationOpen;
    String city;
    long createdAt;
    long startAt;
    int state;

    @JsonProperty("registrationClosesAt")
    public long getRegistrationClosesAt() {
        return this.registrationClosesAt;
    }

    public void setRegistrationClosesAt(long registrationClosesAt) {
        this.registrationClosesAt = registrationClosesAt;
    }

    @JsonProperty("id")
    public long getTournamentId() {
        return this.tournamentId;
    }

    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
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

    @JsonProperty("isRegistrationOpen")
    public boolean getIsRegistrationOpen() {
        return this.isRegistrationOpen;
    }

    public void setIsRegistrationOpen(boolean isRegistrationOpen) {
        this.isRegistrationOpen = isRegistrationOpen;
    }

    @JsonProperty("shortSlug")
    public String getShortSlug() {
        return this.shortSlug;
    }

    public void setShortSlug(String shortSlug) {
        this.shortSlug = shortSlug;
    }

    @JsonProperty("city")
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("createdAt")
    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("startAt")
    public long getStartAt() {
        return this.startAt;
    }

    public void setStartAt(long startAt) {
        this.startAt = startAt;
    }

    @JsonProperty("state")
    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
