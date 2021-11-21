package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root {
    @JsonProperty("data")
    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    Data data;
}
