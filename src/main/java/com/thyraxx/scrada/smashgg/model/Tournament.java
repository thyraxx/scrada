package com.thyraxx.scrada.smashgg.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tournament {

    // TODO: remove not used?
    private Long id;

    @Id
    private long tournamentId;

    private long registrationClosesAt;
    private String name;
    private String shortSlug;
    private String slug;
    private boolean isRegistrationOpen;
    private String city;
    private long createdAt;
    private long startAt;
    private long endAt;
    private int state;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_tournament_id")
    private List<Event> events;

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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public long getEndAt() {
        return endAt;
    }

    public void setEndAt(long endAt) {
        this.endAt = endAt;
    }

    public boolean isTournamentScanned() {
        return isTournamentScanned;
    }

    public void setTournamentScanned(boolean tournamentScanned) {
        isTournamentScanned = tournamentScanned;
    }

    public boolean isUserNotifiedBeforeOpen() {
        return isUserNotifiedBeforeOpen;
    }

    public void setUserNotifiedBeforeOpen(boolean userNotifiedBeforeOpen) {
        isUserNotifiedBeforeOpen = userNotifiedBeforeOpen;
    }

    public boolean isUserNotifiedAfterOpen() {
        return isUserNotifiedAfterOpen;
    }

    public void setUserNotifiedAfterOpen(boolean userNotifiedAfterOpen) {
        isUserNotifiedAfterOpen = userNotifiedAfterOpen;
    }
}
