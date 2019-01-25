package com.yzumis.ai.applications.geneticmlpcar.object;

import com.yzumis.ai.applications.common.screen.Paintable;
import com.yzumis.ai.applications.geneticmlpcar.geometry.Point2d;
import com.yzumis.ai.applications.geneticmlpcar.geometry.Segment;

import java.awt.*;
import java.util.List;

public class Sensor implements Paintable {

    public static final double SENSOR_LENGHT = 3 * Car.CAR_X_SIZE;
    private static final Color SENSOR_COLOR = Color.YELLOW;

    private Segment segment;
    private Track track;

    public Sensor(final Track track) {
        this.track = track;
    }

    public void setupPosition(final Point2d origin, final Point2d destination) {
        this.segment = new Segment(origin, destination);
    }

    public double calculateLecture() {
        final List<Segment> trackSegments = this.track.getSegments();
        final List<Point2d> impactPoints = this.segment.findImpactPoints(trackSegments);
        return this.findShortestDistanceToPoints(impactPoints);
    }

    private double findShortestDistanceToPoints(final List<Point2d> points) {
        double ret = SENSOR_LENGHT;
        for(final Point2d point: points) {
            final double distanceToPoint = this.segment.getOriginPoint().calculateDistanceToPoint(point);
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
        double shortestDistanceToPoint = SENSOR_LENGHT;
        for(final Point2d point: points) {
            final double distanceToPoint = this.segment.getOriginPoint().calculateDistanceToPoint(point);
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
