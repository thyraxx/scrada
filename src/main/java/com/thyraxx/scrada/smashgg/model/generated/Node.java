package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Node {
    @JsonProperty("events")
    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    List<Event> events;
}
