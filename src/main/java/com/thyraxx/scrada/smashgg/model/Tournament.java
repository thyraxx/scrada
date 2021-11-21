package com.thyraxx.scrada.smashgg.model;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tournament {

    @Id
    private Long id;

    @NonNull
    private Long tournamentId;

    private String tournamentName;
    private String tournamentSlug;
    private String tournamentUrl;
    private String registrationClosesAt;

    private boolean isTournamentScanned;
    private boolean isRegistrationOpen;
    private boolean isUserNotifiedBeforeOpen;
    private boolean isUserNotifiedAfterOpen;

    public Long getId() {
        return id;
    }

    @NonNull
    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(@NonNull Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getTournamentSlug() {
        return tournamentSlug;
    }

    public void setTournamentSlug(String tournamentSlug) {
        this.tournamentSlug = tournamentSlug;
    }

    public String getTournamentUrl() {
        return tournamentUrl;
    }

    public void setTournamentUrl(String tournamentUrl) {
        this.tournamentUrl = tournamentUrl;
    }

    public boolean isTournamentScanned() {
        return isTournamentScanned;
    }

    public void setTournamentScanned(boolean tournamentScanned) {
        isTournamentScanned = tournamentScanned;
    }

    public boolean isRegistrationOpen() {
        return isRegistrationOpen;
    }

    public void setRegistrationOpen(boolean registrationOpen) {
        isRegistrationOpen = registrationOpen;
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

    public static class TournamentBuilder {

    }
}
