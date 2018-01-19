package com.yzumis.genetic_car.objects;

import com.yzumis.genetic_car.geometry.Point2d;
import com.yzumis.genetic_car.geometry.Segment;

import java.awt.*;
import java.util.List;

/**
 * Created by Yzumi on 02/01/2018.
 */
public class Sensor implements Paintable {

    public static final float SENSOR_LENGHT = 3 * Car.CAR_X_SIZE;
    private static final Color SENSOR_COLOR = Color.YELLOW;

    private Segment segment;
    private Track track;

    public Sensor(final Track track) {
        this.track = track;
    }

    public void setupPosition(final Point2d origin, final Point2d destination) {
        this.segment = new Segment(origin, destination);
    }

    public float calculateLecture() {
        final List<Segment> trackSegments = this.track.getSegments();
        final List<Point2d> impactPoints = this.segment.findImpactPoints(trackSegments);
        return this.findShortestDistanceToPoints(impactPoints);
    }

    private float findShortestDistanceToPoints(final List<Point2d> points) {
        float ret = SENSOR_LENGHT;
        for(final Point2d point: points) {
            final float distanceToPoint = this.segment.getOriginPoint().calculateDistanceToPoint(point);
            if(distanceToPoint < ret) {
                ret = distanceToPoint;
            }
        }
        return ret;
    }

    private Point2d calculateImpactPoint() {
        final List<Segment> trackSegments = this.track.getSegments();
        final List<Point2d> impactPoints = this.segment.findImpactPoints(trackSegments);
        return this.findShortestImpactPoint(impactPoints);
    }

    private Point2d findShortestImpactPoint(final List<Point2d> points) {
        Point2d ret = null;
        float shortestDistanceToPoint = SENSOR_LENGHT;
        for(final Point2d point: points) {
            final float distanceToPoint = this.segment.getOriginPoint().calculateDistanceToPoint(point);
            if(distanceToPoint < shortestDistanceToPoint) {
                shortestDistanceToPoint = distanceToPoint;
                ret = point;
            }
        }
        return ret;
    }

    @Override
    public void paint(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(SENSOR_COLOR);
        graphics.drawLine((int) this.segment.getOriginPoint().getX(), (int) this.segment.getOriginPoint().getY(), (int)this.segment.getDestinationPoint().getX(), (int)this.segment.getDestinationPoint().getY());
        final Point2d point2d = this.calculateImpactPoint();
        if(point2d != null) {
            point2d.paint(graphics);
        }
        graphics.setColor(color);
    }

}
