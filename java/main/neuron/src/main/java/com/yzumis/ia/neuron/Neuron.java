package com.yzumis.ia.neuron;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 27/12/2017.
 */
public class Neuron {

    private final List<Double> weights;
    private final double bias;
    private double output;

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
        return Math.random();
    }

    public void calculateOutput(final List<Double> inputs) {
        final double weightedInput = this.calculateWeightedInput(inputs) + this.bias;
        this.output = 1.0 / (1.0 + Math.exp(-weightedInput));
    }

    private double calculateWeightedInput(final List<Double> inputs) {
        double ret = 0;
        for(int i = 0; i < inputs.size(); i++) {
            ret += this.weights.get(i) * inputs.get(i);
        }
        return ret;
    }

    public double getOutput() {
        return output;
    }

    public static Neuron reproduce(final Neuron neuron1, final Neuron neuron2, final double mutationRate) {
        final List<Double> childWeights = new ArrayList<>();
        for(int i = 0; i < neuron1.weights.size(); i++) {
            double childWeight;
            if(Math.random() < 0.5) {
                childWeight = neuron1.weights.get(i);
            } else {
                childWeight = neuron2.weights.get(i);
            }
            childWeight = Neuron.randomDeltaMutation(childWeight, mutationRate);
            childWeights.add(childWeight);
        }
        double bias;
        if(Math.random() < 0.5) {
            bias = neuron1.bias;
        } else {
            bias = neuron2.bias;
        }
        bias = Neuron.randomDeltaMutation(bias, mutationRate);
        return new Neuron(childWeights, bias);
    }

    private static double randomDeltaMutation(final double value, final double mutationRate) {
        final double deltaValue;
        if(Math.random() < mutationRate) {
            deltaValue = 2 * Math.random() - 1;
        } else {
            deltaValue = 0;
        }
        return value + deltaValue;
    }

}