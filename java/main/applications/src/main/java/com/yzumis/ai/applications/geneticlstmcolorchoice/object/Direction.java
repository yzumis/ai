package com.yzumis.ai.applications.geneticlstmcolorchoice.object;

import com.yzumis.ai.commonneuron.Vector;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Direction fromVector(final Vector vector) {
        final double value0 = vector.toList().get(0);
        final double value1 = vector.toList().get(1);
        final double value2 = vector.toList().get(2);
        final double value3 = vector.toList().get(3);
        final Direction ret;
        if (value0 > value1 && value0 > value2 && value0 > value3) {
            ret = UP;
        } else if (value1 > value0 && value1 > value2 && value1 > value3) {
            ret = DOWN;
        } else if (value2 > value0 && value2 > value1 && value2 > value3) {
            ret = LEFT;
        } else {
            ret = RIGHT;
        }
        return ret;
    }
}
