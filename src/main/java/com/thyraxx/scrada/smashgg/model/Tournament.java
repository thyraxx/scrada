package com.thyraxx.scrada.smashgg.model;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tournament {

    @Id
    private Long id;

    @NonNull
    long tournamentId;

    long registrationClosesAt;
    String name;
    String shortSlug;
    String slug;
    boolean isRegistrationOpen;
    String city;
    long createdAt;
    long startAt;
    int state;

    private boolean isTournamentScanned;
    private boolean isUserNotifiedBeforeOpen;
    private boolean isUserNotifiedAfterOpen;

    public Long getId() {
        return id;
    }
    public void setId(long id) { this.id = id; }

    public long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public long getRegistrationClosesAt() {
        return registrationClosesAt;
    }

    public void setRegistrationClosesAt(long registrationClosesAt) {
        this.registrationClosesAt = registrationClosesAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortSlug() {
        return shortSlug;
    }

    public void setShortSlug(String shortSlug) {
        this.shortSlug = shortSlug;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isRegistrationOpen() {
        return isRegistrationOpen;
    }

    public void setRegistrationOpen(boolean registrationOpen) {
        isRegistrationOpen = registrationOpen;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getStartAt() {
        return startAt;
    }

    public void setStartAt(long startAt) {
        this.startAt = startAt;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
