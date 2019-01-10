package com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier;

import com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier.image.Image;
import com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier.image.ImageType;
import com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier.network.ConvolutionalNeuralNetwork;
import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Individual;
import com.yzumis.ai.genetic.Reproducible;

import java.io.IOException;
import java.util.List;

public class ConvolutionalNeuralNetworkIndividual extends Individual<ConvolutionalNeuralNetwork, Object> {

    public ConvolutionalNeuralNetworkIndividual(final ConvolutionalNeuralNetwork convolutionalNeuralNetwork, final double mutationRate, final Object goal) {
        super(convolutionalNeuralNetwork, mutationRate, goal);
    }

    @Override
    public void calculateFitness() {
        try {
            final int numberOfImages = 100;
            int fitness = 0;
            final CifarReader cifarReader = new CifarReader(numberOfImages);
            Image image;
            while ((image = cifarReader.readImage()) != null) {
                final ConvolutionalNeuralNetwork convolutionalNeuralNetwork = (ConvolutionalNeuralNetwork)this.getReproducible();
                final Vector output = convolutionalNeuralNetwork.calculateOutputs(image.toNormalizedInput2D());
                final ImageType imageType = calculateImageType(output);
                // System.out.println("imageType = " + imageType);
                // System.out.println("image.getImageType() = " + image.getImageType());
                if(imageType.equals(image.getImageType())) {
                    fitness++;
                }
            }
            setFitness((double) fitness / (double) numberOfImages);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private ImageType calculateImageType(final Vector output) {
        int maxIndex = 0;
        final List<Double> outputList = output.toList();
        // System.out.println("outputList = " + outputList);
        for (int i = 0; i < outputList.size(); i++) {
            if(outputList.get(i) >= outputList.get(maxIndex)) {
                maxIndex = i;
            }
        }
        // System.out.println("ImageType.byteToImageType((byte)maxIndex) = " + ImageType.byteToImageType((byte)maxIndex));
        return ImageType.byteToImageType((byte)maxIndex);
    }

    @Override
    public Individual reproduce(Individual individual) {
        final Reproducible reproducible = super.getReproducible().reproduce(individual.getReproducible(), getMutationRate());
        return new ConvolutionalNeuralNetworkIndividual((ConvolutionalNeuralNetwork) reproducible, getMutationRate(), getGoal());
    }
}
