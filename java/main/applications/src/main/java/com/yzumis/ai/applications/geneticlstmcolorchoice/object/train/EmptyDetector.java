package com.yzumis.ai.applications.geneticlstmcolorchoice.object.train;

import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Input;
import com.yzumis.ai.commonneuron.BaseNeuronFactory;
import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Individual;
import com.yzumis.ai.genetic.Reproducible;
import com.yzumis.ai.mlp.Mlp;
import com.yzumis.ai.neuron.NeuronFactory;

import java.util.ArrayList;
import java.util.List;

public class EmptyDetector extends Individual<Mlp, Double> {

    private static final int MLP_EMPTY_DETECTOR_NUMBER_OF_INPUTS = 10;
    private static final List<Integer> MLP_EMPTY_DETECTOR_NEURONS_PER_LAYER = new ArrayList<>();
    private static final BaseNeuronFactory BASE_NEURON_FACTORY = new NeuronFactory();

    static {
        MLP_EMPTY_DETECTOR_NEURONS_PER_LAYER.add(10);
        MLP_EMPTY_DETECTOR_NEURONS_PER_LAYER.add(1);
    }

    public EmptyDetector(final double mutationRate, final Double goal) {
        this(new Mlp(MLP_EMPTY_DETECTOR_NUMBER_OF_INPUTS, MLP_EMPTY_DETECTOR_NEURONS_PER_LAYER, BASE_NEURON_FACTORY), mutationRate, goal);
    }

    public EmptyDetector(final Mlp mlp, final double mutationRate, final Double goal) {
        super(mlp, mutationRate, goal);
    }

    @Override
    public void calculateFitness() {
        double maximumFitness = 0;
        double fitness = 0;
        final Mlp mlp = (Mlp)this.getReproducible();
        for(final Input input: Input.values()) {
            final Vector outPuts = mlp.calculateOutputs(input.toVector());
            final double expectedOutput = calculateExpectedOutput(input);
            final double output = outPuts.get(0);
            fitness += Math.abs(expectedOutput - output);
            maximumFitness++;
        }
        this.setFitness(fitness / maximumFitness);
    }

    private double calculateExpectedOutput(final Input input) {
        final double ret;
        if(input.equals(Input.EMPTY)) {
            ret = 1d;
        } else {
            ret = 0d;
        }
        return ret;
    }

    @Override
    public Individual reproduce(Individual individual) {
        final Reproducible reproducible = super.getReproducible().reproduce(individual.getReproducible(), getMutationRate());
        return new EmptyDetector((Mlp) reproducible, getMutationRate(), getGoal());
    }

}
