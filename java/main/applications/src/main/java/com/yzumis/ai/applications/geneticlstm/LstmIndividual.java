package com.yzumis.ai.applications.geneticlstm;

import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Individual;
import com.yzumis.ai.genetic.Reproducible;
import com.yzumis.ai.lstm.Lstm;


public class LstmIndividual extends Individual<Lstm, String> {

    public LstmIndividual(final Lstm lstm, final double mutationRate, final String goal) {
        super(lstm, mutationRate, goal);
    }

    public void calculateFitness() {
        float fitness = 0;
        for(int i = 0; i < super.getGoal().length() - 1; i++) {
            final Vector inputVector = calculateVector(super.getGoal().charAt(i));
            final Lstm lstm = ((Lstm)getReproducible());
            lstm.calculateOutput(inputVector);
            final Vector outputVector = lstm.getOutput();
            final char predicted = calculateCharacter(outputVector);
            final char expected = super.getGoal().charAt(i + 1);
            if(predicted == expected) {
                fitness++;
            }
        }
        super.setFitness(fitness);
    }

    @Override
    public Individual reproduce(Individual individual) {
        final Reproducible reproducible = this.getReproducible().reproduce(individual.getReproducible(), getMutationRate());
        return new LstmIndividual((Lstm)reproducible, this.getMutationRate(), this.getGoal());
    }

    private char calculateCharacter(final Vector outputVector) {
        final double vala = outputVector.toList().get(0);
        final double valb = outputVector.toList().get(1);
        final double valc = outputVector.toList().get(2);
        final char ret;
        if(vala > valb && vala > valc) {
            ret = 'a';
        } else if(valb > vala && valb > valc) {
            ret = 'b';
        } else {
            ret = 'c';
        }
        return ret;
    }

    private Vector calculateVector(final char value) {
        final Vector ret;
        if(value == 'a') {
            ret = new Vector(1d, 0d, 0d);
        } else if(value == 'b') {
            ret = new Vector(0d, 1d, 0d);
        } else {
            ret = new Vector(0d, 0d, 1d);
        }
        return ret;
    }

}
