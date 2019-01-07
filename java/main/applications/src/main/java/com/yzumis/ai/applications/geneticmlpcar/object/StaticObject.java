package com.yzumis.ai.applications.geneticmlpcar.object;

import com.yzumis.ai.applications.geneticmlpcar.geometry.Point2d;
import com.yzumis.ai.applications.geneticmlpcar.geometry.Segment;

import java.util.ArrayList;
import java.util.List;

public abstract class StaticObject {

    protected final double x;
    protected final double y;
    protected final double xSize;
    protected final double ySize;

    public StaticObject(final double x, final double y, final double xSize, final double ySize) {
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getxSize() {
        return xSize;
    }

    public double getySize() {
        return ySize;
    }

    public List<Segment> getSegments() {
        final List<Segment> ret = new ArrayList<>();
        ret.add(new Segment(new Point2d(this.x, this.y), new Point2d(this.x + this.xSize, this.y)));
        ret.add(new Segment(new Point2d(this.x, this.y), new Point2d(this.x, this.y + this.ySize)));
        ret.add(new Segment(new Point2d(this.x + this.xSize, this.y), new Point2d(this.x + this.xSize, this.y + this.ySize)));
        ret.add(new Segment(new Point2d(this.x, this.y + this.ySize), new Point2d(this.x + this.xSize, this.y + this.ySize)));
        return ret;
    }

}
