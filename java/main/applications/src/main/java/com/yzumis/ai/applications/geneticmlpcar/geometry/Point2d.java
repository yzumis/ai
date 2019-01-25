package com.yzumis.ai.applications.geneticmlpcar.geometry;

import com.yzumis.ai.applications.common.screen.Paintable;

import java.awt.*;

public class Point2d implements Paintable {

    private static final Color POINT2D_COLOR = Color.GREEN;

    private final double x;
    private final double y;

    public Point2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Double calculateDistanceToPoint(final Point2d point) {
        return Math.sqrt( Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
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
