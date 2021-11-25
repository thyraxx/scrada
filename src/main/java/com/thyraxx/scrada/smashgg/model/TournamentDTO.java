package com.thyraxx.scrada.smashgg.model;

import org.springframework.lang.NonNull;

import javax.persistence.Id;

public class TournamentDTO {

    @Id
    private Long id;

    @NonNull
    private Long tournamentId;

    String name;
    String shortSlug;

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
}