package com.yzumis.ai.mlp;

import com.yzumis.ai.commonneuron.BaseNeuron;
import com.yzumis.ai.commonneuron.BaseNeuronFactory;
import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Reproducible;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 27/12/2017.
 */
public class Mlp implements Reproducible {

    private final List<List<BaseNeuron>> neuronLayers;

    public Mlp(final int numberOfInputs, final List<Integer> neuronsPerLayer, final BaseNeuronFactory baseNeuronFactory) {
        this.neuronLayers = new ArrayList<>();
        for (int i = 0; i < neuronsPerLayer.size(); i++) {
            final List<BaseNeuron> layer = this.initializeNeuronLayer(numberOfInputs, neuronsPerLayer.get(i), i, baseNeuronFactory);
            this.neuronLayers.add(layer);
        }
    }

    public Mlp(final List<List<BaseNeuron>> neuronLayers) {
        this.neuronLayers = neuronLayers;
    }

    private List<BaseNeuron> initializeNeuronLayer(final int numberOfInputs, final int neuronsInLayer, final int numberOflayer, final BaseNeuronFactory baseNeuronFactory) {
        final List<BaseNeuron> ret = new ArrayList<>();
        for(int i = 0; i < neuronsInLayer; i++) {
            final BaseNeuron neuron;
            if(numberOflayer == 0) {
                neuron = baseNeuronFactory.createBaseNeuron(numberOfInputs);
            } else {
                final int middleLayerNumberOfInputs = this.neuronLayers.get(numberOflayer - 1).size();
                neuron = baseNeuronFactory.createBaseNeuron(middleLayerNumberOfInputs);
            }
            ret.add(neuron);
        }
        return ret;
    }

    public Vector calculateOutputs(final Vector input) {
        for(int i = 0; i < this.neuronLayers.size(); i++) {
            for(int j = 0; j < this.neuronLayers.get(i).size(); j++) {
                if( i == 0) {
                    this.neuronLayers.get(i).get(j).calculateOutput(input);
                } else {
                    final Vector middleLayerInputs = this.getLayerOutputs(this.neuronLayers.get(i - 1));
                    this.neuronLayers.get(i).get(j).calculateOutput(middleLayerInputs);
                }
            }
        }
        return this.getLayerOutputs(this.neuronLayers.get(this.neuronLayers.size() -1));
    }

    private Vector getLayerOutputs(final List<BaseNeuron> neuronLayer) {
        Vector ret = new Vector();
        for(final BaseNeuron neuron : neuronLayer) {
            ret = ret.concatenate(neuron.getOutput());
        }
        return ret;
    }

    @Override
    public Reproducible reproduce(final Reproducible reproducible, final double mutationRate) {
        final List<List<BaseNeuron>> childNeuronLayers = new ArrayList<>();
        for(int i = 0; i < this.neuronLayers.size(); i++) {
            final List<BaseNeuron> childNeuronLayer = new ArrayList<>();
            for(int j = 0; j < this.neuronLayers.get(i).size(); j++) {
                final BaseNeuron neuron = (BaseNeuron)this.neuronLayers.get(i).get(j).reproduce(((Mlp)reproducible).neuronLayers.get(i).get(j), mutationRate);
                childNeuronLayer.add(neuron);
            }
            childNeuronLayers.add(childNeuronLayer);
        }
        return new Mlp(childNeuronLayers);
    }

}
