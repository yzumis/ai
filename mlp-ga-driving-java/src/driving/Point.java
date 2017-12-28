package driving;

import java.util.List;

/**
 * Created by Yzumi on 28/12/2017.
 */
public class Point {

    private final float x;
    private final float y;

    public Point(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Float calculateDistanceToPoint(final Point point) {
        return (float) Math.sqrt( Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
    }

    public Float findShortestDistanceToPoints(final List<Point> points) {
        Float ret = null;
        for(final Point point: points) {
            final float distanceToPoint = this.calculateDistanceToPoint(point);
            if(ret == null || distanceToPoint < ret) {
                ret = distanceToPoint;
            }
        }
        return ret;
    }
}
