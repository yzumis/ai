package com.yzumis.geneticmlpimageclassifier;

public enum ImageType {

    AIRPLANE(0),
    AUTOMOBILE(1),
    BIRD(2),
    CAT(3),
    DEER(4),
    DOG(5),
    FROG(6),
    HORSE(7),
    SHIP(8),
    TRUCK(9);

    private final int value;

    ImageType(final int value) {
        this.value = value;
    }

    public static ImageType byteToImageType(byte value) {
        for (final ImageType imageType: ImageType.values()) {
            if (imageType.getValue() == (int)value) {
                return imageType;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

}
