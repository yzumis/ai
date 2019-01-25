package com.yzumis.ai.applications.geneticlstmcolorchoice.genetic;

import com.yzumis.ai.applications.common.screen.Paintable;
import com.yzumis.ai.applications.common.screen.Screen;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.ColorCharacter;
import com.yzumis.ai.applications.geneticlstmcolorchoice.object.Scenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {

    private final Screen screen;
    private final int populationSize;
    private final Scenario scenario;
    private List<ColorCharacter> colorCharacters;

    public Population(final Screen screen, final int populationSize, final Scenario scenario) {
        this.screen = screen;
        this.populationSize = populationSize;
        this.scenario = scenario;
        this.colorCharacters = new ArrayList<>();
        for(int i = 0; i < populationSize; i++) {
            this.colorCharacters.add(new ColorCharacter(scenario));
        }
    }

    public void calculateFitness(final int generation) throws InterruptedException {
        final List<Paintable> paintables = calculatePaintables();
        this.screen.setupScreen(paintables, generation);
        while(existColorCharacterAlive()) {
            // Thread.sleep(100);
            executeColorCharacters();
            this.scenario.execute(this.colorCharacters);
            this.screen.repaint();
        }
    }

    private List<Paintable> calculatePaintables() {
        final List<Paintable> ret = new ArrayList<>();
        ret.add(this.scenario);
        for(final ColorCharacter colorCharacter: this.colorCharacters) {
            ret.add(colorCharacter);
        }
        return ret;
    }

    private void executeColorCharacters() {
        for(final ColorCharacter colorCharacter: this.colorCharacters) {
            colorCharacter.execute();
        }
    }

    private boolean existColorCharacterAlive() {
        boolean ret = false;
        for(final ColorCharacter colorCharacter: this.colorCharacters) {
            if(colorCharacter.isAlive()) {
                ret = true;
            }
        }
        return ret;
    }

    public void reproduction(final double mutationRate) {
        final List<ColorCharacter> childColorCharacters = new ArrayList<>();
        final double totalFitness = this.calculateTotalFitness();
        final ColorCharacter bestColorCharacter = this.pickBestColorCharacter();
        final ColorCharacter bestColorCharacterChild = new ColorCharacter(bestColorCharacter);
        childColorCharacters.add(bestColorCharacterChild);
        for(int i = 0; i < this.populationSize - 1; i++) {
            final ColorCharacter colorCharacter1 = this.pickRandomColorCharacter(totalFitness);
            final ColorCharacter colorCharacter2 = this.pickTotallyRandomColorCharacter(totalFitness);
            final ColorCharacter childColorCharacter = (ColorCharacter) bestColorCharacter.reproduce(colorCharacter1, mutationRate);
            childColorCharacters.add(childColorCharacter);
        }
        this.colorCharacters = childColorCharacters;
    }

    private double calculateTotalFitness() {
        double ret = 0;
        for(final ColorCharacter colorCharacter: this.colorCharacters) {
            ret += colorCharacter.getFitness();
        }
        return ret;
    }

    public ColorCharacter pickBestColorCharacter() {
        ColorCharacter ret = null;
        for(final ColorCharacter colorCharacter: this.colorCharacters) {
            if(ret == null || ret.getFitness() < colorCharacter.getFitness()) {
                ret = colorCharacter;
            }
        }
        return ret;
    }

    private ColorCharacter pickRandomColorCharacter(final double totalFitness) {
        final double randomDouble = Math.random();
        double currentFitness = 0;
        for(final ColorCharacter colorCharacter: this.colorCharacters) {
            currentFitness += colorCharacter.getFitness();
            if(currentFitness / totalFitness > randomDouble) {
                return colorCharacter;
            }
        }
        // Due precision errors the sumation of all ColorCharacters is not always 1
        return this.colorCharacters.get(this.populationSize - 1);
    }

    private ColorCharacter pickTotallyRandomColorCharacter(final double totalFitness) {
        final Random random = new Random();
        final int randomColorCharactersIndex = random.nextInt(this.colorCharacters.size());
        return this.colorCharacters.get(randomColorCharactersIndex);
    }


}
