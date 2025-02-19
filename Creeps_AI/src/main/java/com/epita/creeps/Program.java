package com.epita.creeps;

import com.epita.creeps.AI_manager.ExitCodes.XOR;
import com.epita.creeps.AI_manager.Manager;
import com.epita.creeps.tool.Graph;
import com.epita.creeps.tool.GraphKeeper;

import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<Integer> layers = new ArrayList<>();
        layers.add(2);
        layers.add(2);
        GraphKeeper keeper = Manager.create_layer(layers);

        List<Double> entries = new ArrayList<>();
        entries.add(0.0);
        entries.add(1.0);

        keeper.evaluate_all(entries);
        List<Graph> exit = keeper.getExit();

        Double exit1 = exit.get(0).getExit();
        Double exit2 = exit.get(1).getExit();

        XOR result = (exit1 >= exit2) ? XOR.TRUE : XOR.FALSE;

        System.out.println("[Program]{Tester XOR} : The result is " + result.toString().toLowerCase());
        System.out.println("\texit1 value was :" + exit1);
        System.out.println("\texit2 value was :" + exit2);
    }
}
