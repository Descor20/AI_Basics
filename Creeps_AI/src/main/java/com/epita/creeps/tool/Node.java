package com.epita.creeps.tool;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public Double bias;
    List<Double> weights;

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

    public void random_weight(int nb) {
        if (weights == null)
            weights = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            weights.add(Basics.to_Double(Basics.Random(-50, 50)));
        }
    }
}
