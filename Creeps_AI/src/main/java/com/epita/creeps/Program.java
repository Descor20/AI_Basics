package com.epita.creeps;

import com.epita.creeps.AI_manager.ExitCodes.XOR;
import com.epita.creeps.AI_manager.Manager;
import com.epita.creeps.AI_manager.Memory.JSON_Memory;
import com.epita.creeps.AI_manager.template.GraphTemplate;
import com.epita.creeps.tool.Basics;
import com.epita.creeps.tool.Graph;
import com.epita.creeps.tool.GraphKeeper;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static java.lang.Thread.sleep;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        /* Create IA Graph */
        List<Integer> layers = new ArrayList<>();
        layers.add(2);
        layers.add(3);
        layers.add(2);
        GraphKeeper keeper = Manager.create_layer(layers);
        //keeper = JSON_Memory.read_graph("save1"); -> Using the Graoh of the Book

        /* Create default entries */
        List<Double> entries = new ArrayList<>();
        entries.add(0.0);
        entries.add(1.0);

        /* Evaluate Once */
        keeper.evaluate_all(entries);
        List<Graph> exit = keeper.getExit();

        Double exit1 = exit.get(0).getExit();
        Double exit2 = exit.get(1).getExit();

        XOR result = (exit1 >= exit2) ? XOR.TRUE : XOR.FALSE;

        System.out.println("[Program]{Tester XOR} : The result is " + result.toString().toLowerCase());
        System.out.println("\texit1 value was :" + exit1);
        System.out.println("\texit2 value was :" + exit2);

        ArrayList<Double> expected = new ArrayList<>();
        expected.add(1.0);
        expected.add(0.0);

        Double max = 10000.0;

        for (double i = 0.0; i < max; i++) {
            if (Basics.modcp(i, max / 1000.0) == 0.0)
                System.out.println("\r---" + i * 100 / max + "%---\r");
            entries.set(0, Basics.binariseDizaine(Basics.RandomD(0.0, 10.0)));
            entries.set(1, Basics.binariseDizaine(Basics.RandomD(0.0, 10.0)));
            //sleep(1);
            /* Learning */
            expected.set(0, (!entries.get(0).equals(entries.get(1))) ? 1.0 : 0.0);
            expected.set(1, (!entries.get(0).equals(entries.get(1))) ? 0.0 : 1.0);
            keeper.apply_correction(expected,0.001);
            //System.out.println("Learning sucess :" + keeper.apply_correction(expected,0.001));

            /* Evaluate Twice */
            keeper.evaluate_all(entries);
            exit = keeper.getExit();

            exit1 = exit.get(0).getExit();
            exit2 = exit.get(1).getExit();

            result = (exit1 >= exit2) ? XOR.TRUE : XOR.FALSE;

            //System.out.println("[Program]{Tester XOR} : The result is " + result.toString().toLowerCase());
            //System.out.println("\texit1 value was :" + exit1);
            //System.out.println("\texit2 value was :" + exit2);

            JSON_Memory.write(keeper, "save2");
            sleep(1);
        }
    }
}
