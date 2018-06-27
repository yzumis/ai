package com.yzumis.geneticmlpcar.utilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Yzumi on 10/01/2018.
 */
public class ImageUtil {

    public static final Image loadImage(final String image) {
        BufferedImage ret = null;
        try {
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            final String fileURL = classLoader.getResource(image).getFile();
            final File file = new File(fileURL);
            final FileInputStream fileInputStream = new FileInputStream(file);
            ret = ImageIO.read(fileInputStream);
        } catch (IOException e) {
            System.out.print("ImageUtil.loadImage error");
            System.out.println(e);
        }
        return ret;
    }

}
