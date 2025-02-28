package com.epita.creeps.AI_manager.template;

import com.epita.creeps.tool.Graph;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"bias", "weigth"})
public class GraphTemplate {
    public List<List<Double>> bias;
    public List<List<List<Double>>> weigth;


}
