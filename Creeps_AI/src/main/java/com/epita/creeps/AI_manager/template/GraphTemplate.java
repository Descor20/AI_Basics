package com.epita.creeps.AI_manager.template;

import com.epita.creeps.tool.Graph;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"bias", "weigth"})
public class GraphTemplate {
    public List<List<Double>> bias;
    public List<List<List<Double>>> weigth;

    public GraphTemplate() {
    }

    public GraphTemplate(List<List<Double>> bias, List<List<List<Double>>> weigth) {
        this.bias = bias;
        this.weigth = weigth;
    }

    public List<List<Double>> getBias() {
        return bias;
    }

    public List<List<List<Double>>> getWeigth() {
        return weigth;
    }

    public void setBias(List<List<Double>> bias) {
        this.bias = bias;
    }

    public void setWeigth(List<List<List<Double>>> weigth) {
        this.weigth = weigth;
    }
}
