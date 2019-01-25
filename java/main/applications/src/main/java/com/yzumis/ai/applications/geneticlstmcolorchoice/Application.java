package com.yzumis.ai.applications.geneticlstmcolorchoice;

import com.yzumis.ai.applications.common.screen.Screen;
import com.yzumis.ai.applications.geneticlstmcolorchoice.genetic.Population;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Scenario;

public class Application {

    private static final int NUMBER_OF_GENERATIONS = 100000000;
    private static final int POPULATION_SIZE = 200;
    private static final double MUTATION_RATE = 0.25f;

    public static void main(final String[] args) throws InterruptedException {
        final Scenario scenario = new Scenario();
        final Screen screen = new Screen();
        final Population population = new Population(screen, POPULATION_SIZE, scenario);
        for(int i = 0; i < NUMBER_OF_GENERATIONS; i++) {
            scenario.recalculate();
            population.calculateFitness(i);
            final double fitness = population.pickBestColorCharacter().getFitness();
            System.out.println("Best individual fitness = " + fitness);
            population.reproduction(MUTATION_RATE);
        }
    }

}
