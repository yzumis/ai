package com.yzumis.mlp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 27/12/2017.
 */
public class Mlp {

    private final List<List<Neuron>> neuronLayers;

    public Mlp(final int numberOfInputs, final List<Integer> neuronsPerLayer) {
        this.neuronLayers = new ArrayList<>();
        for (int i = 0; i < neuronsPerLayer.size(); i++) {
            final List<Neuron> layer = this.initializeNeuronLayer(numberOfInputs, neuronsPerLayer.get(i), i);
            this.neuronLayers.add(layer);
        }
    }

    public Mlp(final List<List<Neuron>> neuronLayers) {
        this.neuronLayers = neuronLayers;
    }

    private List<Neuron> initializeNeuronLayer(final int numberOfInputs, final int neuronsInLayer, final int numberOflayer) {
        final List<Neuron> ret = new ArrayList<>();
        for(int i = 0; i < neuronsInLayer; i++) {
            final Neuron neuron;
            if(numberOflayer == 0) {
                neuron = new Neuron(numberOfInputs);
            } else {
                final int middleLayerNumberOfInputs = this.neuronLayers.get(numberOflayer - 1).size();
                neuron = new Neuron(middleLayerNumberOfInputs);
            }
            ret.add(neuron);
        }
        return ret;
    }

    public List<Float> calculateOutputs(final List<Float> inputs) {
        for(int i = 0; i < this.neuronLayers.size(); i++) {
            for(int j = 0; j < this.neuronLayers.get(i).size(); j++) {
                if( i == 0) {
                    this.neuronLayers.get(i).get(j).calculateOutput(inputs);
                } else {
                    final List<Float> middleLayerInputs = this.getLayerOutputs(this.neuronLayers.get(i - 1));
                    this.neuronLayers.get(i).get(j).calculateOutput(middleLayerInputs);
                }
            }
        }
        return this.getLayerOutputs(this.neuronLayers.get(this.neuronLayers.size() -1));
    }

    private List<Float> getLayerOutputs(final List<Neuron> neuronLayer) {
        final List<Float> ret = new ArrayList<>();
        for(final Neuron neuron : neuronLayer) {
            ret.add(neuron.getOutput());
        }
        return ret;
    }

    public static Mlp reproduce(final Mlp mlp1, final Mlp mlp2, final float mutationRate) {
        final List<List<Neuron>> childNeuronLayers = new ArrayList<>();
        for(int i = 0; i < mlp1.neuronLayers.size(); i++) {
            final List<Neuron> childNeuronLayer = new ArrayList<>();
            for(int j = 0; j < mlp1.neuronLayers.get(i).size(); j++) {
                final Neuron neuron = Neuron.reproduce(mlp1.neuronLayers.get(i).get(j), mlp2.neuronLayers.get(i).get(j), mutationRate);
                childNeuronLayer.add(neuron);
            }
            childNeuronLayers.add(childNeuronLayer);
        }
        return new Mlp(childNeuronLayers);
    }
}
