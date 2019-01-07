package com.yzumis.ai.genetic;

public interface Reproducible {

    Reproducible reproduce(final Reproducible reproducible, final double mutationRate);

}
