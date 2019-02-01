package com.yzumis.ai.applications.geneticmlpcar.object;

import com.yzumis.ai.applications.common.screen.Paintable;
import com.yzumis.ai.applications.common.screen.Screen;
import com.yzumis.ai.applications.geneticmlpcar.Application;
import com.yzumis.ai.applications.geneticmlpcar.geometry.Point2d;
import com.yzumis.ai.applications.geneticmlpcar.util.ImageUtil;
import com.yzumis.ai.commonneuron.BaseNeuronFactory;
import com.yzumis.ai.commonneuron.Vector;
import com.yzumis.ai.mlp.Mlp;
import com.yzumis.ai.neuron.NeuronFactory;

import java.awt.*;
import java.util.ArrayList;

public class Car extends MovableObject implements Paintable {


    private Image CAR_IMAGE = ImageUtil.loadImage("Car.png");
    private Image CAR_CRASHED_IMAGE = ImageUtil.loadImage("CarCrashed.png");

    private static final int NUMBER_OF_INPUTS = 3;
    private static final java.util.List<Integer> NEURONS_PER_LAYER = new ArrayList<>();

    static {
        NEURONS_PER_LAYER.add(3);
        NEURONS_PER_LAYER.add(3);
        NEURONS_PER_LAYER.add(1);
    }

    public static final double CAR_X_SIZE = Application.X_SIZE / 36 * 3 / 4; // 15
    private static final double CAR_Y_SIZE = Application.Y_SIZE / 24 / 2; // 10

    public static final double CAR_X_ORIGIN = CAR_X_SIZE;
    public static final double CAR_Y_ORIGIN = Application.Y_SIZE / 2 - CAR_Y_SIZE / 2;

    public static final double CAR_X_VELOCITY = 1d;

    private final Track track;
    private final Sensor sensor1;
    private final Sensor sensor2;
    private final Sensor sensor3;

    private boolean alive;
    private Mlp mlp;

    public Car(final Track track) {
        super(CAR_X_ORIGIN, CAR_Y_ORIGIN, CAR_X_SIZE, CAR_Y_SIZE, CAR_X_VELOCITY);
        this.track = track;
        this.sensor1 = new Sensor(this.track);
        this.sensor2 = new Sensor(this.track);
        this.sensor3 = new Sensor(this.track);
        this.setupSensor1Position();
        this.setupSensor2Position();
        this.setupSensor3Position();
        this.alive = true;
        final BaseNeuronFactory baseNeuronFactory = new NeuronFactory();
        this.mlp = new Mlp(NUMBER_OF_INPUTS, NEURONS_PER_LAYER, baseNeuronFactory);
    }

    private void setupSensor1Position() {
        final Point2d sensorOrigin = new Point2d(this.x + this.xSize, this.y + this.ySize / 2);
        final Point2d sensor1Destination = new Point2d(sensorOrigin.getX() + Sensor.SENSOR_LENGHT * Math.cos(Math.PI  / 4), sensorOrigin.getY() + Sensor.SENSOR_LENGHT * Math.sin(Math.PI  / 4));
        this.sensor1.setupPosition(sensorOrigin, sensor1Destination);
    }

    private void setupSensor2Position() {
        final Point2d sensorOrigin = new Point2d(this.x + this.xSize, this.y + this.ySize / 2);
        final Point2d sensor2Destination = new Point2d(sensorOrigin.getX() + Sensor.SENSOR_LENGHT, sensorOrigin.getY());
        this.sensor2.setupPosition(sensorOrigin, sensor2Destination);
    }

    private void setupSensor3Position() {
        final Point2d sensorOrigin = new Point2d(this.x + this.xSize, this.y + this.ySize / 2);
        final Point2d sensor3Destination = new Point2d(sensorOrigin.getX() + Sensor.SENSOR_LENGHT * Math.cos(7 * Math.PI  / 4), sensorOrigin.getY() + Sensor.SENSOR_LENGHT * Math.sin(7 * Math.PI  / 4));
        this.sensor3.setupPosition(sensorOrigin, sensor3Destination);
    }

    public Car(final Track track, final Mlp mlp) {
        super(CAR_X_ORIGIN, CAR_Y_ORIGIN, CAR_X_SIZE, CAR_Y_SIZE, CAR_X_VELOCITY);
        this.track = track;
        this.sensor1 = new Sensor(this.track);
        this.sensor2 = new Sensor(this.track);
        this.sensor3 = new Sensor(this.track);
        this.setupSensor1Position();
        this.setupSensor2Position();
        this.setupSensor3Position();
        this.alive = true;
        this.mlp = mlp;
    }

    @Override
    public void paint(final Graphics graphics) {
        if(this.alive) {
            graphics.drawImage(CAR_IMAGE, (int)x, (int)y, (int)xSize, (int)ySize, null);
            sensor1.paint(graphics);
            sensor2.paint(graphics);
            sensor3.paint(graphics);
        } else {
            graphics.drawImage(CAR_CRASHED_IMAGE, (int)x, (int)y, (int)xSize, (int)ySize, null);
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public static Car reproduce(final Car car1, final Car car2, final double mutationRate) {
        final Mlp mlp = (Mlp)car1.mlp.reproduce(car2.mlp, mutationRate);
        return new Car(car1.track, mlp);
    }

    public void execute(final long currentTimeMillis) {
        if(this.alive) {
            final Vector inputs = this.readInputValues();
            final Vector output = this.mlp.calculateOutputs(inputs);
            super.yVelocity = output.toList().get(0) - 0.5f; // Output is between 0 and 1. So for negative output is needed to substract 0.5
            this.updatePosition(currentTimeMillis);
            if (this.crashed()) {
                this.alive = false;
            }
        }
    }

    @Override
    public void updatePosition(final long currentTimeMillis) {
        super.updatePosition(currentTimeMillis);
        this.setupSensor1Position();
        this.setupSensor2Position();
        this.setupSensor3Position();
    }

    private Vector readInputValues() {
        final Vector ret = new Vector(sensor1.calculateLecture(), sensor2.calculateLecture(), sensor3.calculateLecture());
        return ret;
    }

    private boolean crashed() {
        boolean ret = false;
        for(Wall wall: this.track.getWalls()) {
            if(this.crashedIntoWall(wall)) {
                ret = true;
            }
        }
        return ret;
    }

    private boolean crashedIntoWall(final Wall wall) {
        final boolean xCrash = this.x + this.xSize >= wall.getX() && this.x < wall.getX() + wall.getxSize();
        final boolean yCrash = this.y + this.ySize >= wall.getY() && this.y < wall.getY() + wall.getySize();
        return xCrash && yCrash;
    }

    public double calculateFitness() {
        return this.x / Application.X_SIZE;
    }

}
