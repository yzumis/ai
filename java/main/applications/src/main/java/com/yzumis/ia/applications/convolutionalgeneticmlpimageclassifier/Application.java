package com.yzumis.ia.applications.convolutionalgeneticmlpimageclassifier;

import com.yzumis.ia.genetic.Individual;
import com.yzumis.ia.genetic.Population;

public class Application {

    private static final int POPULATION_SIZE = 100;
    private static final double MUTATION_RATE = 0.5f;

    public static void main(final String[] args) {
        final ConvolutionalNeuralNetworkIndividualFactory convolutionalNeuralNetworkIndividualFactory = new ConvolutionalNeuralNetworkIndividualFactory();
        final Population population = new Population(convolutionalNeuralNetworkIndividualFactory, POPULATION_SIZE, null, MUTATION_RATE);
        int generation = 0;

        while (true) {
            population.calculateFitness();
            final Individual individual = population.pickBestIndividual();
            System.out.println("Generation = " + generation);
            System.out.println("Fitness = " + individual.getFitness());
            population.reproduction();
            generation++;
        }
    }

}

