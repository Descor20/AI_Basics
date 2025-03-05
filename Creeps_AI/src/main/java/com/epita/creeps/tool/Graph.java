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
    @JsonIgnore
    List<Double> ErrorsInfluence;
    List<Double> NextErrors;

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

    public List<Double> getErrorsInfluence() {
        return ErrorsInfluence;
    }

    public List<Double> getNextErrors() {
        return NextErrors;
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

    public void setErrorsInfluence(List<Double> errorsInfluence) {
        ErrorsInfluence = errorsInfluence;
    }

    public void setNextErrors(List<Double> nextErrors) {
        NextErrors = nextErrors;
    }

    /* Constructor */
    public Graph() {
        id = Basics.Random();
        next = new ArrayList<>();
        previous = new ArrayList<>();
        exit = -1.0;
        entry = new ArrayList<>();
        actual = new Node();
        ErrorsInfluence = new ArrayList<>();
        NextErrors = new ArrayList<>();
    }

    public Graph(Node actual) {
        id = Basics.Random();
        next = new ArrayList<>();
        previous = new ArrayList<>();
        exit = -1.0;
        entry = new ArrayList<>();
        this.actual = actual;
        ErrorsInfluence = new ArrayList<>();
        NextErrors = new ArrayList<>();
    }

    public Graph(List<Graph> next, Node actual) {
        id = Basics.Random();
        this.next = next;
        previous = new ArrayList<>();
        exit = -1.0;
        entry = new ArrayList<>();
        this.actual = actual;
        ErrorsInfluence = new ArrayList<>();
        NextErrors = new ArrayList<>();
    }

    public Graph(int id, Node actual, Double exit, List<Double> entry, List<Graph> next) {
        this.id = id;
        this.actual = actual;
        this.exit = exit;
        this.entry = entry;
        this.next = next;
        ErrorsInfluence = new ArrayList<>();
        NextErrors = new ArrayList<>();
    }

    /* Methods */
    public void evaluate(boolean learning) {
        if (entry.size() == previous.size() || previous.isEmpty()) {
            exit = 0.0;
            for (Double e : entry) {
                exit += e;
            }

            exit = Basics.Sigmoid(exit);

            for (int i = 0; i < next.size(); i++) {
                next.get(i).entry.add(exit * actual.weights.get(i));
                next.get(i).evaluate(learning);
            }

            if (!learning)//TODO check if needed
                entry = new ArrayList<>();
        }
    }

    /* Learning methods */
    private Double previous_weight_sum(int index) {
        Double res = 0.0;
        for (int i = 0; i < previous.size(); i++) {
            Double w = previous.get(i).getActual().weights.get(index);
            res += w;
        }
        if (res == 0.0)
            return 1.0;
        return res;
    }

    private Double next_weight_sum() {
        Double res = 0.0;
        for (int i = 0; i < next.size(); i++) {
            Double w = getActual().weights.get(i);
            res += w;
        }
        if (res == 0.0)
            return 1.0;
        return res;
    }

    public synchronized void set_learning_exit(Double expected, int index, Double learning_rate) {
        Double Error_Rate = expected - exit;
        List<Double> previous_influence = new ArrayList<>();

        for (int i = 0; i < previous.size(); i++) {
            Double w = previous.get(i).getActual().weights.get(index);
            Double prevW = previous_weight_sum(index);
            previous_influence.add(Basics.abs(Error_Rate * (w / prevW)));
        }
        for (int i = 0; i < previous.size(); i++) {
            previous.get(i).ErrorsInfluence.add(previous_influence.get(i));
            previous.get(i).NextErrors.add(Error_Rate);
            //previous.get(i).learningHidden(i, learning_rate);
        }

        // -Erase variables
        ErrorsInfluence = new ArrayList<>();
        NextErrors = new ArrayList<>();
    }

    public synchronized void learningHidden(int index, Double learning_rate) {
        if (next.size() == ErrorsInfluence.size()) {
            Double Error_Rate = 0.0;
            for (int i = 0; i < next.size(); i++) {
                //Double IerrNext = NextErrors.get(i) * getActual().weights.get(i);
                //IerrNext /= next_weight_sum();
                //Error_Rate += IerrNext;
                Error_Rate += ErrorsInfluence.get(i);
            }


            List<Double> previous_influence = new ArrayList<>();

            for (int i = 0; i < previous.size(); i++) {
                Double w = previous.get(i).getActual().weights.get(index);
                Double prevW = previous_weight_sum(index);
                previous_influence.add(Basics.abs(Error_Rate * (w / prevW)));
            }
            for (int i = 0; i < previous.size(); i++) {
                previous.get(i).ErrorsInfluence.add(previous_influence.get(i));
                previous.get(i).NextErrors.add(Error_Rate);
                //previous.get(i).learningHidden(i, learning_rate);
            }
            for (int i = 0; i < next.size(); i++) {
                actual.weights.set(i,
                        getActual().weights.get(i) -
                        (NextErrors.get(i) *
                                ErrorsInfluence.get(i)) * learning_rate);
            }

            // -Erase variables
            ErrorsInfluence = new ArrayList<>();
            NextErrors = new ArrayList<>();
        }

    }

    public void random_weight(int nb) {
        actual.random_weight(nb);
    }
}
