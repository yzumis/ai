package com.yzumis.geneticmlpcar.objects;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;

/**
 * Created by Yzumi on 24/12/2017.
 */
public class Screen extends JFrame {

    public static float X_SIZE = 720;
    public static float Y_SIZE = 480;
    private Background background = new Background();
    private Track track;
    private List<Car> cars;
    private Integer generation;

    public Screen() {
        this.setSize((int) X_SIZE, (int) Y_SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public void setupScreen(final Track track, final List<Car> cars, final int generation) {
        this.track = track;
        this.cars = cars;
        this.generation = generation;
    }

    @Override
    public void paint(final Graphics graphics) {
        final BufferedImage bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().fillRect(0, 0, this.getWidth(), this.getHeight());
        final Graphics bufferedGraphics = bufferedImage.getGraphics();
        this.background.paint(bufferedGraphics);
        // Display generation:
        if(this.generation !=null) {
            paintGeneration(bufferedGraphics);
        }
        if(this.track != null) {
            this.track.paint(bufferedGraphics);
        }
        if(this.cars != null) {
            for (final Car car : this.cars) {
                car.paint(bufferedGraphics);
            }
        }
        graphics.drawImage(bufferedImage, 0, 0, null);
    }

    public void paintGeneration(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.drawString("Generation = " + this.generation, 10, 10);
        graphics.setColor(color);
    }

}
