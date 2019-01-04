package com.yzumis.ia.genetic;

public abstract class Individual<T extends Reproducible, G> {

    private final G goal;
    private final double mutationRate;
    private double fitness;
    private Reproducible reproducible;

    public Individual(final Reproducible reproducible, final double mutationRate, final G goal) {
        this.reproducible = reproducible;
        this.mutationRate = mutationRate;
        this.goal = goal;
        this.fitness = 0f;
    }

    public abstract void calculateFitness();

    protected void setFitness(final double fitness) {
        this.fitness = fitness;
    }

    public abstract Individual reproduce(final Individual individual);

    public double getFitness() {
        return fitness;
    }

    public G getGoal() {
        return goal;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public Reproducible getReproducible() {
        return reproducible;
    }

}
