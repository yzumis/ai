package com.yzumis.geneticmlpimageclassifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApplicationCifarReader {

    public static void main(final String[] args) throws IOException {
        final CifarReader cifarReader = new CifarReader(100);
        Image image;
        final Map<ImageType, Integer> imageTypeCountMap = new HashMap<>();
        while((image = cifarReader.readImage()) != null) {
            if(imageTypeCountMap.containsKey(image.getImageType())) {
                imageTypeCountMap.put(image.getImageType(), imageTypeCountMap.get(image.getImageType()) + 1);
            } else {
                imageTypeCountMap.put(image.getImageType(), 0);
            }
        }
        System.out.println(imageTypeCountMap);
    }

}
