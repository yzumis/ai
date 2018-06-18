package com.yzumis.geneticmlpimageclassifier;

import com.yzumis.genetic.Individual;
import com.yzumis.genetic.Population;

public class Application {

    private static final int POPULATION_SIZE = 20;
    private static final float MUTATION_RATE = 0.2f;

    public static void main(final String[] args) {
        final ConvolutionalNeuralNetworkIndividualFactory convolutionalNeuralNetworkIndividualFactory = new ConvolutionalNeuralNetworkIndividualFactory();
        final Population population = new Population(convolutionalNeuralNetworkIndividualFactory, POPULATION_SIZE, null, MUTATION_RATE);

        while (true) {
            population.calculateFitness();
            final Individual individual = population.pickBestIndividual();
            System.out.println("Fitness = " + individual.getFitness());
            population.reproduction();
        }
    }

}

