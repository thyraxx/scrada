package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thyraxx.scrada.smashgg.model.generated.Event;

import java.util.List;

public class Node {

    private int tournamentId;
    private String name;
    private String shortSlug;
    private String slug;
    private String city;
    private int createdAt;
    private long startAt;
    private long endAt;
    private int state;
    private List<Event> events;
    private int registrationClosesAt;

    private boolean isRegistrationOpen;

    @JsonProperty("events")
    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @JsonProperty("registrationClosesAt")
    public int getRegistrationClosesAt() {
        return this.registrationClosesAt;
    }

    public void setRegistrationClosesAt(int registrationClosesAt) {
        this.registrationClosesAt = registrationClosesAt;
    }

    @JsonProperty("id")
    public int getTournamentId() {
        return this.tournamentId;
    }

    public void setId(int tournamentId) {
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
    public int getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(int createdAt) {
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

    @JsonProperty("endAt")
    public long getEndAt() {
        return endAt;
    }

    public void setEndAt(long endAt) {
        this.endAt = endAt;
    }
}
