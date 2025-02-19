package com.epita.creeps.AI_manager;

import com.epita.creeps.tool.Graph;
import com.epita.creeps.tool.GraphKeeper;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    public static GraphKeeper create_layer(List<Integer> layers) {
        GraphKeeper keeper = new GraphKeeper();

        List<List<Graph>> schema = new ArrayList<>();
        for (int i = 0; i < layers.size(); i++) {
            List<Graph> layer = new ArrayList<>();
            for (int j = 0; j < layers.get(i); j++) {
                layer.add(new Graph());
            }
            schema.add(layer);
        }

        for (int i = 0; i < layers.size() - 1; i++) {
            for (int j = 0; j < layers.get(i); j++) {
                schema.get(i).get(j).setNext(schema.get(i+1));
                schema.get(i).get(j).random_weight(schema.get(i+1).size());
                schema.get(i+1).get(j).setPrevious(schema.get(i));
            }
        }

        keeper.setEntries(schema.getFirst());
        keeper.setExit(schema.getLast());
        return keeper;
    }
}
