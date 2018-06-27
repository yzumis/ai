package mlp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 22/12/2017.
 */
public class GeneticAlgorithm {

    private final int populationSize;
    private final float mutationRate;
    private final int numberOfInputs;
    private final List<Integer> neuronsPerLayer;

    public GeneticAlgorithm(final int populationSize, final float mutationRate, final int numberOfInputs, final List<Integer> neuronsPerLayer) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.numberOfInputs = numberOfInputs;
        this.neuronsPerLayer = neuronsPerLayer;
    }

    public Mlp evolve(final int numberOfGenerations, final List<EvaluationElement> evaluationElements) {
        List<Individual> population = this.initializePopulation();
        for(int i = 0; i < numberOfGenerations; i++) {
            this.selection(population, evaluationElements);
            population = this.reproduction(population, evaluationElements);
        }
        this.selection(population, evaluationElements);
        return this.selectBestIndividual(population, evaluationElements).getMlp();
    }

    private List<Individual> initializePopulation() {
        final List<Individual> ret = new ArrayList<>();
        for(int i = 0; i < this.populationSize; i++) {
            final Mlp mlp = new Mlp(this.numberOfInputs, this.neuronsPerLayer);
            final Individual individual = new Individual(mlp);
            ret.add(individual);
        }
        return ret;
    }

    private void selection(final List<Individual> population, final List<EvaluationElement> evaluationElements) {
        for(final Individual individual : population) {
            individual.calculateOutputs(evaluationElements);
        }
        for(final Individual individual : population) {
            individual.calculateFitness(population, evaluationElements);
        }
    }

    private List<Individual> reproduction(final List<Individual> population, final List<EvaluationElement> evaluationElements) {
        final List<Individual> ret = new ArrayList<>();
        final float totalPopulationFitness = this.calculateTotalPopulationFitness(population);
        for(int i = 0; i < this.populationSize; i++) {
            // final Individual individual1 = this.pickRandomIndividual(population, totalPopulationFitness);
            final Individual individual1 = this.selectBestIndividual(population, evaluationElements);
            // final Individual individual2 = this.pickRandomIndividual(population, totalPopulationFitness);
            final Individual individual2 = this.selectSecondBestIndividual(individual1, population, evaluationElements);
            ret.add(Individual.reproduce(individual1, individual1, this.mutationRate));
        }
        return ret;
    }

    private float calculateTotalPopulationFitness(final List<Individual> population) {
        float ret = 0;
        for(final Individual individual: population) {
            ret += individual.getFitness();
        }
        return ret;
    }

    private Individual pickRandomIndividual(final List<Individual> population, final float totalPopulationFitness) {
        final float randomFloat = (float)Math.random();
        float currentFitness = 0;
        for(final Individual individual: population) {
            currentFitness += individual.getFitness();
            if(currentFitness / totalPopulationFitness > randomFloat) {
                return individual;
            }
        }
        return population.get(this.populationSize - 1);
    }

    private Individual selectBestIndividual(final List<Individual> population, final List<EvaluationElement> evaluationElements) {
        Individual ret = null;
        for(final Individual individual: population) {
            if((ret == null) || (individual.calculateOutputDifference(evaluationElements) < ret.calculateOutputDifference(evaluationElements))) {
                ret = individual;
            }
        }
        return ret;
    }

    private Individual selectSecondBestIndividual(final Individual bestIndividual, final List<Individual> population, final List<EvaluationElement> evaluationElements) {
        Individual ret = null;
        for(final Individual individual: population) {
            if((ret == null) || (individual.calculateOutputDifference(evaluationElements) < ret.calculateOutputDifference(evaluationElements))) {
                ret = individual;
            }
        }
        return ret;
    }

}
