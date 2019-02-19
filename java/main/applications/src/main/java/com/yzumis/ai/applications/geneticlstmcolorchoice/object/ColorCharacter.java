package com.yzumis.ai.applications.geneticlstmcolorchoice.object;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.getopt.GetOptsException;
import com.yzumis.ai.applications.common.screen.Paintable;
import com.yzumis.ai.commonneuron.BaseNeuronFactory;
import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Reproducible;
import com.yzumis.ai.lstm.Lstm;
import com.yzumis.ai.mlp.Mlp;
import com.yzumis.ai.neuron.NeuronFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorCharacter implements Reproducible, Paintable {

    private static final double X_SQUARE_SIZE = Scenario.X_SQUARE_SIZE;
    private static final double Y_SQUARE_SIZE = Scenario.Y_SQUARE_SIZE;
    private static final Color COLOR = Color.BLACK;
    private static final Color DEATH_COLOR = Color.LIGHT_GRAY;
    private static final int MLP_GOAL_DETECTOR_NUMBER_OF_INPUTS = 10;
    private static final int MLP_EMPTY_DETECTOR_NUMBER_OF_INPUTS = 10;
    private static final List<Integer> MLP_GOAL_DETECTOR_NEURONS_PER_LAYER = new ArrayList<>();
    private static final List<Integer> MLP_EMPTY_DETECTOR_NEURONS_PER_LAYER = new ArrayList<>();
    private static final BaseNeuronFactory BASE_NEURON_FACTORY = new NeuronFactory();
    private static final List<Integer> LSTM_MLP_NEURONS_PER_LAYER = new ArrayList<>();
    private static final int Z_INDEX = 1;

    static {
        LSTM_MLP_NEURONS_PER_LAYER.add(10);
        LSTM_MLP_NEURONS_PER_LAYER.add(5);
    }

    static {
        MLP_GOAL_DETECTOR_NEURONS_PER_LAYER.add(10);
        MLP_GOAL_DETECTOR_NEURONS_PER_LAYER.add(1);
    }

    static {
        MLP_EMPTY_DETECTOR_NEURONS_PER_LAYER.add(5);
        MLP_EMPTY_DETECTOR_NEURONS_PER_LAYER.add(1);
    }

    private final Scenario scenario;
    private final Goal goal;
    private final Mlp mlpGoalDetector;
    private final Mlp mlpEmptyDetector;
    private final Lstm lstm;
    private int positionX;
    private int positionY;
    private boolean alive;
    private double live;
    private double fitness;

    public ColorCharacter(final Scenario scenario, final Goal goal) {
        this.scenario = scenario;
        this.goal = goal;
        this.positionX = 0;
        this.positionY = 0;
        this.alive = true;
        this.live = 100;
        this.fitness = 0d;
        this.lstm = new Lstm(10, 5, LSTM_MLP_NEURONS_PER_LAYER, LSTM_MLP_NEURONS_PER_LAYER, LSTM_MLP_NEURONS_PER_LAYER, LSTM_MLP_NEURONS_PER_LAYER, BASE_NEURON_FACTORY);
        this.mlpGoalDetector = new Mlp(MLP_GOAL_DETECTOR_NUMBER_OF_INPUTS, MLP_GOAL_DETECTOR_NEURONS_PER_LAYER, BASE_NEURON_FACTORY);
        this.mlpEmptyDetector = new Mlp(MLP_EMPTY_DETECTOR_NUMBER_OF_INPUTS, MLP_EMPTY_DETECTOR_NEURONS_PER_LAYER, BASE_NEURON_FACTORY);
    }

    public ColorCharacter(final Scenario scenario, final Goal goal, final Lstm lstm, final Mlp mlpGoalDetector, final Mlp mlpEmptyDetector) {
        this.scenario = scenario;
        this.goal = goal;
        this.positionX = 0;
        this.positionY = 0;
        this.alive = true;
        this.live = 100;
        this.fitness = 0d;
        this.lstm = lstm;
        this.mlpGoalDetector = mlpGoalDetector;
        this.mlpEmptyDetector = mlpEmptyDetector;
    }

    public ColorCharacter(final ColorCharacter colorCharacter) {
        this.scenario = colorCharacter.scenario;
        this.positionX = 0;
        this.positionY = 0;
        this.alive = true;
        this.live = 100;
        this.fitness = 0d;
        this.goal = colorCharacter.goal;
        this.lstm = colorCharacter.lstm;
        this.mlpGoalDetector = colorCharacter.mlpGoalDetector;
        this.mlpEmptyDetector = colorCharacter.mlpEmptyDetector;
    }


    public void execute() {
        if(this.alive) {
            final Vector lstmInputs = this.scenario.getInput(this.positionX, this.positionY).toVector();
            this.lstm.calculateOutput(lstmInputs);
            final Vector lstmOutputs = this.lstm.getOutput();

            final Input inputLeft = this.scenario.getInput(this.positionX - 1, this.positionY);
            final Input inputDown = this.scenario.getInput(this.positionX, this.positionY - 1);
            final Input inputUp = this.scenario.getInput(this.positionX, this.positionY + 1);
            final Input inputRight = this.scenario.getInput(this.positionX + 1, this.positionY);

            final Vector inputLeftGoalDetectorOutputs = this.mlpGoalDetector.calculateOutputs(lstmOutputs.concatenate(inputLeft.toVector()));
            final Vector inputDownGoalDetectorOutputs = this.mlpGoalDetector.calculateOutputs(lstmOutputs.concatenate(inputDown.toVector()));
            final Vector inputUpGoalDetectorOutputs = this.mlpGoalDetector.calculateOutputs(lstmOutputs.concatenate(inputUp.toVector()));
            final Vector inputRightGoalDetectorOutputs = this.mlpGoalDetector.calculateOutputs(lstmOutputs.concatenate(inputRight.toVector()));

            final Vector inputLeftEmptyDetectorOutputs = this.mlpGoalDetector.calculateOutputs(inputLeft.toVector());
            final Vector inputDownEmptyDetectorOutputs = this.mlpGoalDetector.calculateOutputs(inputDown.toVector());
            final Vector inputUpEmptyDetectorOutputs = this.mlpGoalDetector.calculateOutputs(inputUp.toVector());
            final Vector inputRightEmptyDetectorOutputs = this.mlpGoalDetector.calculateOutputs(inputRight.toVector());

            this.updatePosition(inputLeftGoalDetectorOutputs, inputDownGoalDetectorOutputs, inputUpGoalDetectorOutputs, inputRightGoalDetectorOutputs, inputLeftEmptyDetectorOutputs, inputDownEmptyDetectorOutputs, inputUpEmptyDetectorOutputs, inputRightEmptyDetectorOutputs);
            this.updateGoal();
        }
    }

    private void updatePosition(
        Vector inputLeftGoalDetectorOutputs,
        Vector inputDownGoalDetectorOutputs,
        Vector inputUpGoalDetectorOutputs,
        Vector inputRightGoalDetectorOutputs,
        Vector inputLeftEmptyDetectorOutputs,
        Vector inputDownEmptyDetectorOutputs,
        Vector inputUpEmptyDetectorOutputs,
        Vector inputRightEmptyDetectorOutputs) {
        final double inputLeftGoalDetectorOutput = inputLeftGoalDetectorOutputs.get(0);
        final double inputDownGoalDetectorOutput = inputDownGoalDetectorOutputs.get(0);
        final double inputUpGoalDetectorOutput = inputUpGoalDetectorOutputs.get(0);
        final double inputRightGoalDetectorOutput = inputRightGoalDetectorOutputs.get(0);
        final double inputLeftEmptyDetectorOutput = inputLeftEmptyDetectorOutputs.get(0);
        final double inputDownEmptyDetectorOutput = inputDownEmptyDetectorOutputs.get(0);
        final double inputUpEmptyDetectorOutput = inputUpEmptyDetectorOutputs.get(0);
        final double inputRightEmptyDetectorOutput = inputRightEmptyDetectorOutputs.get(0);
        if(valueRepresentativeAndGreaterThanOthers(inputLeftGoalDetectorOutput, inputDownGoalDetectorOutput, inputUpGoalDetectorOutput, inputRightGoalDetectorOutput)) {
            this.positionX--;
        } else if(valueRepresentativeAndGreaterThanOthers(inputDownGoalDetectorOutput, inputLeftGoalDetectorOutput, inputUpGoalDetectorOutput, inputRightGoalDetectorOutput)) {
            this.positionY--;
        } else if(valueRepresentativeAndGreaterThanOthers(inputUpGoalDetectorOutput, inputLeftGoalDetectorOutput, inputDownGoalDetectorOutput, inputRightGoalDetectorOutput)) {
            this.positionY++;
        } else if(valueRepresentativeAndGreaterThanOthers(inputRightGoalDetectorOutput, inputLeftGoalDetectorOutput, inputDownGoalDetectorOutput, inputUpGoalDetectorOutput)) {
            this.positionX++;
        } else if(valueRepresentativeAndGreaterThanOthers(inputLeftEmptyDetectorOutput, inputDownEmptyDetectorOutput, inputUpEmptyDetectorOutput, inputRightEmptyDetectorOutput)) {
            this.positionX--;
        } else if(valueRepresentativeAndGreaterThanOthers(inputDownEmptyDetectorOutput, inputLeftEmptyDetectorOutput, inputUpEmptyDetectorOutput, inputRightEmptyDetectorOutput)) {
            this.positionY--;
        } else if(valueRepresentativeAndGreaterThanOthers(inputUpEmptyDetectorOutput, inputLeftEmptyDetectorOutput, inputDownEmptyDetectorOutput, inputRightEmptyDetectorOutput)) {
            this.positionY++;
        } else if(valueRepresentativeAndGreaterThanOthers(inputRightEmptyDetectorOutput, inputLeftEmptyDetectorOutput, inputDownEmptyDetectorOutput, inputUpEmptyDetectorOutput)) {
            this.positionX++;
        } else {
            final Random random = new Random();
            final double randomDouble = random.nextDouble();
            if(randomDouble < 0.25d) {
                this.positionX--;
            } else if(randomDouble < 0.5d) {
                this.positionY--;
            } else if(randomDouble < 0.75d) {
                this.positionY++;
            } else {
                this.positionX++;
            }
        }
        this.live--;
        if(this.live <= 0d) {
            this.alive = false;
        }
    }

    private boolean valueRepresentativeAndGreaterThanOthers(final double value, final double value1, final double value2, final double value3) {
        return value > 0.5d && value >= value1 && value >= value2 && value >= value3;
    }

    private void updateGoal() {
        final Input inputGoal = this.goal.getInput();
        final Input input = this.scenario.getInput(this.positionX, this.positionY);
        if(input.equals(Input.VOID)) {
             this.live = -1;
        } else if(input.equals(Input.EMPTY)) {
            this.fitness++;
        } else if(input.equals(Input.BLUE)){
            this.live = -1;
        } else if(input.equals(inputGoal)) {
            this.fitness++;
            this.live+= 10;
        }  else {
            this.live = -1;
        }
        if(this.live <= 0d) {
            this.alive = false;
        }
    }

    public double getFitness() {
        return this.fitness;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public Reproducible reproduce(final Reproducible reproducible, final double mutationRate) {
        final ColorCharacter colorCharacter = (ColorCharacter) reproducible;
        final Lstm childLstm = (Lstm) this.lstm.reproduce(colorCharacter.lstm, mutationRate);
        final Mlp childMlpGoalDetector = (Mlp) this.mlpGoalDetector.reproduce(colorCharacter.mlpGoalDetector, mutationRate);
        final Mlp childMlpEmptyDetector = (Mlp) this.mlpEmptyDetector.reproduce(colorCharacter.mlpEmptyDetector, mutationRate);
        return new ColorCharacter(this.scenario, this.goal, childLstm, childMlpGoalDetector, childMlpEmptyDetector);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    @Override
    public void paint(final Graphics graphics) {
        final Color previousColor = graphics.getColor();
        if(this.alive) {
            graphics.setColor(COLOR);
        } else {
            graphics.setColor(DEATH_COLOR);
        }
        if(GraphicUtil.ISOMETRIC_GRAPHICS) {
            final int x = 300 + positionX * (int) X_SQUARE_SIZE - positionY * (int) Y_SQUARE_SIZE;
            final int y = 100 + positionX * (int) X_SQUARE_SIZE + positionY * (int) Y_SQUARE_SIZE - Z_INDEX * (int) Y_SQUARE_SIZE;
            GraphicUtil.paintIsometricCube(graphics,  x, y, (int) X_SQUARE_SIZE, (int) Y_SQUARE_SIZE, true, true);
        } else {
            graphics.fillRect(positionX * (int) X_SQUARE_SIZE, positionY * (int) Y_SQUARE_SIZE, (int) X_SQUARE_SIZE, (int) Y_SQUARE_SIZE);
        }
        graphics.setColor(previousColor);
    }
}
