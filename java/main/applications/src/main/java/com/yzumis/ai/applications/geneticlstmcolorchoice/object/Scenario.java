package com.yzumis.ai.applications.geneticlstmcolorchoice.object;

import com.yzumis.ai.applications.common.screen.Paintable;
import com.yzumis.ai.applications.common.screen.Screen;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Scenario implements Paintable {

    private static final int X_SIZE = 10;
    private static final int Y_SIZE = 10;
    public static final double X_SQUARE_SIZE = Screen.X_SIZE / X_SIZE;
    public static final double Y_SQUARE_SIZE = Screen.Y_SIZE / Y_SIZE;
    private static final int NUMBER_OF_OTHER_INPUTS = 5;
    private static final int NUMBER_OF_GOAL_INPUTS = 20;

    private Input goal;
    private Input[][] values;

    public Scenario() {
        this.recalculate();
    }

    public void recalculate() {
        this.goal = this.generateRandomGoal();
        this.values = this.generateRandomValues();
    }

    private Input[][] generateRandomValues() {
        final Input[][] ret = initializeEmptyInputs();
        ret[0][0] = this.goal;

        for(int i = 0; i < NUMBER_OF_GOAL_INPUTS; i++) {
            generateRandomValue(this.goal, ret);
        }
        for(int i = 0; i < NUMBER_OF_OTHER_INPUTS; i++) {
            if(this.goal.equals(Input.RED)) {
                generateRandomValue(Input.GREEN, ret);
                generateRandomValue(Input.BLUE, ret);
            } else if(this.goal.equals(Input.GREEN)) {
                generateRandomValue(Input.RED, ret);
                generateRandomValue(Input.BLUE, ret);
            } else if(this.goal.equals(Input.BLUE)) {
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

    private void processColorCharacter(final ColorCharacter colorCharacter) {
        final Input input = this.getInput(colorCharacter.getPositionX(), colorCharacter.getPositionY());
        if(input.equals(Input.RED) || input.equals(Input.GREEN) || input.equals(Input.GREEN)) {
            values[colorCharacter.getPositionX()][colorCharacter.getPositionY()] = Input.EMPTY;
            generateRandomValue(input, values);
        }
    }

    private Input generateRandomGoal() {
        // The Input.BLUE is never a goal. So the ColorCharacter should always avoid it.
        final Input ret;
        final Random random = new Random();
        final int randomInt = random.nextInt(2);
        if(randomInt == 0) {
            ret = Input.RED;
        } else {
            ret = Input.GREEN;
        }
        return ret;
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

    public Input getGoal() {
        return goal;
    }

    @Override
    public void paint(final Graphics graphics) {
        for(int i = 0; i < this.values.length; i++) {
            for(int j = 0; j < this.values[i].length; j++) {
                final Input input = this.values[i][j];
                this.paintInput(graphics, i, j, input);
                this.paintGoal(graphics);
            }
        }
    }

    private void paintGoal(final Graphics graphics) {
        final Color previousColor = graphics.getColor();
        graphics.setColor(this.goal.toColor());
        graphics.fillRect(0, 0, 20, 20);
        graphics.setColor(previousColor);
    }

    private void paintInput(final Graphics graphics, final int positionX, int positionY, final Input input) {
        final Color previousColor = graphics.getColor();
        graphics.setColor(input.toColor());
        graphics.fillRect(positionX * (int) X_SQUARE_SIZE, positionY * (int) Y_SQUARE_SIZE, (int) X_SQUARE_SIZE, (int) Y_SQUARE_SIZE);
        graphics.setColor(previousColor);
    }

}
