package com.yzumis.ai.applications.geneticlstmcolorchoice;

import com.yzumis.ai.applications.common.screen.Screen;
import com.yzumis.ai.applications.geneticlstmcolorchoice.genetic.Population;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Goal;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Scenario;

public class Application {

    private static final int NUMBER_OF_GENERATIONS = 100000000;
    private static final int POPULATION_SIZE = 200;

    public static final int X_SIZE = 700;
    public static final int Y_SIZE = 700;
    private static final double MUTATION_RATE = 0.25f;

    public static void main(final String[] args) throws InterruptedException {
        final Goal goal = new Goal();
        final Scenario scenario = new Scenario(goal);
        final Screen screen = new Screen(X_SIZE , Y_SIZE);
        final Population population = new Population(screen, POPULATION_SIZE, scenario, goal);
        for(int i = 0; i < NUMBER_OF_GENERATIONS; i++) {
            goal.recalculate();
            scenario.recalculate();
            population.calculateFitness(i);
            final double fitness = population.pickBestColorCharacter().getFitness();
            System.out.println("Best individual fitness = " + fitness);
            population.reproduction(MUTATION_RATE);
        }
    }

}
