package com.yzumis.ai.applications.geneticmlpcar.geometry;

import com.yzumis.ai.applications.geneticmlpcar.object.Paintable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Segment implements Paintable {

    private final static Color SEGMENT_COLOR = Color.YELLOW;
    private final Point2d originPoint;
    private final Point2d destinationPoint;

    public Segment(final Point2d originPoint, final Point2d destinationPoint) {
        this.originPoint = originPoint;
        this.destinationPoint = new Point2d(destinationPoint.getX(), destinationPoint.getY());
    }

    public java.util.List<Point2d> findImpactPoints(final java.util.List<Segment> segments) {
        final List<Point2d> ret = new ArrayList<>();
        for(final Segment segment: segments) {
            final Point2d impactPoint = this.findImpactPoint(segment);
            if(impactPoint != null) {
                ret.add(impactPoint);
            }
        }
        return ret;
    }

    private Point2d findImpactPoint(final Segment segment) {
        final Segment r1 = this;
        final Segment r2 = segment;
        final Point2d ret;
        if(r1.pX() && r2.pX()) {
            if(r1.getOriginPoint().getY() == r2.getOriginPoint().getY()) {
                if((r1.getOriginPoint().calculateDistanceToPoint(r2.getOriginPoint())) < (r1.getOriginPoint().calculateDistanceToPoint(r2.getDestinationPoint()))) {
                    ret = r2.getOriginPoint();
                } else {
                    ret = r2.getDestinationPoint();
                }
            } else { // r1 parallel r2
                ret = null;
            }
        } else if(r1.pX() && r2.pY()) {
            ret = new Point2d(r2.getOriginPoint().getX(), r1.getOriginPoint().getY());
        } else if(r1.pX() && !r2.pX() && !r2.pY()) {
            ret = new Point2d(r2.findX(r1.yk()), r1.getOriginPoint().getY());
        } else if(r1.pY() && r2.pX()) {
            ret = new Point2d(r1.getOriginPoint().getX(), r2.getOriginPoint().getY());
        } else if(r1.pY() && r2.pY()) {
            if(r1.getOriginPoint().getX() == r2.getOriginPoint().getX()) {
                if((r1.getOriginPoint().calculateDistanceToPoint(r2.getOriginPoint())) < (r1.getOriginPoint().calculateDistanceToPoint(r2.getDestinationPoint()))) {
                    ret = r2.getOriginPoint();
                } else {
                    ret = r2.getDestinationPoint();
                }
            } else { // r1 parallel r2
                ret = null;
            }
        } else if(r1.pY() && !r2.pX() && !r2.pY()) {
            ret = new Point2d(r1.getOriginPoint().getX(), r2.findY(r1.xk()));
        } else if(!r1.pX() && !r1.pY() && r2.pX()) {
            ret = new Point2d(r1.findX(r2.yk()), r2.getOriginPoint().getY());
        } else if(!r1.pX() && !r1.pY() && r2.pY()) {
            ret = new Point2d(r2.getOriginPoint().getX(), r1.findY(r2.xk()));
        } else { // (!r1.pX() && !r1.pY() && !r2.pX() && !r2.pY())
            ret = r1.findImpactPointNonParallelAxis(r2);
        }
        if(ret != null && r1.inSegment(ret) && r2.inSegment(ret)) {
            return ret;
        } else {
            return null;
        }
    }

    private double xk() {
        return this.originPoint.getX();
    }

    private double yk() {
        return this.originPoint.getY();
    }

    private double findX(double yk) {
        final double x0 = this.originPoint.getX();
        final double y0 = this.originPoint.getY();
        final double x1 = this.destinationPoint.getX();
        final double y1 = this.destinationPoint.getY();
        return (yk - y0) * (x1 - x0) / (y1- y0) + x0;
    }

    private double findY(double xk){
        final double x0 = this.originPoint.getX();
        final double y0 = this.originPoint.getY();
        final double x1 = this.destinationPoint.getX();
        final double y1 = this.destinationPoint.getY();
        return (xk - x0) * (y1 - y0) / (x1- x0) + y0;
    }

    private Point2d findImpactPointNonParallelAxis(final Segment segment) {
        final double x1 = this.originPoint.getX();
        final double y1 = this.originPoint.getY();
        final double x2 = this.destinationPoint.getX();
        final double y2 = this.destinationPoint.getY();
        final double x1p = segment.originPoint.getX();
        final double y1p = segment.originPoint.getY();
        final double x2p = segment.destinationPoint.getX();
        final double y2p = segment.destinationPoint.getY();
        final double yNumerator = y1*x2*(y2p-y1p)-x1*y2*(y2p-y1p)-y1p*x2p*(y2-y1)+x1p*y2p*(y2-y1);
        final double xNumerator = x1*y2*(x2p-x1p)-y1*x2*(x2p-x1p)-x1p*y2p*(x2-x1)+y1p*x2p*(x2-x1);
        final double yDenominator = (x2-x1)*(y2p-y1p)-(x2p-x1p)*(y2-y1);
        final double xDenominator = (y2-y1)*(x2p-x1p)-(y2p-y1p)*(x2-x1);
        final Point2d ret;
        if((xDenominator == 0) || (yDenominator == 0)) {
            ret = null;
        } else {
            final double x = xNumerator / xDenominator;
            final double y = yNumerator / yDenominator;
            final Point2d point = new Point2d(x, y);
            if(this.inSegment(point)) {
                ret = point;
            } else {
                ret = null;
            }
        }
        return ret;
    }

    private boolean pX() {
        return this.originPoint.getY() == this.destinationPoint.getY();
    }

    private boolean pY() {
        return this.originPoint.getX() == this.destinationPoint.getX();
    }

    private boolean inSegment(final Point2d point) {
        final boolean xInSegment = this.between(point.getX(), this.originPoint.getX(), this.destinationPoint.getX());
        final boolean yInSegment = this.between(point.getY(), this.originPoint.getY(), this.destinationPoint.getY());
        return xInSegment && yInSegment;
    }

    private boolean between(final double number, double numberO, double numberD) {
        final boolean ret;
        if(numberO < numberD) {
            ret = (number >= numberO && number <= numberD);
        } else {
            ret = (number >= numberD && number <= numberO);
        }
        return ret;
    }

    @Override
    public void paint(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(SEGMENT_COLOR);
        graphics.drawLine((int)this.originPoint.getX(), (int)this.originPoint.getY(), (int)this.destinationPoint.getX(), (int)this.destinationPoint.getY());
        graphics.setColor(color);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Segment from " );
        stringBuilder.append(this.originPoint.toString());
        stringBuilder.append(" to ");
        stringBuilder.append(this.destinationPoint.toString());
        return stringBuilder.toString();
    }

    public Point2d getOriginPoint() {
        return originPoint;
    }

    public Point2d getDestinationPoint() {
        return destinationPoint;
    }

}
