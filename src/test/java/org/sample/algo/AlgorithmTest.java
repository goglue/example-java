package org.sample.algo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by moath on 29.06.17.
 */
public class AlgorithmTest {
    private String EOL = System.getProperty("line.separator");
    private char[][] s1cases = {
            {'e', 'a', 'e', 'a'},
            {'e', 'a', 'e', 'a'},
            {'a', 'e', 'x'},
            {'a', 'b', 'c', 'd'},
    };
    private char[][] s2cases = {
            {'a', 'a', 'e', 'a', 'e', 'a'},
            {'e', 'a', 'e', 'a', 'a', 'a'},
            {'a', 'x', 'e'},
            {'a', 'c', 'b', 'd'},
    };
    private String[] results = {
            "insert a" + EOL + "insert a" + EOL,
            "insert a" + EOL + "insert a" + EOL,
            "insert x" + EOL + "delete x" + EOL,
            "insert c" + EOL + "delete c" + EOL,
    };

    @Test
    public void testCalculate() {
        for (int i = 0; i < this.s1cases.length; i++) {
            StreamMock mock = new StreamMock();
            Algorithm algorithm = new Algorithm(mock);
            algorithm.calculateRecursively(s1cases[i], s2cases[i], 0, 0, 0);

            assertEquals(results[i], mock.getBuffer());
        }
    }
}
