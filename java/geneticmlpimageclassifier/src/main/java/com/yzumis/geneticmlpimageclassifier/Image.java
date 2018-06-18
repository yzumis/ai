package com.yzumis.geneticmlpimageclassifier;


import com.yzumis.geneticmlpimageclassifier.input.Input2D;
import com.yzumis.geneticmlpimageclassifier.input.Matrix;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Image {

    private final ImageType imageType;
    private final List<Color> colors;

    public Image(final byte[] imageBytes) {
        this.imageType = ImageType.byteToImageType(imageBytes[0]);
        this.colors = new ArrayList<>();
        for(int i = 0; i < 1024; i++) {
            final int red = imageBytes[i + 1] + 128; // 128 needs to be added as the range of byte is [-128, 127]
            final int green = imageBytes[i + 1 + 1024] + 128;
            final int blue = imageBytes[i + 1 + 2048] + 128;
            final Color color = new Color(red, green, blue);
            this.colors.add(color);
        }
    }

    public Input2D toInput2D() {
        final Matrix redMatrix = new Matrix(32, 32);
        final Matrix greenMatrix = new Matrix(32, 32);
        final Matrix blueMatrix = new Matrix(32, 32);

        for(int i = 0; i < colors.size(); i++) {
            final Color color = colors.get(i);
            final int width = calculateWidth(i);
            final int height = calculateHeight(i);
            redMatrix.setValue(width, height, color.getRed());
            greenMatrix.setValue(width, height, color.getGreen());
            blueMatrix.setValue(width, height, color.getBlue());
        }

        final List<Matrix> levels = new ArrayList<>();
        levels.add(redMatrix);
        levels.add(greenMatrix);
        levels.add(blueMatrix);
        return new Input2D(levels);
    }

    private final int calculateWidth(final int value) {
        return value % 32;
    }

    private final int calculateHeight(final int value) {
        return value / 32;
    }

    public ImageType getImageType() {
        return imageType;
    }

}
