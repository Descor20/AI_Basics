package com.epita.creeps.tool;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"entries", "exit"})
public class GraphKeeper {
    @JsonIgnore
    int id;

    List<Graph> entries;
    List<Graph> exit;

    public GraphKeeper() {
        entries = new ArrayList<>();
    }

    public void setEntries(List<Graph> entries) {
        this.entries = entries;
    }

    public void setExit(List<Graph> exit) {
        this.exit = exit;
    }

    public List<Graph> getExit() {
        return exit;
    }

    public void evaluate_all(List<Double> entry) {
        if (entry.size() != entries.size()) {
            System.err.println("[AI manager]{Evaluate} :The entries doesn't match the requiered number of entries.");
        }
        int i = 0;
        for (Graph g : entries) {
            g.entry.add(entry.get(i));
            g.evaluate();
            i++;
        }
    }
}
