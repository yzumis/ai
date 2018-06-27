package com.yzumis.genetic;

public interface Reproducible {

    Reproducible reproduce(final Reproducible reproducible, final double mutationRate);

}
