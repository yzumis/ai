package com.yzumis.geneticmlpcar.objects;

import com.yzumis.geneticmlpcar.utilities.ImageUtil;

import java.awt.*;

/**
 * Created by Yzumi on 10/01/2018.
 */
public class Background implements Paintable {

    private static final Image BACKGROUND_IMAGE = ImageUtil.loadImage("Background.png");

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(BACKGROUND_IMAGE, 0, 0, (int) Screen.X_SIZE, (int)Screen.Y_SIZE, null);
    }
}
