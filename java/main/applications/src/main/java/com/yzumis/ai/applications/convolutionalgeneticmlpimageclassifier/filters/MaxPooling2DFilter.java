package com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier.filters;

import com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier.input.Input2D;
import com.yzumis.ai.applications.convolutionalgeneticmlpimageclassifier.input.Matrix;

import java.util.ArrayList;
import java.util.List;

public class MaxPooling2DFilter implements Input2DFilter {

    private final int stride;
    private final int windowSize;

    public MaxPooling2DFilter(final int stride, final int windowSize) {
        this.stride = stride;
        this.windowSize = windowSize;
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
        final int newWidth = (int)Math.ceil((double)level.getWidth() / stride);
        final int newHeight = (int)Math.ceil((double)level.getHeigth() / stride);
        final Matrix ret = new Matrix(newWidth, newHeight);
        for(int i = 0; i < newWidth; i++) {
            for(int j = 0; j < newHeight; j++) {
                final double value = calculateWindowMaximumValue(level, i, j);
                ret.setValue(i, j, value);
            }
        }
        return ret;
    }

    private double calculateWindowMaximumValue(final Matrix level, final int width, final int height) {
        double ret = 0;
        for(int i = 0; i < this.windowSize; i++) {
            for(int j = 0; j < this.windowSize; j++) {
                final int levelWidth = width + i;
                final int levelHeight = height + j;
                if(!level.outOfBounds(levelWidth, levelHeight)) {
                    final double value = level.getValue(levelWidth, levelHeight);
                    ret = Math.max(ret, value);
                }
            }
        }
        return ret;
    }

}
