package com.yzumis.geneticlstm;

import com.yzumis.genetic.Individual;
import com.yzumis.genetic.Reproducible;
import com.yzumis.lstm.Lstm;
import com.yzumis.lstm.Vector;

public class LstmIndividual extends com.yzumis.genetic.Individual<Lstm, String> {

    public LstmIndividual(final Lstm lstm, final float mutationRate, final String goal) {
        super(lstm, mutationRate, goal);
    }

    public void calculateFitness() {
        float fitness = 0;
        for(int i = 0; i < super.getGoal().length() - 1; i++) {
            final Vector inputVector = calculateVector(super.getGoal().charAt(i));
            final Vector outputVector = ((Lstm)getReproducible()).calculateOutput(inputVector);
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
        final float vala = outputVector.toList().get(0);
        final float valb = outputVector.toList().get(1);
        final float valc = outputVector.toList().get(2);
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
            ret = new Vector(1f, 0f, 0f);
        } else if(value == 'b') {
            ret = new Vector(0f, 1f, 0f);
        } else {
            ret = new Vector(0f, 0f, 1f);
        }
        return ret;
    }

}
