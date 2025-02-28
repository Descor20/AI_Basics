package com.epita.creeps.tool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

public class Basics {
    public static int _seed;

    /* List and String Methods */
    public static boolean contain(String wrapper, String object) {
        int i = 0;
        for (int j = 0; j < wrapper.length() && i < object.length(); j++) {
            if (wrapper.charAt(j) == object.charAt(i)) {
                i++;
            }
            else
                i = 0;
        }
        return i == object.length();
    }

    public static List<Double> copy_D(List<Double> ToCopy) {
        if (ToCopy == null)
            return null;
        List<Double> exit = new ArrayList<>();
        exit.addAll(ToCopy);
        return exit;
    }

    /* Maths Methods */
    public static int RoundUp(float waitingTicks) {
        BigDecimal result = new BigDecimal(waitingTicks);
        result = result.setScale(0, RoundingMode.UP);
        return result.intValue();
    }

    public static int RoundDown(float waitingTicks) {
        BigDecimal result = new BigDecimal(waitingTicks);
        result = result.setScale(0, RoundingMode.DOWN);
        return result.intValue();
    }

    public static int RoundUp(Double waitingTicks) {
        BigDecimal result = new BigDecimal(waitingTicks);
        result = result.setScale(0, RoundingMode.UP);
        return result.intValue();
    }

    public static int RoundDown(Double waitingTicks) {
        BigDecimal result = new BigDecimal(waitingTicks);
        result = result.setScale(0, RoundingMode.DOWN);
        return result.intValue();
    }

    public static long RoundUpLong(Double waitingTicks) {
        BigDecimal result = new BigDecimal(waitingTicks);
        result = result.setScale(0, RoundingMode.UP);
        return result.longValue();
    }

    public static long RoundDownLong(Double waitingTicks) {
        BigDecimal result = new BigDecimal(waitingTicks);
        result = result.setScale(0, RoundingMode.DOWN);
        return result.longValue();
    }

    public static int abs(int x) {
        return (x >= 0) ? x : -x;
    }

    /* Move Methods */
    public static Double euclidian_distance(int start_x, int start_y, int end_x, int end_y) {
        return Math.sqrt(Math.pow(start_x - end_x, 2) + Math.pow(start_y - end_y, 2));
    }

    public static String get_direction (int start_x, int start_y, int end_x, int end_y) {
        if (start_x < end_x) {
            if (start_y < end_y) {
                return (end_x - start_x > end_y - start_y) ? "right" : "up";
            }
            else {
                return (end_x - start_x > start_y - end_y) ? "right" : "down";
            }
        } else if (start_x > end_x) {
            if (start_y < end_y) {
                return (start_x - end_x  > end_y - start_y) ? "left" : "up";
            }
            else {
                return (start_x - end_x > start_y - end_y) ? "left" : "down";
            }
        }
        else {
            if (start_y < end_y) {
                return "up";
            }
            else if (start_y > end_y) {
                return "down";
            }
        }
        return "same";
    }

    public static boolean is_same (int start_x, int start_y, int end_x, int end_y) {
        return start_x == end_x && start_y == end_y;
    }

    public static boolean is_unknown (int start_x, int start_y) {
        return start_x == -1 || start_y == -1;
    }

    /* Graph Methods */
    public static boolean contains (List<Graph> l, int id) {
        for (Graph g : l) {
            if (g.id == id)
                return true;
        }
        return false;
    }

    public static List<Graph> copy_G(List<Graph> ToCopy) {
        if (ToCopy == null)
            return null;
        List<Graph> exit = new ArrayList<>();
        exit.addAll(ToCopy);
        return exit;
    }

    /* Pseudo Random */
    public synchronized static int Random() {
        if (_seed == 0)
            _seed = LocalTime.now().getSecond();
        _seed =  RoundDown(_seed * LocalTime.now().getMinute() + LocalTime.now().getNano() * (-1 * (random() % 2)));
        return _seed;
    }

    public synchronized static int Random(int max) {
        if (_seed == 0)
            _seed = LocalTime.now().getSecond();
        Random();
        return _seed % max;
    }

    public synchronized static int Random(int min, int max) {
        if (_seed == 0)
            _seed = LocalTime.now().getSecond();
        Random();
        int res = _seed % max;
        while (res < min)
            res++;
        return res;
    }

    /* Cast Methods */
    public static Double to_Double(int x) {
        return x + 0.0;
    }
}
