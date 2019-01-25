package com.yzumis.ai.applications.geneticlstmcolorchoice.object;

import com.yzumis.ai.applications.common.screen.Paintable;
import com.yzumis.ai.commonneuron.BaseNeuronFactory;
import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Reproducible;
import com.yzumis.ai.lstm.Lstm;
import com.yzumis.ai.lstm.LstmNeuronFactory;
import com.yzumis.ai.mlp.Mlp;
import com.yzumis.ai.neuron.NeuronFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ColorCharacter implements Reproducible, Paintable {

    private static final int NUMBER_OF_INPUTS = 30;
    private static final double X_SIZE = Scenario.X_SQUARE_SIZE;
    private static final double Y_SIZE = Scenario.Y_SQUARE_SIZE;
    private static final Color COLOR = Color.BLACK;
    private static final List<Integer> MLP_NEURONS_PER_LAYER = new ArrayList<>();
    private static final BaseNeuronFactory BASE_NEURON_FACTORY = new NeuronFactory();
    private static final List<Integer> LSTM_MLP_NEURONS_PER_LAYER = new ArrayList<>();

    static {
        LSTM_MLP_NEURONS_PER_LAYER.add(10);
        LSTM_MLP_NEURONS_PER_LAYER.add(5);
    }

    static {
        MLP_NEURONS_PER_LAYER.add(30);
        MLP_NEURONS_PER_LAYER.add(60);
        MLP_NEURONS_PER_LAYER.add(4);
    }

    private final Scenario scenario;
    private final Mlp mlp;
    private final Lstm lstm;
    private int positionX;
    private int positionY;
    private boolean alive;
    private double live;
    private double fitness;

    public ColorCharacter(final Scenario scenario) {
        this.scenario = scenario;
        this.positionX = 0;
        this.positionY = 0;
        this.alive = true;
        this.live = 100;
        this.fitness = 0d;
        this.lstm = new Lstm(10, 5, LSTM_MLP_NEURONS_PER_LAYER, LSTM_MLP_NEURONS_PER_LAYER, LSTM_MLP_NEURONS_PER_LAYER, LSTM_MLP_NEURONS_PER_LAYER, BASE_NEURON_FACTORY);
        this.mlp = new Mlp(NUMBER_OF_INPUTS, MLP_NEURONS_PER_LAYER, BASE_NEURON_FACTORY);
    }

    public ColorCharacter(final Scenario scenario, final Lstm lstm, final Mlp mlp) {
        this.scenario = scenario;
        this.positionX = 0;
        this.positionY = 0;
        this.alive = true;
        this.live = 100;
        this.fitness = 0d;
        this.lstm = lstm;
        this.mlp = mlp;
    }

    public ColorCharacter(final ColorCharacter colorCharacter) {
        this.scenario = colorCharacter.scenario;
        this.positionX = 0;
        this.positionY = 0;
        this.alive = true;
        this.live = 100;
        this.fitness = 0d;
        this.lstm = colorCharacter.lstm;
        this.mlp = colorCharacter.mlp;
    }


    public void execute() {
        if(this.alive) {
            final Vector lstmInputs = this.scenario.getInput(this.positionX, this.positionY).toVector();
            this.lstm.calculateOutput(lstmInputs);
            final Vector lstmOutputs = this.lstm.getOutput();
            final Vector inputValues = this.readInputValues();
            final Vector mlpInputs = inputValues.concatenate(lstmOutputs);
            final Vector mlpOutputs = this.mlp.calculateOutputs(mlpInputs);
            this.updatePosition(mlpOutputs);
            this.updateGoal();
        }
    }

    private Vector readInputValues() {
        final Input input0 = this.scenario.getInput(this.positionX, this.positionY);
        final Input input1 = this.scenario.getInput(this.positionX - 1, this.positionY);;
        final Input input2 = this.scenario.getInput(this.positionX, this.positionY - 1);
        final Input input3 = this.scenario.getInput(this.positionX, this.positionY + 1);
        final Input input4 = this.scenario.getInput(this.positionX + 1, this.positionY);
        return (input0.toVector())
                .concatenate(input1.toVector())
                .concatenate(input2.toVector())
                .concatenate(input3.toVector())
                .concatenate(input4.toVector());
    }

    private void updatePosition(final Vector vector) {
        final Direction direction = Direction.fromVector(vector);
        switch (direction) {
            case UP:
                this.positionY++;
                break;
            case DOWN:
                this.positionY--;
                break;
            case LEFT:
                this.positionX--;
                break;
            case RIGHT:
                this.positionX++;
                break;
            default:
                throw new IllegalStateException();
        }
        this.live--;
        if(this.live <= 0d) {
            this.alive = false;
        }
    }

    private void updateGoal() {
        final Input goal = this.scenario.getGoal();
        final Input input = this.scenario.getInput(this.positionX, this.positionY);
        if(input.equals(Input.VOID)) {
             this.live = -1;
        } else if(input.equals(Input.EMPTY)) {
            this.fitness+= 0.1;
        } else if(input.equals(goal)) {
            this.fitness+= 10;
            this.live += 10;
        } else {
            if(this.fitness > 0) {
                this.fitness -= 1;
            }
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
        final Mlp childMlp = (Mlp) this.mlp.reproduce(colorCharacter.mlp, mutationRate);
        return new ColorCharacter(this.scenario, lstm, childMlp);
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
        graphics.setColor(COLOR);
        graphics.fillRect(positionX * (int) X_SIZE, positionY * (int) Y_SIZE, (int) X_SIZE, (int) Y_SIZE);
        graphics.setColor(previousColor);
    }
}
