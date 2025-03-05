package com.epita.creeps.tool;

import com.epita.creeps.AI_manager.Manager;
import org.w3c.dom.ranges.RangeException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Node {
    public Double bias;
    List<Double> weights;

    /* Getter */
    public Double getBias() {
        return bias;
    }

    public List<Double> getWeights() {
        return weights;
    }

    /* Setter */
    public synchronized void setBias(Double bias) {
        this.bias = bias;
    }

    public synchronized void setWeights(List<Double> weights) {
        this.weights = weights;
    }
    public void random_weight(int nb) {
        if (weights == null)
            weights = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            Double w = Basics.RandomD(-3.0, 3.0);
            int overloader = 0;
            while (w == 0.0) {
                overloader++;
                if (overloader == Integer.MAX_VALUE) {
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.err.println("[Node]{Random_Weight} :The randomizer failed to get a weigth at index " + i);
                    throw new RuntimeException();
                }
                w = Basics.RandomD(-3.0, 3.0);
            }
            weights.add(w);
        }
    }

    /* Constructor */
    public Node() {
        bias = Basics.to_Double(Basics.Random(-50, 50));
        weights = new ArrayList<>();
    }

    public Node(Double bias) {
        this.bias = bias;
        weights = new ArrayList<>();
    }

    public Node(Double bias, List<Double> weights) {
        this.bias = bias;
        this.weights = weights;
    }
}
