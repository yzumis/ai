package driving;

import driving.genetic.Population;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 27/12/2017.
 */
public class Application {

    private static final Track TRACK = new Track();
    private static final int NUMBER_OF_GENERATIONS = 30;
    private static final int POPULATION_SIZE = 50;
    private static final float MUTATION_RATE = 0.3f;

    public static void main(String[] args) throws InterruptedException {
        final Population population = new Population(POPULATION_SIZE, TRACK);
        for(int i = 0; i < NUMBER_OF_GENERATIONS; i++) {
            population.calculateFitness(i);
            population.reproduction(MUTATION_RATE);
        }
    }

}