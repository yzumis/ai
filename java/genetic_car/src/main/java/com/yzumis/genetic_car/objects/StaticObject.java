package com.yzumis.genetic_car.objects;

import com.yzumis.genetic_car.geometry.Point2d;
import com.yzumis.genetic_car.geometry.Segment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 24/12/2017.
 */
public abstract class StaticObject {

    protected final float x;
    protected final float y;
    protected final float xSize;
    protected final float ySize;

    public StaticObject(final float x, final float y, final float xSize, final float ySize) {
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getxSize() {
        return xSize;
    }

    public float getySize() {
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
