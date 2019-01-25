package com.yzumis.ai.lstm;

import com.yzumis.ai.commonneuron.BaseNeuron;
import com.yzumis.ai.commonneuron.BaseNeuronFactory;
import com.yzumis.ai.neuron.NeuronFactory;

import java.util.ArrayList;
import java.util.List;

public class LstmNeuronFactory implements BaseNeuronFactory {

    private static final List<Integer> MLP_NEURONS_PER_LAYER = new ArrayList<>();
    private static final int NUMBER_OF_OUTPUTS = 1;
    private static final BaseNeuronFactory BASE_NEURON_FACTORY = new NeuronFactory();

    static {
        MLP_NEURONS_PER_LAYER.add(1);
    }

    @Override
    public BaseNeuron createBaseNeuron(final int numberOfInputs) {
        return new Lstm(numberOfInputs + NUMBER_OF_OUTPUTS, NUMBER_OF_OUTPUTS, MLP_NEURONS_PER_LAYER, MLP_NEURONS_PER_LAYER, MLP_NEURONS_PER_LAYER, MLP_NEURONS_PER_LAYER, BASE_NEURON_FACTORY);
    }

}
