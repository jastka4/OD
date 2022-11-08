package org.jastka4.lab2;

import java.util.*;

public class FieldExtension {

    private static final Map<Integer, List<Integer>> ZECH_LOGARITHMS = Map.of(
            4, Arrays.asList(2, 1),
            8, Arrays.asList(3, 6, 1, 5, 4, 2),
            16, Arrays.asList(4, 8, 14, 1, 10, 13, 9, 2, 7, 5, 12, 11, 6, 3)
    );

    private final int m;
    private final int q;

    public FieldExtension(int m) {
        this.m = m;
        this.q = (int) Math.pow(2, m);
    }

    public int getM() {
        return m;
    }

    public List<List<String>> multiplicationMatrix() {
        List<List<String>> matrix = new ArrayList<>();
        matrix.add(Collections.nCopies(q, "0"));
        for (int i = 0; i < q - 1; i++) {
            List<String> row = new ArrayList<>();
            row.add("0");
            for (int j = 0; j < q - 1; j++) {
                int pow = (i + j) % (q - 1);
                if (pow == 0) {
                    row.add("1");
                } else {
                    row.add("a" + pow);
                }
            }
            matrix.add(row);
        }

        return matrix;
    }

    public int S(int x, int y) {
        if (x != 0 && y != 0 && x > y) {
            return (y + Z(x - y) - 1) % (q - 1) + 1;
        } else if (x != 0 && y != 0 && y > x) {
            return (x + Z(y - x) - 1) % (q - 1) + 1;
        } else if (x == 0 || y == 0) {
            return x + y;
        } else {
            return 0;
        }
    }

    public int P(int x, int y) {
        if (x > 0 && y > 0) {
            return 1 + ((x + y - 2) % (q - 1));
        } else {
            return 0;
        }
    }

    public List<List<Integer>> zechMultiplicationMatrix() {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < q; j++) {
                row.add(P(j, i));
            }
            matrix.add(row);
        }

        return matrix;
    }

    public List<List<Integer>> zechAdditionMatrix() {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < q; j++) {
                row.add(S(i, j));
            }
            matrix.add(row);
        }

        return matrix;
    }

    public String getPolynomial(int x1, int x2, int x3, int x4) {
        return "x^4 + "
                + S(S(x1, x2), S(x3, x4)) + "x^3 + "
                + S(P(S(x1, x2), S(x3, x4)), S(P(x1, x2), P(x3, x4))) + "x^2 + "
                + S(P(S(x3, x4), P(x1, x2)), P(S(x1, x2), P(x3, x4))) + "x + "
                + P(P(x1, x2), P(x3, x4));
    }

    private int Z(int x) {
        return ZECH_LOGARITHMS.get(q).get(x - 1);
    }
}
