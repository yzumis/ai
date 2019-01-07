package com.yzumis.ai.applications.geneticlstm;

import com.yzumis.ai.genetic.Individual;
import com.yzumis.ai.genetic.IndividualFactory;
import com.yzumis.ai.lstm.Lstm;

import java.util.ArrayList;
import java.util.List;

public class LstmIndividualFactory implements IndividualFactory {

    @Override
    public <G> Individual createIndividual(final double mutationRate, final G goal) {
        final List<Integer> mlpNeuronsPerLayer = new ArrayList<>();
        mlpNeuronsPerLayer.add(6);
        mlpNeuronsPerLayer.add(3);
        final Lstm lstm = new Lstm(6, 3, mlpNeuronsPerLayer, mlpNeuronsPerLayer, mlpNeuronsPerLayer, mlpNeuronsPerLayer);
        return new LstmIndividual(lstm, mutationRate, (String) goal);
    }

}

