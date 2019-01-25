package com.yzumis.ai.applications.geneticmlpcar.object;

import com.yzumis.ai.applications.common.screen.Paintable;
import com.yzumis.ai.applications.common.screen.Screen;
import com.yzumis.ai.applications.geneticmlpcar.util.ImageUtil;

import java.awt.*;

public class Background implements Paintable {

    private static final Image BACKGROUND_IMAGE = ImageUtil.loadImage("Background.png");

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(BACKGROUND_IMAGE, 0, 0, (int) Screen.X_SIZE, (int)Screen.Y_SIZE, null);
    }
}

