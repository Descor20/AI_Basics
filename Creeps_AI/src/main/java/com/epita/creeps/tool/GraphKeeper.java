package com.epita.creeps.tool;

import com.epita.creeps.AI_manager.Memory.JSON_Memory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"entries", "exit"})
public class GraphKeeper {

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

    public List<Graph> getEntries() {
        return entries;
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


    /* Memory Methods */
    public void save(String path) {
        JSON_Memory.write(this, path);
    }

    private void set_previous_layer() {
        List<Graph> g = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            g.add(entries.get(0));
        }

        while (g.size() > 0){
            Graph entry = g.get(0);
            List<Graph> previous = new ArrayList<>();

            List<Graph> it = new ArrayList<>();
            for (int j = 0; j < entries.size(); j++) {
                it.add(entries.get(j));
            }
            while (it.size() > 0) {
                Graph prev = it.get(0);
                if (Basics.contains(prev.next, entry.id)) {
                    previous.add(prev);
                }
                for (int i = 0; i < prev.getNext().size(); i++) {
                    it.add(prev.getNext().get(i));
                }
                it.remove(prev);
            }

            entry.setPrevious(previous); // Don't work, is it a copy !?


            for (int i = 0; i < entry.getNext().size(); i++) {
                g.add(entry.getNext().get(i));
            }
            g.remove(entry);
        }

    }
    public static GraphKeeper retrieve(String path) {
        GraphKeeper keeper = JSON_Memory.read_graph(path);
        keeper.set_previous_layer();
        return keeper;
    }
}
