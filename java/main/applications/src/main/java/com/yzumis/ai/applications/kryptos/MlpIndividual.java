package com.yzumis.ai.applications.kryptos;

import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.genetic.Individual;
import com.yzumis.ai.genetic.Reproducible;
import com.yzumis.ai.mlp.Mlp;

import java.util.List;


public class MlpIndividual extends Individual<Mlp, String> {

    private static final String TEXT = "OBKRUOXOGHULBSOLIFBBWFLRVQQPRNGKSSOTWTQSJQSSEKZZWATJKLUDIAWINFBNYPVTTMZFPKWGDKZXTJCDIGKUHUAUEKCAR";
    private static final Vector INPUT_VECTOR = buildInputVector(TEXT);

    private static Vector buildInputVector(final String text) {
        final Vector ret = new Vector();
        for(int i = 0; i < text.length(); i++) {
            final Vector vector = CharacterVectorUtils.CHARACTER_VECTOR_MAP.get(text.charAt(i));
            ret.concatenate(vector);
        }
        return ret;
    }

    private static final char SPECIAL_CHARACTER = '.';
    private static final int MAXIMUM_FITNESS = 20;

    public MlpIndividual(final Mlp mlp, final double mutationRate, final String goal) {
        super(mlp, mutationRate, goal);
    }

    public void calculateFitness() {
        float fitness = 0;
        final Mlp mlp = (Mlp)getReproducible();
        final Vector outputVector = mlp.calculateOutputs(INPUT_VECTOR);
        final String outputString = buildOutputString(outputVector);
        for(int i = 0; i < super.getGoal().length(); i++) {
            if(super.getGoal().charAt(i) != SPECIAL_CHARACTER) {
                if(super.getGoal().charAt(i) == outputString.charAt(i)) {
                    fitness++;
                }
            }
        }
        if(fitness == MAXIMUM_FITNESS) {
            System.out.println(outputString);
        }
        super.setFitness(fitness);
    }

    private String buildOutputString(final Vector vector) {
        final StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 97; i++) {
            final int currentIndex = i * 5;
            final Vector subVector = new Vector(
                    normalize(vector.get(currentIndex)),
                    normalize(vector.get(currentIndex + 1)),
                    normalize(vector.get(currentIndex + 2)),
                    normalize(vector.get(currentIndex + 3)),
                    normalize(vector.get(currentIndex + 4))
            );
            Character character = CharacterVectorUtils.VECTOR_CHARACTER_MAP.get(subVector);
            if(character == null) {
                character = SPECIAL_CHARACTER;
            }
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    private Double normalize(double value) {
        final Double ret;
        if(value < 0.5) {
            ret = 0d;
        } else {
            ret = 1d;
        }
        return ret;
    }

    @Override
    public Individual reproduce(Individual individual) {
        final Reproducible reproducible = this.getReproducible().reproduce(individual.getReproducible(), getMutationRate());
        return new MlpIndividual((Mlp) reproducible, this.getMutationRate(), this.getGoal());
    }

}
