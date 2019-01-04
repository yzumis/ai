package com.yzumis.ia.applications.geneticlstm;

import com.yzumis.ia.genetic.Individual;
import com.yzumis.ia.genetic.IndividualFactory;
import com.yzumis.ia.lstm.Lstm;

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

