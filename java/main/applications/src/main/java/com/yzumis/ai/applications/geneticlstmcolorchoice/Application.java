package com.yzumis.ai.applications.geneticlstmcolorchoice;

import com.yzumis.ai.applications.common.screen.Screen;
import com.yzumis.ai.applications.geneticlstmcolorchoice.genetic.Population;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.ColorCharacter;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Goal;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Scenario;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.train.EmptyDetector;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.train.GoalDetector;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.train.GoalMemory;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.train.factories.EmptyDetectorFactory;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.train.factories.GoalDetectorFactory;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.train.factories.GoalMemoryFactory;
import com.yzumis.ai.genetic.Individual;
import com.yzumis.ai.lstm.Lstm;
import com.yzumis.ai.mlp.Mlp;

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
            if(generation % 100 == 0) {
                final ColorCharacter colorCharacter = new ColorCharacter(bestColorCharacter);
                executeColorCharacter(generation, screen, colorCharacter);
            }
            population.reproduction(MUTATION_RATE);
            generation++;
        }
    }

    private static void executeColorCharacter(final int generation, final Screen screen, final ColorCharacter colorCharacter) throws InterruptedException {
        final Goal goal = new Goal();
        final Scenario scenario = new Scenario(goal);
        final Population population = new Population(screen, colorCharacter, scenario, goal);
        population.calculateFitness(generation);
        System.out.println(" Best fitness with new scenario and goal= " + population.pickBestColorCharacter().getFitness());
    }

}
