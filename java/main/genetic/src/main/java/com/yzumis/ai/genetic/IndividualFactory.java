package com.yzumis.ai.genetic;

public interface IndividualFactory {

    <G> Individual createIndividual(final double mutationRate, final G goal);

}
