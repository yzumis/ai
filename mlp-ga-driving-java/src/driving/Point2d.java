package driving;

import java.awt.*;
import java.util.List;

/**
 * Created by Yzumi on 28/12/2017.
 */
public class Point2d implements Paintable {

    private static final Color POINT2D_COLOR = Color.GREEN;

    private final float x;
    private final float y;

    public Point2d(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Float calculateDistanceToPoint(final Point2d point) {
        return (float) Math.sqrt( Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
    }

    @Override
    public void paint(final Graphics graphics) {
        final Color color = graphics.getColor();
        graphics.setColor(POINT2D_COLOR);
        graphics.drawLine((int)this.x - 3, (int)this.y - 3, (int)this.x + 3, (int)this. y + 3);
        graphics.drawLine((int)this.x - 3, (int)this.y + 3, (int)this.x + 3, (int)this. y - 3);
        graphics.setColor(color);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(this.x);
        stringBuilder.append(", ");
        stringBuilder.append(this.y);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

}
