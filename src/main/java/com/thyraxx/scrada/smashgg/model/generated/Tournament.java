package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tournament {
    @JsonProperty("registrationClosesAt")
    public int getRegistrationClosesAt() {
        return this.registrationClosesAt;
    }

    public void setRegistrationClosesAt(int registrationClosesAt) {
        this.registrationClosesAt = registrationClosesAt;
    }

    int registrationClosesAt;

    @JsonProperty("id")
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;

    @JsonProperty("slug")
    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    String slug;

    @JsonProperty("isRegistrationOpen")
    public boolean getIsRegistrationOpen() {
        return this.isRegistrationOpen;
    }

    public void setIsRegistrationOpen(boolean isRegistrationOpen) {
        this.isRegistrationOpen = isRegistrationOpen;
    }

    boolean isRegistrationOpen;

    @JsonProperty("shortSlug")
    public String getShortSlug() {
        return this.shortSlug;
    }

    public void setShortSlug(String shortSlug) {
        this.shortSlug = shortSlug;
    }

    String shortSlug;

    @JsonProperty("city")
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    String city;

    @JsonProperty("createdAt")
    public int getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    int createdAt;

    @JsonProperty("startAt")
    public int getStartAt() {
        return this.startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    int startAt;

    @JsonProperty("state")
    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    int state;
}
