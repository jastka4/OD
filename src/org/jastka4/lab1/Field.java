package org.jastka4.lab1;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final int p;
    private final List<List<Integer>> additionTable;
    private final List<List<Integer>> multiplicationTable;

    public Field(int p) {
        this.p = p;
        this.additionTable = addition(p);
        this.multiplicationTable = multiplication(p);
    }

    public int getP() {
        return p;
    }

    public List<List<Integer>> getAdditionTable() {
        return additionTable;
    }

    public List<List<Integer>> getMultiplicationTable() {
        return multiplicationTable;
    }

    public static List<Integer> getMultiplicativeOrder(int p) {
        List<Integer> multiplicativeOrder = new ArrayList<>();
        for (int i = 1; i < p; i++) {
            int e = 1;
            while (Math.pow(i, e) % p != 1) {
                e++;
            }
            multiplicativeOrder.add(e);
        }

        return multiplicativeOrder;
    }

    public static List<Integer> getPrimitiveElements(int p) {
        List<Integer> primitiveElements = new ArrayList<>();

        for (int i = 1; i < p; i++) {
            int e = 1;
            while (Math.pow(i, e) % p != 1) {
                e++;
            }
            if (p - 1 == e) {
                primitiveElements.add(i);
            }
        }

        return primitiveElements;
    }

    private List<List<Integer>> addition(int p) {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < p; j++) {
                row.add((i + j) % p);
            }
            matrix.add(row);
        }

        return matrix;
    }

    private List<List<Integer>> multiplication(int p) {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < p; j++) {
                row.add((i * j) % p);
            }
            matrix.add(row);
        }

        return matrix;
    }
}
