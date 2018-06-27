package com.yzumis.geneticmlpimageclassifier;

import com.yzumis.geneticmlpimageclassifier.input.Input2D;
import com.yzumis.geneticmlpimageclassifier.input.Matrix;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ApplicationImageReader extends JFrame {

    private static final int NUMBER_OF_IMAGES = 100;

    private Image image;

    public ApplicationImageReader() {
        this.setLocation(300, 300);
        this.setSize(32, 32);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);
        this.image = null;
    }

    @Override
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if(this.getImage() != null) {
            final Input2D input2D = this.getImage().toInput2D();
            final Matrix redMatrix = input2D.getLevels().get(0);
            final Matrix greenMatrix = input2D.getLevels().get(1);
            final Matrix blueMatrix = input2D.getLevels().get(2);

            for (int i = 0; i < redMatrix.getWidth(); i++) {
                for (int j = 0; j < redMatrix.getHeigth(); j++) {
                    final int red = (int) redMatrix.getValue(i, j);
                    final int green = (int) greenMatrix.getValue(i, j);
                    final int blue = (int) blueMatrix.getValue(i, j);

                    final Color color = new Color(red, green, blue);
                    graphics.setColor(color);
                    graphics.drawLine(i, j, i, j);
                }
            }
        }
    }

    public static void main(final String[] args) throws IOException, InterruptedException {
        final ApplicationImageReader applicationImageReader = new ApplicationImageReader();

        final CifarReader cifarReader = new CifarReader(NUMBER_OF_IMAGES);
        Image image;
        while ((image = cifarReader.readImage()) != null) {
            applicationImageReader.setImage(image);
            applicationImageReader.repaint();
            Thread.sleep(2000);
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
