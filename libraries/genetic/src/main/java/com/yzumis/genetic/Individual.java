package com.yzumis.genetic;

public abstract class Individual<T extends Reproducible, G> {

    private final G goal;
    private final float mutationRate;
    private float fitness;
    private Reproducible reproducible;

    public Individual(final Reproducible reproducible, final float mutationRate, final G goal) {
        this.reproducible = reproducible;
        this.mutationRate = mutationRate;
        this.goal = goal;
        this.fitness = 0f;
    }

    public abstract void calculateFitness();

    protected void setFitness(final float fitness) {
        this.fitness = fitness;
    }

    public abstract Individual reproduce(final Individual individual);

    public float getFitness() {
        return fitness;
    }

    public G getGoal() {
        return goal;
    }

    public float getMutationRate() {
        return mutationRate;
    }

    public Reproducible getReproducible() {
        return reproducible;
    }

}
