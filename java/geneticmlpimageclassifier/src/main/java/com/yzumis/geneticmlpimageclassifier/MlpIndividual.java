package com.yzumis.geneticmlpimageclassifier;

import com.yzumis.genetic.Individual;
import com.yzumis.genetic.Reproducible;
import com.yzumis.mlp.Mlp;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MlpIndividual extends Individual<Mlp, Object> {

    public MlpIndividual(Mlp mlp, float mutationRate, Object goal) {
        super(mlp, mutationRate, goal);
    }


    @Override
    public void calculateFitness() {
        try {
            final int numberOfImages = 100;
            int fitness = 0;
            final CifarReader cifarReader = new CifarReader(numberOfImages);
            Image image;
            while ((image = cifarReader.readImage()) != null) {
                final Mlp mlp = (Mlp)this.getReproducible();
                final List<Float> output = mlp.calculateOutputs(image.toInput());
                final ImageType imageType = calculateImageType(output);
                // System.out.println("imageType = " + imageType);
                // System.out.println("image.getImageType() = " + image.getImageType());

                if(imageType.equals(image.getImageType())) {
                    fitness++;
                }
            }
            setFitness((float) fitness / (float) numberOfImages);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private ImageType calculateImageType(final List<Float> output) {
        int maxIndex = 0;
        // System.out.println("output = " + output);
        for (int i = 0; i < output.size(); i++) {
            if(output.get(i) >= output.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return ImageType.byteToImageType((byte)maxIndex);
    }

    @Override
    public Individual reproduce(Individual individual) {
        final Reproducible reproducible = super.getReproducible().reproduce(individual.getReproducible(), getMutationRate());
        return new MlpIndividual((Mlp)reproducible, getMutationRate(), getGoal());
    }
}
