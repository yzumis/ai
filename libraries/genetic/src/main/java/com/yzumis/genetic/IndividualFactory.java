package com.yzumis.genetic;

public interface IndividualFactory {

    <G> Individual createIndividual(final float mutationRate, final G goal);

}
