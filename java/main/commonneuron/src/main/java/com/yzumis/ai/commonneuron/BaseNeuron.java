package com.yzumis.ai.commonneuron;

import com.yzumis.ai.genetic.Reproducible;

public interface BaseNeuron extends Reproducible {

    void calculateOutput(final Vector input);

    Vector getOutput();

}
