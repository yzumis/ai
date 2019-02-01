package com.yzumis.ai.applications.geneticlstmcolorchoice.object;

import com.yzumis.ai.applications.common.screen.Paintable;

import java.awt.*;
import java.util.Random;

public class Goal implements Paintable {

    private static final double X_SQUARE_SIZE = Scenario.X_SQUARE_SIZE;
    private static final double Y_SQUARE_SIZE = Scenario.Y_SQUARE_SIZE;
    private static final int Z_INDEX = 0;

    private Input input;

    public Goal() {
        this.recalculate();
    }

    public void recalculate() {
        final Random random = new Random();
        final int randomInt = random.nextInt(2);
        if(randomInt == 0) {
            this.input = Input.RED;
        } else {
            this.input = Input.GREEN;
        }
    }

    public Input getInput() {
        return input;
    }

    @Override
    public void paint(Graphics graphics) {
        final Color previousColor = graphics.getColor();
        graphics.setColor(this.input.toColor());
        if(GraphicUtil.ISOMETRIC_GRAPHICS) {
            final int x = 150 + 0 * (int) X_SQUARE_SIZE - 0 * (int) Y_SQUARE_SIZE;
            final int y = 50 + 0 * (int) X_SQUARE_SIZE + 0 * (int) Y_SQUARE_SIZE - (Z_INDEX + 1)* (int) Y_SQUARE_SIZE;
            GraphicUtil.paintIsometricCube(graphics,  x, y, (int) X_SQUARE_SIZE, (int) Y_SQUARE_SIZE, true, true);
        } else {
            graphics.fillRect(0, 0, 20, 20);
        }
        graphics.setColor(previousColor);
    }

}
