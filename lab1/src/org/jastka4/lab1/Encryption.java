package org.jastka4.lab1;

import java.util.Arrays;

public class Encryption {

    final Field field;
    final int[] polynomial;
    final int[] startingSequence;
    final int t;

    public Encryption(Field field, int[] polynomial, int[] startingSequence) {
        this.field = field;
        this.polynomial = polynomial;
        this.startingSequence = startingSequence;
        this.t = countT();
    }

    public Field getField() {
        return field;
    }

    public int[] getPolynomial() {
        return polynomial;
    }

    public int[] getStartingSequence() {
        return startingSequence;
    }

    public int getT() {
        return t;
    }

    public int[] getPeriodicSequence() {
        int[] s = Arrays.copyOf(startingSequence, 20 + startingSequence.length + 1);
        int m = startingSequence.length;

        for (int j = 0; j < 20; j++) {
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

    private int countT() {
        return (int) (Math.pow(field.getP(), startingSequence.length) - 1);
    }
}
