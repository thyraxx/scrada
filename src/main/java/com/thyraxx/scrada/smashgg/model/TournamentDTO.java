package com.thyraxx.scrada.smashgg.model;

import org.springframework.lang.NonNull;

import javax.persistence.Id;
import java.util.List;

public class TournamentDTO {

    @Id
    private Long id;

    @NonNull
    private Long tournamentId;

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

    private boolean isUserNotifiedBeforeOpen;
    private boolean isUserNotifiedAfterOpen;

    private List<EventDTO> events;

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
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

    public long getRegistrationClosesAt() {
        return registrationClosesAt;
    }

    public void setRegistrationClosesAt(long registrationClosesAt) {
        this.registrationClosesAt = registrationClosesAt;
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

    public long getEndAt() {
        return endAt;
    }

    public void setEndAt(long endAt) {
        this.endAt = endAt;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(@NonNull Long tournamentId) {
        this.tournamentId = tournamentId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}