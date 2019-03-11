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

    private static final int NUMBER_OF_GENERATIONS = 100000000;
    private static final int POPULATION_SIZE = 100;

    public static final int X_SIZE = 700;
    public static final int Y_SIZE = 700;
    private static final double MUTATION_RATE = 0.3f;

    public static void main(final String[] args) throws InterruptedException {
        final GoalDetector goalDetector = calculateGoalDetector();
        final EmptyDetector emptyDetector = calculateEmptyDetector();
        final GoalMemory goalMemory = calculateGoalMemory();
        final Screen screen = new Screen(X_SIZE, Y_SIZE);
        int iteration = 0;
        while (true) {
            Thread.sleep(500);
            final Goal goal = new Goal();
            final Scenario scenario = new Scenario(goal);
            final ColorCharacter colorCharacter = new ColorCharacter(scenario, goal, new Lstm((Lstm) goalMemory.getReproducible()), (Mlp) goalDetector.getReproducible(), (Mlp) emptyDetector.getReproducible());
            final Population population = new Population(screen, colorCharacter, scenario, goal);
            goal.recalculate();
            scenario.recalculate();
            population.calculateFitness(iteration);
            final double fitness = population.pickBestColorCharacter().getFitness();
            System.out.println("Iteration = " + iteration + " Best fitness = " + fitness);
            iteration++;
        }
    }

    private static GoalDetector calculateGoalDetector() {
        final GoalDetectorFactory goalDetectorFactory = new GoalDetectorFactory();
        final com.yzumis.ai.genetic.Population population = new com.yzumis.ai.genetic.Population(goalDetectorFactory, 100, 0.95, 0.3);
        int iteration = 0;
        GoalDetector ret = null;
        while(ret == null || ret.getFitness() < 0.95) {
            population.calculateFitness();
            ret = (GoalDetector) population.pickBestIndividual();
            final double fitness = ret.getFitness();
            population.reproduction();
            System.out.println("GoalDetector { iteration = " + iteration + ", fitness = " + fitness + " }");
            iteration++;
        }
        return ret;
    }

    private static EmptyDetector calculateEmptyDetector() {
        final EmptyDetectorFactory emptyDetectorFactory = new EmptyDetectorFactory();
        final com.yzumis.ai.genetic.Population population = new com.yzumis.ai.genetic.Population(emptyDetectorFactory, 100, 0.95, 0.3);
        int iteration = 0;
        EmptyDetector ret = null;
        while(ret == null || ret.getFitness() < 0.95) {
            population.calculateFitness();
            ret = (EmptyDetector) population.pickBestIndividual();
            final double fitness = ret.getFitness();
            population.reproduction();
            System.out.println("EmptyDetector { iteration = " + iteration + ", fitness = " + fitness + " }");
            iteration++;
        }
        return ret;
    }

    private static GoalMemory calculateGoalMemory() {
        final GoalMemoryFactory goalMemoryFactory = new GoalMemoryFactory();
        final com.yzumis.ai.genetic.Population population = new com.yzumis.ai.genetic.Population(goalMemoryFactory, 100, 0.95, 0.3);
        int iteration = 0;
        GoalMemory ret = null;
        while(ret == null || ret.getFitness() < 0.95) {
            population.calculateFitness();
            ret = (GoalMemory) population.pickBestIndividual();
            final double fitness = ret.getFitness();
            population.reproduction();
            System.out.println("GoalMemory { iteration = " + iteration + ", fitness = " + fitness + " }");
            iteration++;
        }
        return ret;
    }

}
