package com.yzumis.lstm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {

    private List<Individual> individuals;

    public Population(final int populationSize, final String goal, final float mutationRate) {
        this.individuals = initializePopulation(populationSize, goal, mutationRate);
    }

    private static List<Individual> initializePopulation(final int populationSize, final String goal, final float mutationRate) {
        final List<Individual> ret = new ArrayList<>();
        for(int i = 0; i < populationSize; i++) {
            final List<Integer> mlpNeuronsPerLayer = new ArrayList<>();
            mlpNeuronsPerLayer.add(6);
            mlpNeuronsPerLayer.add(3);
            final Lstm lstm = new Lstm(6, 3, mlpNeuronsPerLayer, mlpNeuronsPerLayer, mlpNeuronsPerLayer, mlpNeuronsPerLayer);
            final Individual individual = new Individual(lstm, goal, mutationRate);
            ret.add(individual);
        }
        return ret;
    }

    public void calculateFitness() {
        for (final Individual individual: individuals) {
            individual.calculateFitness();
        }
    }


    public void reproduction() {
        final List<Individual> childIndividuals = new ArrayList<>();
        for(int i = 0; i < this.individuals.size(); i++) {
            final Individual individual1 = this.pickBestIndividual();
            final Individual individual2 = this.pickRandomIndividual();
            childIndividuals.add(individual1.reproduce(individual2));
        }
        this.individuals = childIndividuals;
    }

    private Individual pickBestIndividual() {
        Individual ret = null;
        for(final Individual individual: this.individuals) {
            if(ret == null || ret.getFitness() < individual.getFitness()) {
                ret = individual;
            }
        }
        return ret;
    }

    private Individual pickRandomIndividual() {
        final Random random = new Random();
        final int randomInt = random.nextInt(this.individuals.size());
        return this.individuals.get(randomInt);
    }


}
