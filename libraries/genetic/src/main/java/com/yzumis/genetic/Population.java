package com.yzumis.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population<G> {

    private List<Individual> individuals;

    public Population(final IndividualFactory individualFactory, final int populationSize, final G goal, final double mutationRate) {
        this.individuals = initializePopulation(individualFactory, populationSize, goal, mutationRate);
    }

    private List<Individual> initializePopulation(final IndividualFactory individualFactory, final int populationSize, final G goal, final double mutationRate) {
        final List<Individual> ret = new ArrayList<>();
        for(int i = 0; i < populationSize; i++) {
            final List<Integer> mlpNeuronsPerLayer = new ArrayList<>();
            mlpNeuronsPerLayer.add(6);
            mlpNeuronsPerLayer.add(3);
            final Individual individual = individualFactory.createIndividual(mutationRate, goal);
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

    public Individual pickBestIndividual() {
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
