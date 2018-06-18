package com.yzumis.geneticmlpimageclassifier.input;

import com.yzumis.genetic.Reproducible;

public class Matrix implements Reproducible {

    private final float[][] values;

    public Matrix(float[][] values) {
        this.values = values;
    }

    public Matrix(final int width, final int height) {
        this(new float[width][height]);
    }

    public Matrix(final int matrixSize) {
        this(matrixSize, matrixSize);
        for(int i = 0; i < matrixSize; i++) {
            for(int j = 0; j < matrixSize; j++) {
                this.values[i][j] = (float)Math.random();
            }
        }
    }

    public void setValue(final int width, final int height, final float value) {
        this.values[width][height] = value;
    }

    public float getValue(final int width, final int height) {
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

    public float multiplyValuePerValueAndAdd(final Matrix matrix) {
        assert this.getWidth() == matrix.getWidth();
        assert this.getHeigth() == matrix.getHeigth();
        float ret = 0;
        for(int i = 0; i < this.getWidth(); i++) {
            for(int j = 0; j < this.getHeigth(); j++) {
                ret += this.getValue(i, j) * matrix.getValue(i, j);
            }
        }
        return ret;
    }

    @Override
    public Reproducible reproduce(final Reproducible reproducible, final float mutationRate) {
        final Matrix matrix = (Matrix) reproducible;
        final Matrix ret = new Matrix(this.getWidth(), this.getHeigth());
        for(int i = 0; i < this.getWidth(); i++) {
            for(int j = 0; j < this.getHeigth(); j++) {
                final float value = calculateValue(this.getValue(i, j), matrix.getValue(i, j), mutationRate);
                ret.setValue(i, j, value);
            }
        }
        return ret;
    }

    private float calculateValue(final float valueA, final float valueB, final float mutationRate) {
        final float ret;
        if(Math.random() < mutationRate) {
            ret = (float) Math.random();
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
