package com.yzumis.ai.applications.geneticlstmcolorchoice.object.train;

import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Goal;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Input;
import com.yzumis.ai.commonneuron.BaseNeuronFactory;
import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Individual;
import com.yzumis.ai.genetic.Reproducible;
import com.yzumis.ai.lstm.Lstm;
import com.yzumis.ai.neuron.NeuronFactory;

import java.util.ArrayList;
import java.util.List;

public class GoalMemory extends Individual<Lstm, Double> {

    private static final int LSTM_NUMBER_OF_INPUTS = 10;
    private static final int LSTM_NUMBER_OF_OUTPUTS = 5;
    private static final List<Integer> LSTM_MLP_NEURONS_PER_LAYER = new ArrayList<>();
    private static final BaseNeuronFactory BASE_NEURON_FACTORY = new NeuronFactory();

    static {
        LSTM_MLP_NEURONS_PER_LAYER.add(10);
        LSTM_MLP_NEURONS_PER_LAYER.add(5);
    }

    public GoalMemory(final double mutationRate, final Double goal) {
        this(new Lstm(LSTM_NUMBER_OF_INPUTS, LSTM_NUMBER_OF_OUTPUTS, LSTM_MLP_NEURONS_PER_LAYER, LSTM_MLP_NEURONS_PER_LAYER, LSTM_MLP_NEURONS_PER_LAYER, LSTM_MLP_NEURONS_PER_LAYER, BASE_NEURON_FACTORY), mutationRate, goal);
    }

    public GoalMemory(final Lstm lstm, final double mutationRate, final Double goal) {
        super(lstm, mutationRate, goal);
    }

    @Override
    public void calculateFitness() {
        double maximumFitness = 0;
        double fitness = 0;
        final Lstm lstm = (Lstm)this.getReproducible();
        for(final Input goal: Input.values()) {
            final Lstm currentGoalLstm = new Lstm(lstm);
            currentGoalLstm.calculateOutput(goal.toVector());
            for(int i = 0; i < 30; i++) {
                final Input input = Input.random();
                currentGoalLstm.calculateOutput(input.toVector());
                final Vector lstmOutPut = currentGoalLstm.getOutput();
                if(Input.fromVector(lstmOutPut).equals(goal)) {
                    fitness++;
                }
                maximumFitness++;
            }
        }
        this.setFitness(fitness / maximumFitness);
    }

    @Override
    public Individual reproduce(Individual individual) {
        final Reproducible reproducible = super.getReproducible().reproduce(individual.getReproducible(), getMutationRate());
        return new GoalMemory((Lstm) reproducible, getMutationRate(), getGoal());
    }

}
