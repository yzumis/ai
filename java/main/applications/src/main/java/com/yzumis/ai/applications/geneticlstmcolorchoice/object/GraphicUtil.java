package com.yzumis.ai.applications.geneticlstmcolorchoice.object;

import java.awt.*;

public class GraphicUtil {

    public static boolean ISOMETRIC_GRAPHICS = true;

    public static void paintIsometricCube(final Graphics graphics, final int x, final int y, final int xSize, final int ySize, final boolean draw, final boolean fill) {
        final Polygon polygon1 = new Polygon();
        polygon1.addPoint(x, y);
        polygon1.addPoint(x + xSize, y - ySize);
        polygon1.addPoint(x + 2 * xSize, y);
        polygon1.addPoint(x + xSize, y + ySize);
        final Polygon polygon2 = new Polygon();
        polygon2.addPoint(x, y);
        polygon2.addPoint(x + xSize, y + ySize);
        polygon2.addPoint(x + xSize, y + 2 * ySize);
        polygon2.addPoint(x, y + ySize);
        polygon2.addPoint(x, y);
        final Polygon polygon3 = new Polygon();
        polygon3.addPoint(x + xSize, y + ySize);
        polygon3.addPoint(x + 2 * xSize, y);
        polygon3.addPoint(x + 2 * xSize, y + ySize);
        polygon3.addPoint(x + xSize, y + 2 * ySize);
        polygon3.addPoint(x + xSize, y + ySize);
        if(fill) {
            graphics.fillPolygon(polygon1);
            graphics.fillPolygon(polygon2);
            graphics.fillPolygon(polygon3);
        }
        if(draw) {
            final Color color = graphics.getColor();
            graphics.setColor(Color.BLACK);
            graphics.drawPolygon(polygon1);
            graphics.drawPolygon(polygon2);
            graphics.drawPolygon(polygon3);
            graphics.setColor(color);
        }
    }

}
