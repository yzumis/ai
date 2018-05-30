package com.yzumis.geneticmlpimageclassifier;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CifarReader {

    private static final String CIFAR_FOLDER = "cifar-10-batches-bin/";
    private static final int IMAGE_BYTES = 3073;
    private static final List<String> BATCHES = new ArrayList<>();

    static {
        BATCHES.add(CIFAR_FOLDER + "data_batch_1.bin");
        BATCHES.add(CIFAR_FOLDER + "data_batch_2.bin");
        BATCHES.add(CIFAR_FOLDER + "data_batch_3.bin");
        BATCHES.add(CIFAR_FOLDER + "data_batch_4.bin");
        BATCHES.add(CIFAR_FOLDER + "data_batch_5.bin");
    }

    private final int maximumImages;
    private int currentImage;
    private int currentBatch;
    private int currentBatchOffset;

    public CifarReader(final int maximumImages) {
        this.maximumImages = maximumImages;
        this.currentImage = 0;
        this.currentBatch = 0;
        this.currentBatchOffset = 0;
    }

    public CifarReader() {
        this(Integer.MAX_VALUE);
    }

    public Image readImage() throws IOException {
        final Image ret;
        if(this.currentImage > this.maximumImages) {
            ret = null;
        } else {
            if (currentBatch == BATCHES.size()) {
                ret = null;
            } else {
                try (final FileInputStream fileInputStream = new FileInputStream((this.getClass().getClassLoader().getResource(BATCHES.get(currentBatch)).getFile()))) {
                    fileInputStream.skip(currentBatchOffset);
                    final byte[] imageBytes = new byte[IMAGE_BYTES];
                    fileInputStream.read(imageBytes);
                    ret = new Image(imageBytes);
                    if (fileInputStream.available() == 0) {
                        this.currentBatch++;
                        this.currentBatchOffset = 0;
                    } else {
                        this.currentBatchOffset += IMAGE_BYTES;
                    }
                }
            }
        }
        this.currentImage++;
        return ret;
    }


}
