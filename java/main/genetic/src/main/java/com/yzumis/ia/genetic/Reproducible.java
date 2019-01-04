package com.yzumis.ia.genetic;

public interface Reproducible {

    Reproducible reproduce(final Reproducible reproducible, final double mutationRate);

}
