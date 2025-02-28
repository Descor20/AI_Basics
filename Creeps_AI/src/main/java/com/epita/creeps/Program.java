package com.epita.creeps;

import com.epita.creeps.AI_manager.ExitCodes.XOR;
import com.epita.creeps.AI_manager.Manager;
import com.epita.creeps.AI_manager.Memory.JSON_Memory;
import com.epita.creeps.AI_manager.template.GraphTemplate;
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

        GraphTemplate template = Manager.to(keeper);
        System.out.println("");
        /*
        keeper.save("save1");
        keeper = GraphKeeper.retrieve("save1"); // Don't work, is it a copy !?

        keeper.evaluate_all(entries);
        List<Graph> exit = keeper.getExit();

        Double exit1 = exit.get(0).getExit();
        Double exit2 = exit.get(1).getExit();

        XOR result = (exit1 >= exit2) ? XOR.TRUE : XOR.FALSE;

        System.out.println("[Program]{Tester XOR} : The result is " + result.toString().toLowerCase());
        System.out.println("\texit1 value was :" + exit1);
        System.out.println("\texit2 value was :" + exit2);

        keeper.save("save1");
        System.out.println(JSON_Memory.read_text("save1"));
        */

    }
}
