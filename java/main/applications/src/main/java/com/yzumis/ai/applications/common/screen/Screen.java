package com.yzumis.ai.applications.common.screen;

import com.yzumis.ai.applications.geneticmlpcar.object.Background;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

public class Screen extends JFrame {

    private final Background background;
    private final boolean isometricScreen;
    private List<Paintable> paintables;
    private Integer generation;

    public Screen(final int xSize, final int ySize) {
        this(xSize, ySize, false);
    }

    public Screen(final int xSize, final int ySize, final boolean isometricScreen) {
        this.background = new Background(this.getX(), this.getY());
        this.isometricScreen = isometricScreen;
        this.setSize(xSize, ySize);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public void setupScreen(final List<Paintable> paintables, final int generation) {
        this.paintables = paintables;
        this.generation = generation;
    }

    @Override
    public void paint(final Graphics graphics) {
        final BufferedImage bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().fillRect(0, 0, this.getWidth(), this.getHeight());
        final Graphics bufferedGraphics = bufferedImage.getGraphics();
        this.background.paint(bufferedGraphics);
        if(isometricScreen) {
            final List<Paintable> sortedPaintables = sortIsometricPaintables(this.paintables);
            this.paintPaintables(sortedPaintables, bufferedGraphics);
        } else {
            this.paintPaintables(this.paintables, bufferedGraphics);
        }
        // Display generation:
        if(this.generation !=null) {
            paintGeneration(bufferedGraphics);
        }
        graphics.drawImage(bufferedImage, 0, 0, null);
    }

    private List<Paintable> sortIsometricPaintables(final List<Paintable> paintables) {
        return paintables; // ### TODO Implement this method
    }

    private void paintPaintables(final List<Paintable> paintables, final Graphics graphics) {
        if(paintables != null) {
            for (final Paintable paintable : paintables) {
                paintable.paint(graphics);
            }
        }
    }

    public void paintGeneration(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.drawString("Generation = " + this.generation, 10, 10);
        graphics.setColor(color);
    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public int getY() {
        return super.getY();
    }

}

