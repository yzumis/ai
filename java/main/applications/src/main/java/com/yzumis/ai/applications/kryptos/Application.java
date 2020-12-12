package com.yzumis.ai.applications.kryptos;

import com.yzumis.ai.genetic.Population;

public class Application {

    private static final int POPULATION_SIZE = 20;
    private static final double MUTATION_RATE = 0.2f;
    private static final String GOAL = ".........................NORTHEAST.............................BERLINCLOCK.......................";

    public static void main(final String[] args) {
        final MlpIndividualFactory mlpIndividualFactory = new MlpIndividualFactory();
        final Population population = new Population(mlpIndividualFactory, POPULATION_SIZE, GOAL, MUTATION_RATE);
        int currentIteration = 0;
        while(true) {
            System.out.println();
            population.calculateFitness();
            System.out.println("Iteration: " + currentIteration + " . Fitness: " + population.pickBestIndividual().getFitness());
            population.reproduction();
            currentIteration++;
        }
    }
}
