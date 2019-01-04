package com.yzumis.ia.applications.convolutionalgeneticmlpimageclassifier;

import com.yzumis.ia.applications.convolutionalgeneticmlpimageclassifier.network.ConvolutionalNeuralNetwork;
import com.yzumis.ia.genetic.Individual;
import com.yzumis.ia.genetic.IndividualFactory;
import com.yzumis.ia.mlp.Mlp;

import java.util.ArrayList;
import java.util.List;

public class ConvolutionalNeuralNetworkIndividualFactory implements IndividualFactory {

    @Override
    public <G> Individual createIndividual(final double mutationRate, final G goal) {
        final List<Integer> mlpNeuronsPerLayer = new ArrayList<>();
        mlpNeuronsPerLayer.add(36);
        mlpNeuronsPerLayer.add(8);
        final Mlp mlp = new Mlp(36, mlpNeuronsPerLayer);
        final List<Integer> convolutionalNeuralNetworkNeuronsPerLayer = new ArrayList<>();
        convolutionalNeuralNetworkNeuronsPerLayer.add(3);
        convolutionalNeuralNetworkNeuronsPerLayer.add(3);
        final ConvolutionalNeuralNetwork convolutionalNeuralNetwork = new ConvolutionalNeuralNetwork(convolutionalNeuralNetworkNeuronsPerLayer, 4, mlp);
        return new ConvolutionalNeuralNetworkIndividual(convolutionalNeuralNetwork, mutationRate, goal);
    }

}