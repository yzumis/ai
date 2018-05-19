package com.yzumis.geneticlstm;

import com.yzumis.genetic.Population;

public class Application {

    private static final int POPULATION_SIZE = 20;
    private static final float MUTATION_RATE = 0.2f;
    private static final String GOAL = "abacabacabacabacabac";

    public static void main(final String[] args) {
        final LstmIndividualFactory lstmIndividualFactory = new LstmIndividualFactory();
        final Population population = new Population(lstmIndividualFactory, POPULATION_SIZE, GOAL, MUTATION_RATE);
        while(true) {
            population.calculateFitness();
            final LstmIndividual lstmIndividual = (LstmIndividual) population.pickBestIndividual();
            if(lstmIndividual.getFitness() == 19) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILURE = " + lstmIndividual.getFitness());
            }
            population.reproduction();
        }
    }

}
