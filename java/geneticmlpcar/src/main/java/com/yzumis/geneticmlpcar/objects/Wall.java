package com.yzumis.geneticmlpcar.objects;

import com.yzumis.geneticmlpcar.utilities.ImageUtil;

import java.awt.*;

/**
 * Created by Yzumi on 24/12/2017.
 */
public class Wall extends StaticObject implements Paintable {

    private static final Image WALL_IMAGE = ImageUtil.loadImage("Wall.png");
    public static final float WALL_X_SIZE = Screen.X_SIZE / 36; // 20
    public static final float WALL_Y_SIZE = Screen.Y_SIZE / 24; // 20

    public Wall(final float x, final float y) {
        super(x, y, WALL_X_SIZE, WALL_Y_SIZE);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(WALL_IMAGE, (int)x, (int)y, (int)xSize, (int)ySize, null);
    }

}
