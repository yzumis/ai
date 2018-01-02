package driving.genetic;

import driving.Car;
import driving.Screen;
import driving.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 27/12/2017.
 */
public class Population {

    private final int populationSize;
    private final Track track;
    private List<Car> cars;

    public Population(final int populationSize, final Track track) {
        this.populationSize = populationSize;
        this.track = track;
        this.cars = new ArrayList<>();
        for(int i = 0; i < populationSize; i++) {
            this.cars.add(new Car(track));
        }
    }

    public void calculateFitness(final int generation) throws InterruptedException {
        final Screen screen = new Screen(track, cars, generation);
        while(existCarAlive()) {
            Thread.sleep((long)(1000f / 24f));
            executeCars(System.currentTimeMillis());
            screen.repaint();
        }
        screen.dispose();
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

    public void reproduction(float mutationRate) {
        final List<Car> childCars = new ArrayList<>();
        final float totalFitness = this.calculateTotalFitness();
        for(int i = 0; i < this.populationSize; i++) {
            final Car car1 = this.pickRandomCar(totalFitness);
            final Car car2 = this.pickRandomCar(totalFitness);
            childCars.add(Car.reproduce(car1, car2, mutationRate));
        }
        this.cars = childCars;
    }

    private float calculateTotalFitness() {
        float ret = 0;
        for(final Car car: this.cars) {
            ret += car.calculateFitness();
        }
        return ret;
    }

    private Car pickRandomCar(final float totalFitness) {
        final float randomFloat = (float)Math.random();
        float currentFitness = 0;
        for(final Car car: this.cars) {
            currentFitness += car.calculateFitness();
            if(currentFitness / totalFitness > randomFloat) {
                return car;
            }
        }
        // Due precision errors the sumation of all cars is not always 1
        return this.cars.get(this.populationSize - 1);
    }


}