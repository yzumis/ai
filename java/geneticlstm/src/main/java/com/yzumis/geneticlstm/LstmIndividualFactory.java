package com.yzumis.geneticlstm;

import com.yzumis.genetic.IndividualFactory;
import com.yzumis.lstm.Lstm;

import java.util.ArrayList;
import java.util.List;

public class LstmIndividualFactory implements IndividualFactory {

    @Override
    public <G> com.yzumis.genetic.Individual createIndividual(final float mutationRate, final G goal) {
        final List<Integer> mlpNeuronsPerLayer = new ArrayList<>();
        mlpNeuronsPerLayer.add(6);
        mlpNeuronsPerLayer.add(3);
        final Lstm lstm = new Lstm(6, 3, mlpNeuronsPerLayer, mlpNeuronsPerLayer, mlpNeuronsPerLayer, mlpNeuronsPerLayer);
        return new LstmIndividual(lstm, mutationRate, (String) goal);
    }

}
