package driving.brain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 27/12/2017.
 */
public class Neuron {

    private final List<Float> weights;
    private final float bias;
    private float output;

    public Neuron(final int numberOfconnections) {
        this.weights = new ArrayList<>();
        for(int i = 0; i < numberOfconnections; i++) {
            this.weights.add(this.generateRandomWeight());
        }
        this.bias = this.generateRandomWeight();
    }

    public Neuron(final List<Float> weights, final float bias) {
        this.weights = weights;
        this.bias = bias;
    }

    private float generateRandomWeight() {
        return (float) Math.random();
    }

    public void calculateOutput(final List<Float> inputs) {
        final float weightedInput = this.calculateWeightedInput(inputs) + this.bias;
        this.output = 1 / (1 + (float)Math.exp(-weightedInput));
    }

    private float calculateWeightedInput(final List<Float> inputs) {
        float ret = 0;
        for(int i = 0; i < inputs.size(); i++) {
            ret += this.weights.get(i) * inputs.get(i);
        }
        return ret;
    }

    public float getOutput() {
        return output;
    }

    public static Neuron reproduce(final Neuron neuron1, final Neuron neuron2, final float mutationRate) {
        final List<Float> childWeights = new ArrayList<>();
        for(int i = 0; i < neuron1.weights.size(); i++) {
            float childWeight;
            if(Math.random() < 0.5) {
                childWeight = neuron1.weights.get(i);
            } else {
                childWeight = neuron2.weights.get(i);
            }
            childWeight = Neuron.randomDeltaMutation(childWeight, mutationRate);
            childWeights.add(childWeight);
        }
        float bias;
        if(Math.random() < 0.5) {
            bias = neuron1.bias;
        } else {
            bias = neuron2.bias;
        }
        bias = Neuron.randomDeltaMutation(bias, mutationRate);
        return new Neuron(childWeights, bias);
    }

    private static float randomDeltaMutation(final float value, final float mutationRate) {
        final float deltaValue;
        if(Math.random() < mutationRate) {
            deltaValue = 2 * (float) Math.random() - 1;
        } else {
            deltaValue = 0;
        }
        return value + deltaValue;
    }

}

