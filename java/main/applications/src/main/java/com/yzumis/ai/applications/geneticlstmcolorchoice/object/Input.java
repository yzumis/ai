package com.yzumis.ai.applications.geneticlstmcolorchoice.object;

import com.yzumis.ai.commonneuron.Vector;

import java.awt.*;

public enum Input {
    VOID,
    EMPTY,
    RED,
    GREEN,
    BLUE;

    public Vector toVector() {
        final Vector ret;
        switch (this) {
            case VOID:
                ret = new Vector(0d, 0d, 0d, 0d, 1d);
                break;
            case EMPTY:
                ret = new Vector(0d, 0d, 0d, 1d, 0d);
                break;
            case RED:
                ret = new Vector(0d, 0d, 1d, 0d, 0d);
                break;
            case GREEN:
                ret = new Vector(0d, 1d, 0d, 0d, 0d);
                break;
            case BLUE:
                ret = new Vector(1d, 0d, 0d, 0d, 0d);
                break;
            default:
                throw new IllegalStateException();
        }
        return ret;
    }

    public Color toColor() {
        final Color ret;
        switch (this) {
            case EMPTY:
                ret = Color.ORANGE;
                break;
            case RED:
                ret = Color.RED;
                break;
            case GREEN:
                ret = Color.GREEN;
                break;
            case BLUE:
                ret = Color.BLUE;
                break;
            default:
                throw new IllegalStateException();
        }
        return ret;

    }

    public static Input fromVector(final Vector vector) {
        final Input ret;
        if (vector.get(0) > vector.get(1) && vector.get(0) > vector.get(2) && vector.get(0) > vector.get(3) && vector.get(0) > vector.get(4)) {
            ret = BLUE;
        } else if(vector.get(1) > vector.get(0) && vector.get(1) > vector.get(2) && vector.get(1) > vector.get(3) && vector.get(1) > vector.get(4)) {
            ret = GREEN;
        } else if(vector.get(2) > vector.get(0) && vector.get(2) > vector.get(1) && vector.get(2) > vector.get(3) && vector.get(2) > vector.get(4)) {
            ret = RED;
        } else if(vector.get(3) > vector.get(0) && vector.get(3) > vector.get(1) && vector.get(3) > vector.get(2) && vector.get(3) > vector.get(4)) {
            ret = EMPTY;
        } else {
            ret = VOID;
        }
        return ret;
    }

}
