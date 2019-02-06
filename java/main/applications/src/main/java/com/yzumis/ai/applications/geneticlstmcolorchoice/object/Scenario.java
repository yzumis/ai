package com.yzumis.ai.applications.geneticlstmcolorchoice.object;

import com.yzumis.ai.applications.common.screen.Paintable;
import com.yzumis.ai.applications.common.screen.Screen;
import com.yzumis.ai.applications.geneticlstmcolorchoice.Application;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Scenario implements Paintable {

    private static final int X_SIZE = 10;
    private static final int Y_SIZE = 10;
    public static final double X_SQUARE_SIZE = Application.X_SIZE / X_SIZE / 3;
    public static final double Y_SQUARE_SIZE = Application.Y_SIZE / Y_SIZE / 3;
    private static final int NUMBER_OF_OTHER_INPUTS = 5;
    private static final int NUMBER_OF_GOAL_INPUTS = 20;
    private static final int Z_INDEX = 0;

    private final Goal goal;
    private Input[][] values;

    public Scenario(final Goal goal) {
        this.goal = goal;
        this.recalculate();
    }

    public void recalculate() {
        this.values = this.generateRandomValues();
    }

    private Input[][] generateRandomValues() {
        final Input[][] ret = initializeEmptyInputs();
        ret[0][0] = this.goal.getInput();

        for(int i = 0; i < NUMBER_OF_GOAL_INPUTS; i++) {
            generateRandomValue(this.goal.getInput(), ret);
        }
        for(int i = 0; i < NUMBER_OF_OTHER_INPUTS; i++) {
            if(this.goal.getInput().equals(Input.RED)) {
                generateRandomValue(Input.GREEN, ret);
                generateRandomValue(Input.BLUE, ret);
            } else if(this.goal.getInput().equals(Input.GREEN)) {
                generateRandomValue(Input.RED, ret);
                generateRandomValue(Input.BLUE, ret);
            } else if(this.goal.getInput().equals(Input.BLUE)) {
                generateRandomValue(Input.RED, ret);
                generateRandomValue(Input.GREEN, ret);
            } else {
                throw new IllegalStateException();
            }
        }
        return ret;
    }

    private Input[][] initializeEmptyInputs() {
        final Input[][] ret = new Input[X_SIZE][Y_SIZE];
        for(int i = 0; i < X_SIZE; i++) {
            for(int j = 0; j < Y_SIZE; j++) {
                ret[i][j] = Input.EMPTY;
            }
        }
        return ret;
    }

    private void generateRandomValue(final Input input, final Input[][] inputs) {
        final Random random = new Random();
        final int xPosition = random.nextInt(X_SIZE);
        final int yPosition = random.nextInt(Y_SIZE);
        if(((xPosition == 0) && (yPosition == 0)) || !inputs[xPosition][yPosition].equals(Input.EMPTY)) {
            generateRandomValue(input, inputs);
        } else {
            inputs[xPosition][yPosition] = input;
        }
    }

    public void execute(final List<ColorCharacter> colorCharacters) {
        for(final ColorCharacter colorCharacter: colorCharacters) {
            processColorCharacter(colorCharacter);
        }
    }

    public void execute(final ColorCharacter colorCharacter) {
        processColorCharacter(colorCharacter);
    }

    private void processColorCharacter(final ColorCharacter colorCharacter) {
        final Input input = this.getInput(colorCharacter.getPositionX(), colorCharacter.getPositionY());
        if(input.equals(Input.RED) || input.equals(Input.GREEN) || input.equals(Input.BLUE)) {
            values[colorCharacter.getPositionX()][colorCharacter.getPositionY()] = Input.EMPTY;
            generateRandomValue(input, values);
        }
    }

    public Input getInput(final int x, final int y) {
        final Input ret;
        if((x < 0) || (x >= X_SIZE) || (y < 0) || (y >= Y_SIZE)) {
            ret = Input.VOID;
        } else {
            ret = this.values[x][y];
        }
        return ret;
    }

    @Override
    public void paint(final Graphics graphics) {
        // Paint surface
        for(int i = 0; i < this.values.length; i++) {
            for(int j = 0; j < this.values[i].length; j++) {
                this.paintSurface(graphics, i, j);
            }
        }
        // Paint inputs
        for(int i = 0; i < this.values.length; i++) {
            for(int j = 0; j < this.values[i].length; j++) {
                final Input input = this.values[i][j];
                if(!input.equals(Input.EMPTY)) {
                    this.paintInput(graphics, i, j, input);
                }
            }
        }
    }

    private void paintSurface(final Graphics graphics, final int positionX, int positionY) {
        final Color previousColor = graphics.getColor();
        graphics.setColor(Input.EMPTY.toColor());
        if(GraphicUtil.ISOMETRIC_GRAPHICS) {
            final int x = 300 + positionX * (int) X_SQUARE_SIZE - positionY * (int) Y_SQUARE_SIZE;
            final int y = 100 + positionX * (int) X_SQUARE_SIZE + positionY * (int) Y_SQUARE_SIZE - Z_INDEX * (int) Y_SQUARE_SIZE;
            GraphicUtil.paintIsometricCube(graphics,  x, y, (int) X_SQUARE_SIZE, (int) Y_SQUARE_SIZE, true, true);
        } else {
            graphics.fillRect(positionX * (int) X_SQUARE_SIZE, positionY * (int) Y_SQUARE_SIZE, (int) X_SQUARE_SIZE, (int) Y_SQUARE_SIZE);
        }
        graphics.setColor(previousColor);
    }

    private void paintInput(final Graphics graphics, final int positionX, int positionY, final Input input) {
        final Color previousColor = graphics.getColor();
        graphics.setColor(input.toColor());
        if(GraphicUtil.ISOMETRIC_GRAPHICS) {
            final int x = 300 + positionX * (int) X_SQUARE_SIZE - positionY * (int) Y_SQUARE_SIZE;
            final int y = 100 + positionX * (int) X_SQUARE_SIZE + positionY * (int) Y_SQUARE_SIZE - (Z_INDEX + 1) * (int) Y_SQUARE_SIZE;
            GraphicUtil.paintIsometricCube(graphics,  x, y, (int) X_SQUARE_SIZE, (int) Y_SQUARE_SIZE, true, true);
        } else {
            graphics.fillRect(positionX * (int) X_SQUARE_SIZE, positionY * (int) Y_SQUARE_SIZE, (int) X_SQUARE_SIZE, (int) Y_SQUARE_SIZE);
        }
        graphics.setColor(previousColor);
    }

}
