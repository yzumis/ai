package com.yzumis.ai.applications.geneticmlpcar.object;

import com.yzumis.ai.applications.common.screen.Paintable;
import com.yzumis.ai.applications.geneticmlpcar.Application;
import com.yzumis.ai.applications.geneticmlpcar.util.ImageUtil;

import java.awt.*;

public class Wall extends StaticObject implements Paintable {

    private static final Image WALL_IMAGE = ImageUtil.loadImage("Wall.png");
    public static final double WALL_X_SIZE = Application.X_SIZE / 35;
    public static final double WALL_Y_SIZE = Application.Y_SIZE / 22;

    public Wall(final double x, final double y) {
        super(x, y, WALL_X_SIZE, WALL_Y_SIZE);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(WALL_IMAGE, (int)x, (int)y, (int) WALL_X_SIZE, (int) WALL_Y_SIZE, null);
    }

}
