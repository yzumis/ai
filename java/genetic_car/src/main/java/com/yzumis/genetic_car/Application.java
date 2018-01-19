package com.yzumis.genetic_car;

import com.yzumis.genetic_car.genetic.Population;
import com.yzumis.genetic_car.objects.Screen;
import com.yzumis.genetic_car.objects.Track;

/**
 * Created by Yzumi on 27/12/2017.
 */
public class Application {

    private static final Track TRACK = new Track();
    private static final int NUMBER_OF_GENERATIONS = 30;
    private static final int POPULATION_SIZE = 100;
    private static final float MUTATION_RATE = 0.3f;

    public static void main(final String[] args) throws InterruptedException {
        final Screen screen = new Screen();
        final Population population = new Population(screen, POPULATION_SIZE, TRACK);
        for(int i = 0; i < NUMBER_OF_GENERATIONS; i++) {
            population.calculateFitness(i);
            population.reproduction(MUTATION_RATE);
        }
    }

}