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

}
