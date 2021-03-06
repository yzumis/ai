package com.yzumis.ai.applications.geneticlstmcolorchoice;

import com.yzumis.ai.applications.common.screen.Screen;
import com.yzumis.ai.applications.geneticlstmcolorchoice.genetic.Population;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.ColorCharacter;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Goal;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Scenario;

public class Application {

    private static final int POPULATION_SIZE = 100;

    public static final int X_SIZE = 700;
    public static final int Y_SIZE = 700;
    private static final double MUTATION_RATE = 0.3f;

    public static void main(final String[] args) throws InterruptedException {
        final Screen screen = new Screen(X_SIZE, Y_SIZE);
        final Goal goal = new Goal();
        final Scenario scenario = new Scenario(goal);
        int generation = 0;
        while(true) {
            final Population population = new Population(screen, POPULATION_SIZE, scenario, goal);
            goal.recalculate();
            scenario.recalculate();
            population.calculateFitness(generation);
            final ColorCharacter bestColorCharacter = population.pickBestColorCharacter();
            final double fitness = bestColorCharacter.getFitness();
            System.out.println("Generation = " + generation + " Best fitness = " + fitness);
            if(generation % 1000 == 0) {
                final ColorCharacter colorCharacter = new ColorCharacter(bestColorCharacter, true);
                executeColorCharacter(generation, goal, scenario, screen, colorCharacter);
            }
            population.reproduction(MUTATION_RATE);
            generation++;
        }
    }

    private static void executeColorCharacter(final int generation, final Goal goal, final Scenario scenario, final Screen screen, final ColorCharacter colorCharacter) throws InterruptedException {
        final Population population = new Population(screen, colorCharacter, scenario, goal);
        population.calculateFitness(generation);
        System.out.println(" Best fitness with new scenario and goal= " + population.pickBestColorCharacter().getFitness());
    }

}
