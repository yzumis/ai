package com.yzumis.lstm;

import java.util.ArrayList;
import java.util.List;

public class Individual {

    private final Lstm lstm;
    private final String goal;
    private final float mutationRate;
    private float fitness;


    public Individual(final Lstm lstm, final String goal, final float mutationRate) {
        this.lstm = lstm;
        this.goal = goal;
        this.mutationRate = mutationRate;
    }

    public void calculateFitness() {
        // "abacabac";
        final List<Vector> inputs = new ArrayList<>();
        inputs.add(new Vector(1f, 0f, 0f));
        inputs.add(new Vector(0f, 1f, 0f));
        inputs.add(new Vector(1f, 0f, 0f));
        inputs.add(new Vector(0f, 0f, 1f));

        inputs.add(new Vector(1f, 0f, 0f));
        inputs.add(new Vector(0f, 1f, 0f));
        inputs.add(new Vector(1f, 0f, 0f));
        inputs.add(new Vector(0f, 0f, 1f));

        inputs.add(new Vector(1f, 0f, 0f));
        inputs.add(new Vector(0f, 1f, 0f));
        inputs.add(new Vector(1f, 0f, 0f));
        inputs.add(new Vector(0f, 0f, 1f));

        inputs.add(new Vector(1f, 0f, 0f));
        inputs.add(new Vector(0f, 1f, 0f));
        inputs.add(new Vector(1f, 0f, 0f));
        inputs.add(new Vector(0f, 0f, 1f));

        inputs.add(new Vector(1f, 0f, 0f));
        inputs.add(new Vector(0f, 1f, 0f));
        inputs.add(new Vector(1f, 0f, 0f));
        inputs.add(new Vector(0f, 0f, 1f)); // Last input is just for prediction purposes
        float fitness = 0;
        for(int i = 0; i < inputs.size() - 1; i++) {
            final Vector outputVector = this.lstm.calculateOutput(inputs.get(i));
            final char predicted = calculateCharacter(outputVector);
            final char expected = calculateCharacter(inputs.get(i+1));
            if(predicted == expected) {
                fitness++;
            }
        }
        if(fitness == 15 || fitness == 16 || fitness == 17 || fitness == 18) {
            System.out.println("GREAT");
        }
        if(fitness == 19) {
            System.out.println("AMAZING");
        }
        this.fitness = fitness;
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

    public Individual reproduce(Individual individual) {
        return new Individual(this.lstm.reproduce(individual.lstm, mutationRate), goal, this.mutationRate);
    }

    public float getFitness() {
        return fitness;
    }

}
