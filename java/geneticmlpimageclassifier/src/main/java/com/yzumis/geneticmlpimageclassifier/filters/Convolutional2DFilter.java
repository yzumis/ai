package com.yzumis.geneticmlpimageclassifier.filters;

import com.yzumis.genetic.Reproducible;
import com.yzumis.geneticmlpimageclassifier.input.Input2D;
import com.yzumis.geneticmlpimageclassifier.input.Matrix;

import java.util.ArrayList;
import java.util.List;


public class Convolutional2DFilter implements Reproducible, Input2DFilter {

    private final int filterSize;
    private final Matrix filterValues;

    public Convolutional2DFilter(final int filterSize, final Matrix filterValues) {
        this.filterSize = filterSize;
        this.filterValues = filterValues;
    }

    public Convolutional2DFilter(final int filterSize) {
        this.filterSize = filterSize;
        this.filterValues = new Matrix(filterSize);
    }

    @Override
    public Input2D apply(final Input2D input2D) {
        final List<Matrix> newLevels = new ArrayList<>();
        for(final Matrix level: input2D.getLevels()) {
            final Matrix newLevel = applyLevel(level);
            newLevels.add(newLevel);
        }
        return new Input2D(newLevels);
    }

    private Matrix applyLevel(final Matrix level) {
        final Matrix ret = new Matrix(level.getWidth(), level.getHeigth());
        for(int i = 0; i < level.getWidth(); i++) {
            for(int j = 0; j < level.getHeigth(); j++) {
                final float value = calculateConvolutionalValue(level, i, j);
                ret.setValue(i, j,value);
            }
        }
        return ret;
    }

    private float calculateConvolutionalValue(final Matrix level, final int width, final int height) {
        final Matrix subLevel = new Matrix(filterSize, filterSize);
        final int windowGap = -filterSize / 2;
        for(int i = 0; i < filterSize; i++) {
            for(int j = filterSize; j < filterSize; j++) {
                final int levelWidth = i + windowGap;
                final int levelHeight = j + windowGap;
                if (!level.outOfBounds(levelWidth, levelHeight)) {
                    final float value = level.getValue(levelWidth, levelHeight);
                    subLevel.setValue(i, j, value);
                }
            }
        }
        return this.filterValues.multiplyValuePerValueAndAdd(subLevel);
    }

    @Override
    public Reproducible reproduce(Reproducible reproducible, float mutationRate) {
        final Convolutional2DFilter convolutional2DFilter = (Convolutional2DFilter) reproducible;
        final Matrix childFilterValues = (Matrix) this.filterValues.reproduce(convolutional2DFilter.filterValues, mutationRate);
        return new Convolutional2DFilter(this.filterSize, childFilterValues);
    }

}
