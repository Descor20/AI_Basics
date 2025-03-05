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

    public boolean evaluate_all(List<Double> entry) {
        if (entry.size() != entries.size()) {
            System.err.println("[AI manager]{Evaluate All} :The entries doesn't match the requiered number of entries.");
            return false;
        }
        int i = 0;
        for (Graph g : entries) {
            g.entry.add(entry.get(i));
            g.evaluate(false);
            i++;
        }
        return true;
    }

    public boolean apply_correction(List<Double> expected, Double LearningRate) {
        if (expected == null || exit == null || exit.isEmpty() || expected.isEmpty() || expected.size() != exit.size()) {
            System.err.println("[AI manager]{Apply Correction} :The expected doesn't match the requiered number of exit.");
            return false;
        }
        List<Graph> layer = exit;
        List<Thread> list_t = new ArrayList<>();
        for (int i = 0; i < layer.size(); i++) {
            Graph g = layer.get(i);
            final int index = i;
            Thread t = new Thread(() -> {
                g.set_learning_exit(expected.get(index), index, LearningRate);
            });
            list_t.add(t);
        }
        for (int i = 0; i < list_t.size(); i++) {
            list_t.get(i).start();
        }
        for (int i = 0; i < list_t.size(); i++) {
            Thread t = list_t.get(i);
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //System.out.println("All joigned exit !");
        layer = layer.get(0).previous;

        while (layer != null && !layer.isEmpty()) {
            list_t = new ArrayList<>();
            for (int i = 0; i < layer.size(); i++) {
                Graph g = layer.get(i);
                final int index = i;
                Thread t = new Thread(() -> {
                    g.learningHidden(index, LearningRate);
                });
                list_t.add(t);
            }
            for (int i = 0; i < list_t.size(); i++) {
                list_t.get(i).start();
            }
            for (int i = 0; i < list_t.size(); i++) {
                Thread t = list_t.get(i);
                try {
                    t.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //System.out.println("All joigned !");
            layer = layer.get(0).previous;
        }
        return true;
    }


    /* Memory Methods */
    public void save(String path) {
        JSON_Memory.write(this, path);
    }

    public static GraphKeeper retrieve(String path) {
        return JSON_Memory.read_graph(path);
    }
}
