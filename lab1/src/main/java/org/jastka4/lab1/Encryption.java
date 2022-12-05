package org.jastka4.lab1;

import java.util.Arrays;

public class Encryption {

    final Field field;
    final int[] polynomial;
    final int[] startingSequence;
    final int t;

    public Field getField() {
        return field;
    }

    public int[] getPolynomial() {
        return polynomial;
    }

    public Encryption(Field field, int[] polynomial, int[] startingSequence) {
        this.field = field;
        this.polynomial = polynomial;
        this.startingSequence = startingSequence;
        this.t = countT();
    }

    public int[] getStartingSequence() {
        return startingSequence;
    }

    public int getT() {
        return t;
    }

    public int[] getPeriodicSequence() {
        int m = startingSequence.length;
        int[] s = Arrays.copyOf(startingSequence, 2 * t + m);

        for (int j = 0; j < 2 * t; j++) {
            for (int i = m; i > 0; i--) {
                s[j + m] = (s[j + m] + (field.getP() - polynomial[m - i]) * s[j + m - i]) % field.getP();
            }
        }

        return s;
    }

    public int getCycleCount(int[] periodicSequence) {
        for (int i = t; i > 0; i--) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (periodicSequence[j + i] == periodicSequence[j]) {
                    count++;
                } else {
                    break;
                }
                if (count == i) {
                    return count;
                }
            }
        }

        return 0;
    }

    public byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i % key.length]);
        }
        return out;
    }

    public String printPolynomial() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = polynomial.length - 1; i >= 0; i--) {
            if (i == 0) {
                if (polynomial[i] != 0) {
                    appendPlus(stringBuilder);
                    stringBuilder.append(polynomial[i]);
                }
            } else if (i - 1 == 0) {
                if (polynomial[i] != 0) {
                    appendPlus(stringBuilder);
                    appendCoefficient(stringBuilder, i);
                    stringBuilder.append("x");
                }
            } else {
                if (polynomial[i] != 0) {
                    appendPlus(stringBuilder);
                    appendCoefficient(stringBuilder, i);
                    stringBuilder.append("x^").append(i);
                }
            }
        }

        return stringBuilder.toString();
    }

    private int countT() {
        return (int) (Math.pow(field.getP(), startingSequence.length) - 1);
    }

    private void appendCoefficient(StringBuilder stringBuilder, int i) {
        if (polynomial[i] != 1) {
            stringBuilder.append(polynomial[i]);
        }
    }

    private static void appendPlus(StringBuilder stringBuilder) {
        if (!stringBuilder.isEmpty()) {
            stringBuilder.append("+");
        }
    }
}
