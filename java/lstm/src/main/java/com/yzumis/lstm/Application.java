package com.yzumis.lstm;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final int POPULATION_SIZE = 20;
    private static final float MUTATION_RATE = 0.2f;
    private static final String GOAL = "abacabacabac";

    public static void main(final String[] args) {
        final Population population = new Population(POPULATION_SIZE, GOAL, MUTATION_RATE);
        while(true) {
            population.calculateFitness();
            population.reproduction();
        }
    }



}
