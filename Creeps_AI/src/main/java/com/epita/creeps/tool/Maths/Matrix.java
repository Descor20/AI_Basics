package com.epita.creeps.tool.Maths;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private List<List<Double>> data;
    private int nb_col;
    private int nb_row;

    /* Getter */
    public List<Double> get_row(int i) {
        //TODO :Implement function
        return null;
    }

    public List<Double> get_col(int i) {
        if (i < 0 || data == null || i >= data.size())
            return null;
        return data.get(i);
    }

    public List<List<Double>> getData() {
        return data;
    }

    public int getNb_col() {
        return nb_col;
    }

    public int getNb_row() {
        return nb_row;
    }

    /* Setter */
    public void setData(List<List<Double>> data) {
        this.data = data;
    }

    public int add_row(List<Double>row) {
        if (data == null)
            data = new ArrayList<>();
        if (data.isEmpty()) {
            for (int i = 0; i < row.size(); i++) {
                //TODO
            }
        }
        return -1;
    }

    public int add_col(List<Double> col) {
        if (data == null)
            data = new ArrayList<>();
        int tmp = data.size();
        data.add(col);
        nb_col++;
        return tmp;
    }

    /* Methods */ //TODO
    public static Matrix add(Matrix m1, Matrix m2) {
        return null;
    }
    public static Matrix sub(Matrix m1, Matrix m2) {
        return null;
    }
    public static Matrix mul(Matrix m1, Matrix m2) {
        return null;
    }
    public static Matrix trans(Matrix m) {
        return null;
    }
}
