package com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier.input;

import com.yzumis.ai.genetic.Reproducible;

public class Matrix implements Reproducible {

    private final double[][] values;

    public Matrix(final double[][] values) {
        this.values = values;
    }

    public Matrix(final int width, final int height) {
        this(new double[width][height]);
    }

    public Matrix(final int matrixSize) {
        this(matrixSize, matrixSize);
        for(int i = 0; i < matrixSize; i++) {
            for(int j = 0; j < matrixSize; j++) {
                this.values[i][j] = Math.random();
            }
        }
    }

    public void setValue(final int width, final int height, final double value) {
        this.values[width][height] = value;
    }

    public double getValue(final int width, final int height) {
        return this.values[width][height];
    }

    public int getWidth() {
        return this.values.length;
    }

    public int getHeigth() {
        return this.values[0].length;
    }

    public boolean outOfBounds(final int width, final int height) {
        final boolean outOfBoundsWidth = width < 0 || width >= this.values.length;
        final boolean outOfBoundsHeight = height < 0 || height >= this.values.length;
        return outOfBoundsWidth || outOfBoundsHeight;
    }

    public double multiplyValuePerValueAndAdd(final Matrix matrix) {
        assert this.getWidth() == matrix.getWidth();
        assert this.getHeigth() == matrix.getHeigth();
        double ret = 0;
        for(int i = 0; i < this.getWidth(); i++) {
            for(int j = 0; j < this.getHeigth(); j++) {
                ret += this.getValue(i, j) * matrix.getValue(i, j);
            }
        }
        return ret;
    }

    @Override
    public Reproducible reproduce(final Reproducible reproducible, final double mutationRate) {
        final Matrix matrix = (Matrix) reproducible;
        final Matrix ret = new Matrix(this.getWidth(), this.getHeigth());
        for(int i = 0; i < this.getWidth(); i++) {
            for(int j = 0; j < this.getHeigth(); j++) {
                final double value = calculateValue(this.getValue(i, j), matrix.getValue(i, j), mutationRate);
                ret.setValue(i, j, value);
            }
        }
        return ret;
    }

    private double calculateValue(final double valueA, final double valueB, final double mutationRate) {
        final double ret;
        if(Math.random() < mutationRate) {
            ret = Math.random();
        } else {
            if (Math.random() < 0.5D) {
                ret = valueA;
            } else {
                ret = valueB;
            }
        }
        return ret;
    }
}
