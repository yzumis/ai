package com.yzumis.geneticmlpimageclassifier;

import com.yzumis.genetic.Individual;
import com.yzumis.genetic.IndividualFactory;
import com.yzumis.mlp.Mlp;

import java.util.ArrayList;
import java.util.List;

public class MlpIndividualFactory implements IndividualFactory {

    @Override
    public <G> Individual createIndividual(final float mutationRate, final G goal) {
        final List<Integer> neuronsPerLayer = new ArrayList<>();
        neuronsPerLayer.add(3072);
        neuronsPerLayer.add(10);
        final Mlp mlp = new Mlp(3072, neuronsPerLayer);
        return new MlpIndividual(mlp, mutationRate, goal);
    }

}
