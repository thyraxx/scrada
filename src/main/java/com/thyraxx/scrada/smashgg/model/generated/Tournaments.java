package com.thyraxx.scrada.smashgg.model.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Tournaments {
    @JsonProperty("nodes")
    public List<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    List<Node> nodes;
}
