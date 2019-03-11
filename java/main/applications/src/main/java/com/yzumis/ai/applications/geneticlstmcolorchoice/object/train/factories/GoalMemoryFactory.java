package com.yzumis.ai.applications.geneticlstmcolorchoice.object.train.factories;

import com.yzumis.ai.applications.geneticlstmcolorchoice.object.train.EmptyDetector;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.train.GoalMemory;
import com.yzumis.ai.genetic.Individual;
import com.yzumis.ai.genetic.IndividualFactory;

public class GoalMemoryFactory implements IndividualFactory {

    @Override
    public <G> Individual createIndividual(double mutationRate, G goal) {
        return new GoalMemory(mutationRate, (Double) goal);
    }
}
