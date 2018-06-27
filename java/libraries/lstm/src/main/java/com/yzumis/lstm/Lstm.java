package com.yzumis.lstm;

import com.yzumis.genetic.Reproducible;
import com.yzumis.mlp.Mlp;

import java.util.List;

public class Lstm implements Reproducible {

    private final int numberOfInputs;
    private final int numberOfOutputs;
    private final Mlp selectMlp;
    private final Mlp forgetMlp;
    private final Mlp ignoreMlp;
    private final Mlp predictionMlp;
    private Vector previousOutput;
    private Vector lastForgetResult;

    public Lstm(final int numberOfInputs, final int numberOfOutputs, final List<Integer> selectMlpNeuronsPerLayer, final List<Integer> forgetMlpNeuronsPerLayer, final List<Integer> ignoreMlpNeuronsPerLayer, final List<Integer> predictionMlpNeuronsPerLayer) {
        assert numberOfOutputs == selectMlpNeuronsPerLayer.get(selectMlpNeuronsPerLayer.size() - 1);
        assert numberOfOutputs == forgetMlpNeuronsPerLayer.get(forgetMlpNeuronsPerLayer.size() - 1);
        assert numberOfOutputs == ignoreMlpNeuronsPerLayer.get(ignoreMlpNeuronsPerLayer.size() - 1);
        assert numberOfOutputs == predictionMlpNeuronsPerLayer.get(predictionMlpNeuronsPerLayer.size() - 1);
        this.numberOfInputs = numberOfInputs;
        this.numberOfOutputs = numberOfOutputs;
        this.selectMlp = new Mlp(numberOfInputs, selectMlpNeuronsPerLayer);
        this.forgetMlp = new Mlp(numberOfInputs, forgetMlpNeuronsPerLayer);
        this.ignoreMlp = new Mlp(numberOfInputs, ignoreMlpNeuronsPerLayer);
        this.predictionMlp = new Mlp(numberOfInputs, predictionMlpNeuronsPerLayer);
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

    public Vector calculateOutput(final Vector input) {
        final Vector globalInput = input.concatenate(previousOutput);
        final Vector selectResult = new Vector(this.selectMlp.calculateOutputs(globalInput.toList())).sigmoid();
        final Vector forgetResult = new Vector(this.forgetMlp.calculateOutputs(globalInput.toList())).sigmoid();
        final Vector ignoreResult = new Vector(this.ignoreMlp.calculateOutputs(globalInput.toList())).sigmoid();
        final Vector predictionResult = new Vector(this.predictionMlp.calculateOutputs(globalInput.toList())).tanh();

        final Vector predictionMultiplyIgnoreResult = predictionResult.multiply(ignoreResult);
        final Vector forgetMultiplyLastForgetResult = forgetResult.multiply(lastForgetResult);

        final Vector predictionIgnoreAddForgetResult = predictionMultiplyIgnoreResult.add(forgetMultiplyLastForgetResult);
        lastForgetResult = predictionIgnoreAddForgetResult;

        final Vector predictionIgnoreAddForgetResultTanh = predictionIgnoreAddForgetResult.tanh();
        final Vector predictionIgnoreForgetTanhMultiplySelectionResult = predictionIgnoreAddForgetResultTanh.multiply(selectResult);
        previousOutput = predictionIgnoreForgetTanhMultiplySelectionResult;
        return predictionIgnoreForgetTanhMultiplySelectionResult;
    }

    @Override
    public Reproducible reproduce(Reproducible reproducible, double mutationRate) {
        final Lstm lstm = (Lstm) reproducible;
        final Mlp childselectMlp = (Mlp) this.selectMlp.reproduce(lstm.selectMlp, mutationRate);
        final Mlp childForgetMlp = (Mlp) this.forgetMlp.reproduce(forgetMlp, mutationRate);
        final Mlp childIgnoreMlp = (Mlp) this.ignoreMlp.reproduce(ignoreMlp, mutationRate);
        final Mlp childPredictionMlp = (Mlp) this.predictionMlp.reproduce(lstm.predictionMlp, mutationRate);
        return new Lstm(this.numberOfInputs, this.numberOfOutputs, childselectMlp, childForgetMlp, childIgnoreMlp, childPredictionMlp);
    }

}
