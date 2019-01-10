package com.yzumis.ai.neuron;

import com.yzumis.ai.commonneuron.BaseNeuron;
import com.yzumis.ai.commonneuron.BaseNeuronFactory;

public class NeuronFactory implements BaseNeuronFactory {

    @Override
    public BaseNeuron createBaseNeuron(int numberOfInputs) {
        return new Neuron(numberOfInputs);
    }

}
