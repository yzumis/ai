package driving;

import driving.brain.Mlp;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 27/12/2017.
 */
public class Car extends MovableObject implements Paintable {

    private static final Color WALL_COLOR = Color.RED;
    private static final int NUMBER_OF_INPUTS = 3;
    private static final List<Integer> NEURONS_PER_LAYER = new ArrayList<>();
    static {
        NEURONS_PER_LAYER.add(3);
        NEURONS_PER_LAYER.add(4);
        NEURONS_PER_LAYER.add(1);
    }

    private static final float CAR_X_SIZE = Screen.X_SIZE / 36  * 3 / 4;
    private static final float CAR_Y_SIZE = Screen.Y_SIZE / 24 / 2;

    public static final float CAR_X_ORIGIN = CAR_X_SIZE;
    public static final float CAR_Y_ORIGIN = Screen.Y_SIZE / 2 - CAR_Y_SIZE / 2;

    public static final float CAR_X_VELOCITY = 1f;

    private final Track track;
    private boolean alive;
    private Mlp mlp;

    public Car(final Track track) {
        super(CAR_X_ORIGIN, CAR_Y_ORIGIN, CAR_X_SIZE, CAR_Y_SIZE, CAR_X_VELOCITY);
        this.track = track;
        this.alive = true;
        this.mlp = new Mlp(NUMBER_OF_INPUTS, NEURONS_PER_LAYER);
    }

    public Car(final Track track, final Mlp mlp) {
        super(CAR_X_ORIGIN, CAR_Y_ORIGIN, CAR_X_SIZE, CAR_Y_SIZE, CAR_X_VELOCITY);
        this.track = track;
        this.alive = true;
        this.mlp = mlp;
    }

    @Override
    public void paint(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(WALL_COLOR);
        graphics.fillRect((int)x, (int)y, (int)xSize, (int)ySize);
        graphics.setColor(color);
    }

    public boolean isAlive() {
        return alive;
    }

    public static Car reproduce(final Car car1, final Car car2, final float mutationRate) {
        final Mlp mlp = Mlp.reproduce(car1.mlp, car2.mlp, mutationRate);
        return new Car(car1.track, mlp);
    }

    public void execute(final long currentTimeMillis) {
        if(this.alive) {
            final List<Float> inputs = this.readInputValues();
            final List<Float> output = this.mlp.calculateOutputs(inputs);
            super.yVelocity = output.get(0);
            super.updatePosition(currentTimeMillis);
            if (this.crashed()) {
                this.alive = false;
            }
        }
    }

    private List<Float> readInputValues() {
        final float sensorLenght = 3 * CAR_X_SIZE;
        final Point sensorOrigin = new Point(this.x + this.xSize, this.y + this.ySize / 2);
        final Point sensor1Destination = new Point(sensorOrigin.getX() + sensorLenght * (float)Math.cos(Math.PI  / 4), sensorOrigin.getY() + sensorLenght * (float)Math.sin(Math.PI  / 4));
        final Point sensor2Destination = new Point(sensorOrigin.getX() + sensorLenght, sensorOrigin.getY());
        final Point sensor3Destination = new Point(sensorOrigin.getX() + sensorLenght * (float)Math.cos(7 * Math.PI  / 4), sensorOrigin.getY() + sensorLenght * (float)Math.sin(7 * Math.PI  / 4));
        final Segment sensor1Segment = new Segment(sensorOrigin, sensor1Destination);
        final Segment sensor2Segment = new Segment(sensorOrigin, sensor2Destination);
        final Segment sensor3Segment = new Segment(sensorOrigin, sensor3Destination);
        final List<Segment> trackSegments = this.track.getSegments();
        final List<Point> sensor1ImpactPoints = sensor1Segment.findImpactPoints(trackSegments);
        final List<Point> sensor2ImpactPoints = sensor2Segment.findImpactPoints(trackSegments);
        final List<Point> sensor3ImpactPoints = sensor3Segment.findImpactPoints(trackSegments);
        final List<Float> ret = new ArrayList<>();
        ret.add(sensorOrigin.findShortestDistanceToPoints(sensor1ImpactPoints));
        ret.add(sensorOrigin.findShortestDistanceToPoints(sensor2ImpactPoints));
        ret.add(sensorOrigin.findShortestDistanceToPoints(sensor3ImpactPoints));
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
        final boolean xCrash = this.x >= wall.getX() && this.x < wall.getX() + wall.getxSize();
        final boolean yCrash = this.y >= wall.getY() && this.y < wall.getY() + wall.getySize();
        return xCrash && yCrash;
    }

    public float calculateFitness() {
        return this.x / Screen.X_SIZE;
    }

}
