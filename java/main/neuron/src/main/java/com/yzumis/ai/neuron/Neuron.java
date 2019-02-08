package com.yzumis.ai.neuron;

import com.yzumis.ai.commonneuron.BaseNeuron;
import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Reproducible;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Yzumi on 27/12/2017.
 */
public class Neuron implements BaseNeuron {

    private final List<Double> weights;
    private final double bias;
    private Vector output;

    public Neuron(final int numberOfconnections) {
        this.weights = new ArrayList<>();
        for(int i = 0; i < numberOfconnections; i++) {
            this.weights.add(this.generateRandomWeight());
        }
        this.bias = this.generateRandomWeight();
    }

    public Neuron(final List<Double> weights, final double bias) {
        this.weights = weights;
        this.bias = bias;
    }

    private double generateRandomWeight() {
        return ThreadLocalRandom.current().nextDouble(-1, 1);
    }

    @Override
    public void calculateOutput(final Vector input) {
        final double weightedInput = this.calculateWeightedInput(input) + this.bias;
        this.output = new Vector(1.0 / (1.0 + Math.exp(-weightedInput)));
    }

    private double calculateWeightedInput(final Vector input) {
        double ret = 0;
        final List<Double> inputList = input.toList();
        for(int i = 0; i < inputList.size(); i++) {
            ret += this.weights.get(i) * inputList.get(i);
        }
        return ret;
    }

    @Override
    public Vector getOutput() {
        return output;
    }


    @Override
    public Reproducible reproduce(final Reproducible reproducible, final double mutationRate) {
        final Neuron neuron = ((Neuron)reproducible);
        final List<Double> childWeights = new ArrayList<>();
        for(int i = 0; i < this.weights.size(); i++) {
            double childWeight;
            if(Math.random() < 0.5) {
                childWeight = this.weights.get(i);
            } else {
                childWeight = neuron.weights.get(i);
            }
            childWeight = this.randomDeltaMutation(childWeight, mutationRate);
            childWeights.add(childWeight);
        }
        double bias;
        if(Math.random() < 0.5) {
            bias = this.bias;
        } else {
            bias = neuron.bias;
        }
        bias = this.randomDeltaMutation(bias, mutationRate);
        return new Neuron(childWeights, bias);
    }

    private double randomDeltaMutation(final double value, final double mutationRate) {
        final double deltaValue;
        if(Math.random() < mutationRate) {
            deltaValue = ThreadLocalRandom.current().nextDouble(-1, 1);
        } else {
            deltaValue = 0;
        }
        return value + deltaValue;
    }

}
