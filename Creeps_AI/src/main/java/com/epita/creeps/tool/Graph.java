package com.epita.creeps.tool;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"id", "actual", "exit", "entry", "next"})
public class Graph {
    /* General */
    int id;
    Node actual;
    Double exit;

    /* Backward */
    @JsonIgnore
    List<Graph> previous;
    List<Double> entry;

    /* Forward */
    List<Graph> next;

    /* Getter */
    public Node getActual() {
        return actual;
    }

    public List<Graph> getPrevious() {
        return previous;
    }

    public List<Double> getEntry() {
        return entry;
    }

    public List<Graph> getNext() {
        return next;
    }

    public Double getExit() {
        return exit;
    }

    public int getId() {
        return id;
    }

    /* Setter */
    public void setActual(Node actual) {
        this.actual = actual;
    }

    public void setExit(Double exit) {
        this.exit = exit;
    }

    public void setEntry(List<Double> entry) {
        this.entry = entry;
    }

    public void setNext(List<Graph> next) {
        this.next = next;
    }

    public void setPrevious(List<Graph> previous) {
        this.previous = previous;
    }

    public void setId(int id) {
        this.id = id;
    }

    /* Constructor */
    public Graph() {
        id = Basics.Random();
        next = new ArrayList<>();
        previous = new ArrayList<>();
        exit = -1.0;
        entry = new ArrayList<>();
        actual = new Node();
    }

    public Graph(Node actual) {
        id = Basics.Random();
        next = new ArrayList<>();
        previous = new ArrayList<>();
        exit = -1.0;
        entry = new ArrayList<>();
        this.actual = actual;
    }

    public Graph(List<Graph> next, Node actual) {
        id = Basics.Random();
        this.next = next;
        previous = new ArrayList<>();
        exit = -1.0;
        entry = new ArrayList<>();
        this.actual = actual;
    }

    public Graph(int id, Node actual, Double exit, List<Double> entry, List<Graph> next) {
        this.id = id;
        this.actual = actual;
        this.exit = exit;
        this.entry = entry;
        this.next = next;
    }

    /* Methods */
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

    public void random_weight(int nb) {
        actual.random_weight(nb);
    }
}
