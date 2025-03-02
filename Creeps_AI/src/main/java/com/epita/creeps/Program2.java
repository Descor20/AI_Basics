package com.epita.creeps;

import com.epita.creeps.AI_manager.ExitCodes.XOR;
import com.epita.creeps.AI_manager.Memory.JSON_Memory;
import com.epita.creeps.tool.Basics;
import com.epita.creeps.tool.Graph;
import com.epita.creeps.tool.GraphKeeper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Program2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Retrieving the graph\n");
        GraphKeeper keeper = JSON_Memory.read_graph("save2");
        System.out.println("The test is starting\n");

        double sucess = 0.0;
        double tryed = 0.0;
        /* Create default entries */
        List<Double> entries = new ArrayList<>();
        entries.add(0.0);
        entries.add(0.0);
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(0.0);
        expected.add(0.0);

        /* Create Variable Storage */
        List<Graph> exit;
        Double exit1;
        Double exit2;
        XOR result;
        XOR result_;

        /* Evaluate Once */
        entries.set(0, 0.0);
        entries.set(1, 0.0);
        expected.set(0, 0.0);
        expected.set(1, 1.0);
        keeper.evaluate_all(entries);
        exit = keeper.getExit();

        exit1 = exit.get(0).getExit();
        exit2 = exit.get(1).getExit();

        result = (exit1 >= exit2) ? XOR.TRUE : XOR.FALSE;
        result_ = (expected.get(0) >= expected.get(1)) ? XOR.TRUE : XOR.FALSE;

        System.out.println("[Program]{Tester XOR} : The result is " + result.toString().toLowerCase() + " expected " + result_.toString().toLowerCase());
        System.out.println("\texit1 value was :" + exit1);
        System.out.println("\texit2 value was :" + exit2);

        /* Evaluate Once */
        entries.set(0, 1.0);
        entries.set(1, 1.0);
        expected.set(0, 0.0);
        expected.set(1, 1.0);
        keeper.evaluate_all(entries);
        exit = keeper.getExit();

        exit1 = exit.get(0).getExit();
        exit2 = exit.get(1).getExit();

        result = (exit1 >= exit2) ? XOR.TRUE : XOR.FALSE;
        result_ = (expected.get(0) >= expected.get(1)) ? XOR.TRUE : XOR.FALSE;

        System.out.println("[Program]{Tester XOR} : The result is " + result.toString().toLowerCase() + " expected " + result_.toString().toLowerCase());
        System.out.println("\texit1 value was :" + exit1);
        System.out.println("\texit2 value was :" + exit2);

        /* Evaluate Once */
        entries.set(0, 0.0);
        entries.set(1, 1.0);
        expected.set(0, 1.0);
        expected.set(1, 0.0);
        keeper.evaluate_all(entries);
        exit = keeper.getExit();

        exit1 = exit.get(0).getExit();
        exit2 = exit.get(1).getExit();

        result = (exit1 >= exit2) ? XOR.TRUE : XOR.FALSE;
        result_ = (expected.get(0) >= expected.get(1)) ? XOR.TRUE : XOR.FALSE;

        System.out.println("[Program]{Tester XOR} : The result is " + result.toString().toLowerCase() + " expected " + result_.toString().toLowerCase());
        System.out.println("\texit1 value was :" + exit1);
        System.out.println("\texit2 value was :" + exit2);

        /* Evaluate Once */
        entries.set(0, 1.0);
        entries.set(1, 0.0);
        expected.set(0, 1.0);
        expected.set(1, 0.0);
        keeper.evaluate_all(entries);
        exit = keeper.getExit();

        exit1 = exit.get(0).getExit();
        exit2 = exit.get(1).getExit();

        result = (exit1 >= exit2) ? XOR.TRUE : XOR.FALSE;
        result_ = (expected.get(0) >= expected.get(1)) ? XOR.TRUE : XOR.FALSE;

        System.out.println("[Program]{Tester XOR} : The result is " + result.toString().toLowerCase() + " expected " + result_.toString().toLowerCase());
        System.out.println("\texit1 value was :" + exit1);
        System.out.println("\texit2 value was :" + exit2);

        //System.out.println("The test is finished :\n\tAttempt :" + tryed + "\n\tSuccess :" + sucess);

    }
}
