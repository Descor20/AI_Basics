package com.epita.creeps.AI_manager;

import com.epita.creeps.AI_manager.template.GraphTemplate;
import com.epita.creeps.tool.Basics;
import com.epita.creeps.tool.Graph;
import com.epita.creeps.tool.GraphKeeper;
import com.epita.creeps.tool.Node;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    /* ----- Graph Constructor ----- */
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
            }
        }
        for (int i = 1; i < layers.size(); i++) {
            for (int j = 0; j < layers.get(i); j++) {
                schema.get(i).get(j).setPrevious(schema.get(i-1));
            }
        }

        if (!schema.isEmpty())
            keeper.setEntries(schema.get(0));
        if (!schema.isEmpty())
            keeper.setExit(schema.get(schema.size() - 1));
        return keeper;
    }


    /* ----- Graph Re-Constructor ----- */
    /* - Data Retriever - */
    public static GraphKeeper extract_bias(GraphKeeper keeper, GraphTemplate template) {
        List<Graph> entry = keeper.getEntries();
        for (int i = 0; i < template.getBias().size(); i++) {
            for (int j = 0; j < template.getBias().get(i).size(); j++) {
                entry.get(j).getActual().setBias(template.getBias().get(i).get(j));
            }
            entry = entry.get(0).getNext();
        }
        return keeper;
    }
    public static GraphKeeper extract_weight(GraphKeeper keeper, GraphTemplate template) {
        List<Graph> entry = keeper.getEntries();
        for (int i = 0; i < template.getBias().size(); i++) {
            for (int j = 0; j < template.getBias().get(i).size(); j++) {
                entry.get(j).getActual().setWeights(template.getWeigth().get(i).get(j));
            }
            entry = entry.get(0).getNext();
        }
        return keeper;
    }

    /* - Re-Constructor - */
    public static GraphKeeper from(GraphTemplate template) {
        if (template == null) {
            System.err.println("[MemoryManager]{to keeper} :template was empty or null.");
            return null;
        }

        List<Integer> model = new ArrayList<>();
        for (int i = 0; i < template.getBias().size(); i++) {
            model.add(template.bias.get(i).size());
        }

        GraphKeeper keeper = create_layer(model);
        keeper = extract_bias(keeper, template);
        keeper = extract_weight(keeper, template);
        return keeper;
    }


    /* ----- Graph Saver ----- */
    /* - Data Saver - */
    private static GraphTemplate place_bias(GraphTemplate template, List<Graph> list) {
        List<Double> line = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            line.add(list.get(i).getActual().bias);
        }
        if (template.bias == null)
            template.bias = new ArrayList<>();
        template.bias.add(line);
        return template;
    }
    private static GraphTemplate place_weight(GraphTemplate template, List<Graph> graphs) {
        List<List<Double>> list = new ArrayList<>();
        for (int i = 0; i < graphs.size(); i++) {
            list.add(graphs.get(i).getActual().getWeights());
        }
        if (template.weigth == null)
            template.weigth = new ArrayList<>();
        template.weigth.add(list);
        return template;
    }

    /* - Saver - */
    public static GraphTemplate to(GraphKeeper keeper) {
        if (keeper == null) {
            System.err.println("[MemoryManager]{from keeper} :keeper was empty or null.");
            return null;
        }
        GraphTemplate template = new GraphTemplate();
        template.weigth = new ArrayList<>();

        List<Graph> entry = keeper.getEntries();

        while (entry != null && !entry.isEmpty()) {
            template = place_bias(template, entry);
            template = place_weight(template, entry);
            entry = entry.get(0).getNext();
        }
        return template;
    }
}
