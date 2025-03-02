package com.epita.creeps;

import com.epita.creeps.AI_manager.ExitCodes.XOR;
import com.epita.creeps.AI_manager.Memory.JSON_Memory;
import com.epita.creeps.tool.Basics;
import com.epita.creeps.tool.Graph;
import com.epita.creeps.tool.GraphKeeper;

import java.util.ArrayList;
import java.util.List;

public class Program2 {
    public static void main(String[] args) {
        System.out.println("Retrieving the graph");
        GraphKeeper keeper = JSON_Memory.read_graph("save2");
        System.out.println("The test is starting");

        double sucess = 0.0;
        double tryed = 0.0;
        List<Double> entries = new ArrayList<>();
        entries.add(0.0);
        entries.add(0.0);
        ArrayList<Double> expected = new ArrayList<>(2);
        expected.add(0.0);
        expected.add(0.0);
        for (double i = 0.0; i < 10000; i++) {
            /* Define Result and Test */
            entries.set(0, Basics.binarise(Basics.RandomD(0.0, 1.0)));
            entries.set(1, Basics.binarise(Basics.RandomD(0.0, 1.0)));
            expected.set(0, (!entries.get(0).equals(entries.get(1))) ? 1.0 : 0.0);
            expected.set(1, (!entries.get(0).equals(entries.get(1))) ? 0.0 : 1.0);

            System.out.println("The entries are " + entries.get(0) + " / " + entries.get(1));

            /* Launch test */
            keeper.evaluate_all(entries);

            /* Get Answers */
            List<Graph> exit = keeper.getExit();
            Double exit1 = exit.get(0).getExit();
            Double exit2 = exit.get(1).getExit();
            XOR result = (exit1 >= exit2) ? XOR.TRUE : XOR.FALSE;

            /* Compare Answers */
            XOR result_expect = (expected.get(0) >= expected.get(1)) ? XOR.TRUE : XOR.FALSE;
            tryed++;
            if (result_expect == result)
                sucess++;
        }

        System.out.println("The test is finished :\n\tAttempt :" + tryed + "\n\tSuccess :" + sucess);

    }
}
