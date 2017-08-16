package org.sample.algo;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by moath on 29.06.17.
 */
public class Algorithm {

    private OutputStream writer;

    private String EOL = System.getProperty("line.separator");

    public Algorithm(OutputStream writer) {
        this.writer = writer;
    }

    /**
     * Calculates the minimum steps to turn s1 to s2
     *
     * @param s1 The string need to be transformed
     * @param s2 The expected string after steps of transformation
     */
    public void calculate(char[] s1, char[] s2) {
//        boolean delete = true;
//        int lastIndex = -1;
//        for (int i = 0; i < s1.length; i++) {
//            for (int j = i; j < s2.length; j++) {
//                if (j <= lastIndex) continue;
//                if (s1[i] != s2[j]) continue;
//
//                delete = false;
//                if (i == j) break;
//                if (j - 1 == lastIndex) {
//                    lastIndex++;
//                    break;
//                }
//                // insert from [s2[i] to s2[j])
//                insert(s2, i, j - 1);
//                lastIndex = j;
//                break;
//            }
//            if (delete) {
//                delete(s1, i);
//            }
//            delete = true;
//        }
    }

    public void calculateRecursively(char[] s1, char[] s2, int i, int j, int l) {
        if (i == s1.length && j == s2.length) {
            return;
        }

        if (s2.length == j) {
            if (s2[j - 1] != s1[i]) {
                delete(s1, i);
            }
            calculateRecursively(s1, s2, i + 1, j, l);
            return;
        }
        if (s1.length == i) {
            insert(s2, j, s2.length - 1);
            return;
        }
        if (s1[i] != s2[j]) {
            calculateRecursively(s1, s2, i, j + 1, l);
            return;
        }
        if (i == j) {
            calculateRecursively(s1, s2, i + 1, j + 1, j);
            return;
        }
        if (j - 1 != l) {
            insert(s2, i, j - 1);
            l = j;
        } else {
            l = j;
        }

        calculateRecursively(s1, s2, i + 1, j + 1, l);
    }

    private void delete(char[] s, int i) {
        write("delete " + s[i]);
    }

    private void insert(char[] s, int i, int j) {
        for (; i <= j; i++) {
            write("insert " + s[i]);
        }
    }

    private void write(String step) {
        try {
            this.writer.write((step + EOL).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
