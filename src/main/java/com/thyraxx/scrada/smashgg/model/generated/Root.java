package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Root {

    private Data data;

    @JsonProperty("data")
    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
