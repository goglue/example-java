package org.sample.algo;

public class AlgorithmApp {

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm(System.out);

        algorithm.calculateRecursively(
                "abcd".toCharArray(),
                "acbd".toCharArray(),
                0,
                0,
                0
        );
    }

}
