package com.yzumis.ai.applications.kryptos;

import com.yzumis.ai.commonneuron.BaseNeuronFactory;
import com.yzumis.ai.genetic.Individual;
import com.yzumis.ai.genetic.IndividualFactory;
import com.yzumis.ai.mlp.Mlp;
import com.yzumis.ai.neuron.NeuronFactory;

import java.util.ArrayList;
import java.util.List;

public class MlpIndividualFactory implements IndividualFactory {

    @Override
    public <G> Individual createIndividual(final double mutationRate, final G goal) {
        final List<Integer> mlpNeuronsPerLayer = new ArrayList<>();
        mlpNeuronsPerLayer.add(97 * 5);
        mlpNeuronsPerLayer.add(97 * 5);
        final BaseNeuronFactory baseNeuronFactory = new NeuronFactory();
        final Mlp mlp = new Mlp(97 * 5, mlpNeuronsPerLayer, baseNeuronFactory);
        return new MlpIndividual(mlp, mutationRate, (String) goal);
    }

}

