package driving;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;

/**
 * Created by Yzumi on 24/12/2017.
 */
public class Screen extends JFrame {

    public static float X_SIZE = 600;
    public static float Y_SIZE = 400;
    private final Track track;
    private final List<Car> cars;
    private final int generation;

    public Screen(final Track track, final List<Car> cars, final int generation) {
        this.track = track;
        this.cars = cars;
        this.generation = generation;
        this.setSize((int) X_SIZE, (int) Y_SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setVisible(true);
    }

    @Override
    public void paint(final Graphics graphics) {
        final BufferedImage bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().fillRect(0, 0, this.getWidth(), this.getHeight());
        final Graphics bufferedGraphics = bufferedImage.getGraphics();
        // Display generation:
        final Color color = bufferedGraphics.getColor();
        bufferedGraphics.setColor(Color.BLACK);
        bufferedGraphics.drawString("Generation = " + this.generation, 10, 10);
        bufferedGraphics.setColor(color);
        this.track.paint(bufferedGraphics);
        for(final Car car: this.cars) {
            car.paint(bufferedGraphics);
        }

        graphics.drawImage(bufferedImage, 0, 0, null);
    }

}
