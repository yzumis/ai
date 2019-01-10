package com.yzumis.ai.lstm;

import com.yzumis.ai.commonneuron.BaseNeuron;
import com.yzumis.ai.commonneuron.BaseNeuronFactory;
import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Reproducible;
import com.yzumis.ai.mlp.Mlp;

import java.util.List;

public class Lstm implements BaseNeuron {

    private final int numberOfInputs;
    private final int numberOfOutputs;
    private final Mlp selectMlp;
    private final Mlp forgetMlp;
    private final Mlp ignoreMlp;
    private final Mlp predictionMlp;
    private Vector output;
    private Vector previousOutput;
    private Vector lastForgetResult;

    public Lstm(final int numberOfInputs, final int numberOfOutputs, final List<Integer> selectMlpNeuronsPerLayer, final List<Integer> forgetMlpNeuronsPerLayer, final List<Integer> ignoreMlpNeuronsPerLayer, final List<Integer> predictionMlpNeuronsPerLayer, final BaseNeuronFactory baseNeuronFactory) {
        assert numberOfOutputs == selectMlpNeuronsPerLayer.get(selectMlpNeuronsPerLayer.size() - 1);
        assert numberOfOutputs == forgetMlpNeuronsPerLayer.get(forgetMlpNeuronsPerLayer.size() - 1);
        assert numberOfOutputs == ignoreMlpNeuronsPerLayer.get(ignoreMlpNeuronsPerLayer.size() - 1);
        assert numberOfOutputs == predictionMlpNeuronsPerLayer.get(predictionMlpNeuronsPerLayer.size() - 1);
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.selectMlp = new Mlp(numberOfInputs, selectMlpNeuronsPerLayer, baseNeuronFactory);
        this.forgetMlp = new Mlp(numberOfInputs, forgetMlpNeuronsPerLayer, baseNeuronFactory);
        this.ignoreMlp = new Mlp(numberOfInputs, ignoreMlpNeuronsPerLayer, baseNeuronFactory);
        this.predictionMlp = new Mlp(numberOfInputs, predictionMlpNeuronsPerLayer, baseNeuronFactory);
        this.previousOutput = new Vector(numberOfOutputs);
        this.lastForgetResult = new Vector(numberOfOutputs);
    }

    private Lstm(final int numberOfInputs, final int numberOfOutputs, final Mlp selectMlp, final Mlp forgetMlp, final Mlp ignoreMlp, final Mlp predictionMlp) {
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.selectMlp = selectMlp;
        this.forgetMlp = forgetMlp;
        this.ignoreMlp = ignoreMlp;
        this.predictionMlp = predictionMlp;
        this.previousOutput = new Vector(numberOfOutputs);
        this.lastForgetResult = new Vector(numberOfOutputs);
    }

    @Override
    public void calculateOutput(final Vector input) {
        final Vector globalInput = input.concatenate(previousOutput);
        final Vector selectResult = this.selectMlp.calculateOutputs(globalInput).sigmoid();
        final Vector forgetResult = this.forgetMlp.calculateOutputs(globalInput).sigmoid();
        final Vector ignoreResult = this.ignoreMlp.calculateOutputs(globalInput).sigmoid();
        final Vector predictionResult = this.predictionMlp.calculateOutputs(globalInput).tanh();

        final Vector predictionMultiplyIgnoreResult = predictionResult.multiply(ignoreResult);
        final Vector forgetMultiplyLastForgetResult = forgetResult.multiply(lastForgetResult);

        final Vector predictionIgnoreAddForgetResult = predictionMultiplyIgnoreResult.add(forgetMultiplyLastForgetResult);
        lastForgetResult = predictionIgnoreAddForgetResult;

        final Vector predictionIgnoreAddForgetResultTanh = predictionIgnoreAddForgetResult.tanh();
        final Vector predictionIgnoreForgetTanhMultiplySelectionResult = predictionIgnoreAddForgetResultTanh.multiply(selectResult);
        previousOutput = predictionIgnoreForgetTanhMultiplySelectionResult;
        this.output = predictionIgnoreForgetTanhMultiplySelectionResult;
    }

    @Override
    public Reproducible reproduce(final Reproducible reproducible, final double mutationRate) {
        final Lstm lstm = (Lstm) reproducible;
        final Mlp childselectMlp = (Mlp) this.selectMlp.reproduce(lstm.selectMlp, mutationRate);
        final Mlp childForgetMlp = (Mlp) this.forgetMlp.reproduce(lstm.forgetMlp, mutationRate);
        final Mlp childIgnoreMlp = (Mlp) this.ignoreMlp.reproduce(lstm.ignoreMlp, mutationRate);
        final Mlp childPredictionMlp = (Mlp) this.predictionMlp.reproduce(lstm.predictionMlp, mutationRate);
        return new Lstm(this.numberOfInputs, this.numberOfOutputs, childselectMlp, childForgetMlp, childIgnoreMlp, childPredictionMlp);
    }

    @Override
    public Vector getOutput() {
        return output;
    }

}
