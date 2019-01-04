package com.yzumis.ia.applications.convolutionalgeneticmlpimageclassifier.input;

import com.yzumis.ia.lstm.Vector;

import java.util.ArrayList;
import java.util.List;

public class Input2D {

    private final List<Matrix> levels;

    public Input2D(final List<Matrix> levels) {
        this.levels = levels;
    }

    public Vector toVector() {
        final int levelsWidth = this.levels.get(0).getWidth();
        final int levelsHeight = this.levels.get(0).getHeigth();
        final int levelsDepth = this.levels.size();
        final List<Double> inputValues = new ArrayList<>();
        for(int i = 0; i < levelsWidth; i++) {
            for(int j = 0; j < levelsHeight; j++) {
                for(int k = 0; k < levelsDepth; k++) {
                    final double value = this.levels.get(k).getValue(i, j);
                    inputValues.add(value);
                }
            }
        }
        return new Vector(inputValues);
    }

    public List<Matrix> getLevels() {
        return levels;
    }

}
