package com.yzumis.geneticmlpimageclassifier;

import com.yzumis.genetic.Individual;
import com.yzumis.genetic.IndividualFactory;
import com.yzumis.geneticmlpimageclassifier.network.ConvReluPooling2DNeuron;
import com.yzumis.geneticmlpimageclassifier.network.ConvolutionalNeuralNetwork;
import com.yzumis.mlp.Mlp;
import com.yzumis.mlp.Neuron;

import java.util.ArrayList;
import java.util.List;

public class ConvolutionalNeuralNetworkIndividualFactory implements IndividualFactory {

    @Override
    public <G> Individual createIndividual(final double mutationRate, final G goal) {
        final List<Integer> mlpNeuronsPerLayer = new ArrayList<>();
        mlpNeuronsPerLayer.add(108);
        mlpNeuronsPerLayer.add(8);
        final Mlp mlp = new Mlp(108, mlpNeuronsPerLayer);
        final List<Integer> convolutionalNeuralNetworkNeuronsPerLayer = new ArrayList<>();
        convolutionalNeuralNetworkNeuronsPerLayer.add(3);
        convolutionalNeuralNetworkNeuronsPerLayer.add(3);
        final ConvolutionalNeuralNetwork convolutionalNeuralNetwork = new ConvolutionalNeuralNetwork(convolutionalNeuralNetworkNeuronsPerLayer, 4, mlp);
        return new ConvolutionalNeuralNetworkIndividual(convolutionalNeuralNetwork, mutationRate, goal);
    }

}
