package com.epita.creeps.tool;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    /* General */
    Node actual;
    Double exit;

    /* Backward */
    List<Graph> previous;
    List<Double> entry;

    /* Forward */
    List<Graph> next;

    public Graph() {
        next = new ArrayList<>();
        previous = new ArrayList<>();
        exit = -1.0;
        entry = new ArrayList<>();
        actual = new Node();
    }

    public Graph(Node actual) {
        next = new ArrayList<>();
        previous = new ArrayList<>();
        exit = -1.0;
        entry = new ArrayList<>();
        this.actual = actual;
    }

    public Graph(List<Graph> next, Node actual) {
        this.next = next;
        previous = new ArrayList<>();
        exit = -1.0;
        entry = new ArrayList<>();
        this.actual = actual;
    }

    public void evaluate() {
        if (entry.size() == previous.size() || previous.isEmpty()) {
            exit = 0.0;
            for (Double e : entry) {
                exit += e;
            }

            exit += actual.bias;

            for (int i = 0; i < next.size(); i++) {
                next.get(i).entry.add(exit * actual.weights.get(i));
                next.get(i).evaluate();
            }

            entry = new ArrayList<>();
        }
    }

    public void setNext(List<Graph> next) {
        this.next = next;
    }

    public void setPrevious(List<Graph> previous) {
        this.previous = previous;
    }

    public Double getExit() {
        return exit;
    }

    public void random_weight(int nb) {
        actual.random_weight(nb);
    }
}
