package com.yzumis.ai.applications.geneticmlpcar;

import com.yzumis.ai.applications.geneticmlpcar.genetic.Population;
import com.yzumis.ai.applications.common.screen.Screen;
import com.yzumis.ai.applications.geneticmlpcar.object.Track;

public class Application {

    private static final Track TRACK = new Track();
    private static final int NUMBER_OF_GENERATIONS = 50;
    private static final int POPULATION_SIZE = 100;
    private static final double MUTATION_RATE = 0.3f;

    public static void main(final String[] args) throws InterruptedException {
        final Screen screen = new Screen();
        final Population population = new Population(screen, POPULATION_SIZE, TRACK);
        for(int i = 0; i < NUMBER_OF_GENERATIONS; i++) {
            population.calculateFitness(i);
            population.reproduction(MUTATION_RATE);
        }
    }

}
