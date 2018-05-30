package com.yzumis.geneticmlpimageclassifier;


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

    public List<Float> toInput() {
        final List<Float> ret = new ArrayList<>();
        for(final Color color: this.colors) {
            final List<Float> colorInputs = toInput(color);
            ret.addAll(colorInputs);
        }
        return ret;
    }

    private List<Float> toInput(final Color color) {
        final List<Float> ret = new ArrayList<>();
        ret.add(1f / 255f * (float)color.getRed());
        ret.add(1f / 255f * (float)color.getGreen());
        ret.add(1f / 255f * (float)color.getBlue());
        return ret;
    }

    public ImageType getImageType() {
        return imageType;
    }

}
