package com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier;

import com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier.network.ConvolutionalNeuralNetwork;
import com.yzumis.ai.commonneuron.BaseNeuronFactory;
import com.yzumis.ai.genetic.Individual;
import com.yzumis.ai.genetic.IndividualFactory;
import com.yzumis.ai.mlp.Mlp;
import com.yzumis.ai.neuron.NeuronFactory;

import java.util.ArrayList;
import java.util.List;

public class ConvolutionalNeuralNetworkIndividualFactory implements IndividualFactory {

    @Override
    public <G> Individual createIndividual(final double mutationRate, final G goal) {
        final List<Integer> mlpNeuronsPerLayer = new ArrayList<>();
        mlpNeuronsPerLayer.add(36);
        mlpNeuronsPerLayer.add(8);
        final BaseNeuronFactory baseNeuronFactory = new NeuronFactory();
        final Mlp mlp = new Mlp(36, mlpNeuronsPerLayer, baseNeuronFactory);
        final List<Integer> convolutionalNeuralNetworkNeuronsPerLayer = new ArrayList<>();
        convolutionalNeuralNetworkNeuronsPerLayer.add(3);
        convolutionalNeuralNetworkNeuronsPerLayer.add(3);
        final ConvolutionalNeuralNetwork convolutionalNeuralNetwork = new ConvolutionalNeuralNetwork(convolutionalNeuralNetworkNeuronsPerLayer, 4, mlp);
        return new ConvolutionalNeuralNetworkIndividual(convolutionalNeuralNetwork, mutationRate, goal);
    }

}
