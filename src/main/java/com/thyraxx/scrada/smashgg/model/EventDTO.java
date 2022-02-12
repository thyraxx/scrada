package com.thyraxx.scrada.smashgg.model;

public class EventDTO {

    private long eventId;

    private String eventName;
    private String eventSlug;
    private int fee;
    private int valueLimit;

    private Integer teamRosterSize;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventSlug() {
        return eventSlug;
    }

    public void setEventSlug(String eventSlug) {
        this.eventSlug = eventSlug;
    }

    public Integer getTeamRosterSize() {
        return teamRosterSize;
    }

    public void setTeamRosterSize(Integer teamRosterSize) {
        this.teamRosterSize = teamRosterSize;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getValueLimit() {
        return valueLimit;
    }

    public void setValueLimit(int valueLimit) {
        this.valueLimit = valueLimit;
    }
}
