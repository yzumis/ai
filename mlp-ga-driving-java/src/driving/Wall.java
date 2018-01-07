package driving;

import java.awt.*;

/**
 * Created by Yzumi on 24/12/2017.
 */
public class Wall extends StaticObject implements Paintable {

    private static final Color WALL_COLOR = Color.BLACK;
    public static final float WALL_X_SIZE = Screen.X_SIZE / 36; // 20
    public static final float WALL_Y_SIZE = Screen.Y_SIZE / 24; // 20

    public Wall(final float x, final float y) {
        super(x, y, WALL_X_SIZE, WALL_Y_SIZE);
    }

    @Override
    public void paint(Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(WALL_COLOR);
        graphics.fillRect((int) getX(), (int) getY(), (int) getxSize(), (int) getySize());
        graphics.setColor(color);
    }
}
