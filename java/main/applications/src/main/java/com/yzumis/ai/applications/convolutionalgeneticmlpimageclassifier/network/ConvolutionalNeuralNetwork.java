package com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier.network;

import com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier.input.Input2D;
import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Reproducible;
import com.yzumis.ai.mlp.Mlp;

import java.util.ArrayList;
import java.util.List;

public class ConvolutionalNeuralNetwork implements Reproducible {

    private final List<List<ConvReluPooling2DNeuron>> neuronLayers;
    private final Mlp mlp;

    public ConvolutionalNeuralNetwork(final List<List<ConvReluPooling2DNeuron>> neuronLayers, final Mlp mlp) {
        this.neuronLayers = neuronLayers;
        this.mlp = mlp;
    }

    public ConvolutionalNeuralNetwork(final List<Integer> neuronsPerLayer, final int filterSize, final Mlp mlp) {
        this.neuronLayers = initializeNeuronLayers(neuronsPerLayer, filterSize);
        this.mlp = mlp;
    }

    private List<List<ConvReluPooling2DNeuron>> initializeNeuronLayers(final List<Integer> neuronsPerLayer, final int filterSize) {
        final List<List<ConvReluPooling2DNeuron>> ret = new ArrayList<>();
        for(final int i: neuronsPerLayer) {
            final List<ConvReluPooling2DNeuron> neuronLayer = initializeNeuronLayer(i, filterSize);
            ret.add(neuronLayer);
        }
        return ret;
    }

    private List<ConvReluPooling2DNeuron> initializeNeuronLayer(final int neurons, final int filterSize) {
        final List<ConvReluPooling2DNeuron> ret = new ArrayList<>();
        for(int i = 0; i < neurons; i++) {
            final ConvReluPooling2DNeuron convReluPooling2DNeuron = new ConvReluPooling2DNeuron(filterSize);
            ret.add(convReluPooling2DNeuron);
        }
        return ret;
    }

    public Vector calculateOutputs(final Input2D input2D) {
        List<Input2D> currentInputs = new ArrayList<>();
        currentInputs.add(input2D);
        List<Input2D> currentOutputs = new ArrayList<>();

        for(final List<ConvReluPooling2DNeuron> neuronLayer : this.neuronLayers) {
            currentOutputs = calculateNeuronLayerOutpus(neuronLayer, currentInputs);
            currentInputs = currentOutputs;
        }

        Vector vector = new Vector();
        for(final Input2D output2D: currentOutputs) {
            final Vector currentOutputVector = output2D.toVector();
            vector = vector.concatenate(currentOutputVector);

        }
        return this.mlp.calculateOutputs(vector);
    }

    private List<Input2D> calculateNeuronLayerOutpus(final List<ConvReluPooling2DNeuron> neuronLayer, final List<Input2D> inputs2D) {
        final List<Input2D> ret = new ArrayList<>();
        for(final ConvReluPooling2DNeuron neuron : neuronLayer) {
            final List<Input2D> currentNeuronOutputs = calculateOutPuts(inputs2D, neuron);
            ret.addAll(currentNeuronOutputs);
        }
        return ret;
    }

    private List<Input2D> calculateOutPuts(final List<Input2D> currentInputs, final ConvReluPooling2DNeuron convReluPooling2DNeuron) {
        final List<Input2D> ret = new ArrayList<>();
        for(final Input2D input2D: currentInputs) {
            final Input2D output2D = convReluPooling2DNeuron.apply(input2D);
            ret.add(output2D);
        }
        return ret;
    }

    @Override
    public Reproducible reproduce(final Reproducible reproducible, final double mutationRate) {
        final ConvolutionalNeuralNetwork convolutionalNeuralNetwork = (ConvolutionalNeuralNetwork) reproducible;
        final List<List<ConvReluPooling2DNeuron>> childNeuronLayers = calculateChildNeuronLayers(this.neuronLayers, convolutionalNeuralNetwork.neuronLayers, mutationRate);
        final Mlp childMlp = (Mlp) this.mlp.reproduce(convolutionalNeuralNetwork.mlp, mutationRate);
        return new ConvolutionalNeuralNetwork(childNeuronLayers, childMlp);
    }

    private List<List<ConvReluPooling2DNeuron>> calculateChildNeuronLayers(final List<List<ConvReluPooling2DNeuron>> neuronLayersA, final List<List<ConvReluPooling2DNeuron>> neuronLayersB, final double mutationRate) {
        final List<List<ConvReluPooling2DNeuron>> ret = new ArrayList<>();
        for(int i = 0; i < neuronLayers.size(); i++) {
            final List<ConvReluPooling2DNeuron> childNeuronLayer = calculateChildNeuronLayer(neuronLayersA.get(i), neuronLayersB.get(i), mutationRate);
            ret.add(childNeuronLayer);
        }
        return ret;
    }

    private List<ConvReluPooling2DNeuron> calculateChildNeuronLayer(final List<ConvReluPooling2DNeuron> neuronLayerA, final List<ConvReluPooling2DNeuron> neuronLayerB, final double mutationRate) {
        final List<ConvReluPooling2DNeuron> ret = new ArrayList<>();
        for(int i = 0; i < neuronLayerA.size(); i++) {
            final ConvReluPooling2DNeuron childConvReluPooling2DNeuron = (ConvReluPooling2DNeuron) neuronLayerA.get(i).reproduce(neuronLayerB.get(i), mutationRate);
            ret.add(childConvReluPooling2DNeuron);
        }
        return ret;
    }

}
