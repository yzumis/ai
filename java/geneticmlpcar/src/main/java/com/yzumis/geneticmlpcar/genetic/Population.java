package com.yzumis.geneticmlpcar.genetic;

import com.yzumis.geneticmlpcar.objects.Screen;
import com.yzumis.geneticmlpcar.objects.Track;
import com.yzumis.geneticmlpcar.objects.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 27/12/2017.
 */
public class Population {

    private final Screen screen;
    private final int populationSize;
    private final Track track;
    private List<Car> cars;

    public Population(final Screen screen, final int populationSize, final Track track) {
        this.screen = screen;
        this.populationSize = populationSize;
        this.track = track;
        this.cars = new ArrayList<>();
        for(int i = 0; i < populationSize; i++) {
            this.cars.add(new Car(track));
        }
    }

    public void calculateFitness(final int generation) throws InterruptedException {
        this.screen.setupScreen(track, cars, generation);
        while(existCarAlive()) {
            Thread.sleep((long)(1000f / 24f));
            executeCars(System.currentTimeMillis());
            this.screen.repaint();
        }
    }

    private void executeCars(final long currentTimeMillis) {
        for(final Car car: cars) {
            car.execute(currentTimeMillis);
        }
    }

    private boolean existCarAlive() {
        boolean ret = false;
        for(final Car car: cars) {
            if(car.isAlive()) {
                ret = true;
            }
        }
        return ret;
    }

    public void reproduction(double mutationRate) {
        final List<Car> childCars = new ArrayList<>();
        final double totalFitness = this.calculateTotalFitness();
        for(int i = 0; i < this.populationSize; i++) {
            final Car car1 = this.pickBestCar();
            final Car car2 = this.pickRandomCar(totalFitness);
            childCars.add(Car.reproduce(car1, car2, mutationRate));
        }
        this.cars = childCars;
    }

    private double calculateTotalFitness() {
        double ret = 0;
        for(final Car car: this.cars) {
            ret += car.calculateFitness();
        }
        return ret;
    }

    private Car pickBestCar() {
        Car ret = null;
        for(final Car car: this.cars) {
            if(ret == null || ret.calculateFitness() < car.calculateFitness()) {
                ret = car;
            }
        }
        return ret;
    }

    private Car pickRandomCar(final double totalFitness) {
        final double randomDouble = Math.random();
        double currentFitness = 0;
        for(final Car car: this.cars) {
            currentFitness += car.calculateFitness();
            if(currentFitness / totalFitness > randomDouble) {
                return car;
            }
        }
        // Due precision errors the sumation of all cars is not always 1
        return this.cars.get(this.populationSize - 1);
    }

}