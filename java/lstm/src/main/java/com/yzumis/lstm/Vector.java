package com.yzumis.lstm;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.List;

public class Vector {

    private final Float[] values;

    public Vector(final int vectorLength) {
        assert vectorLength > 0;
        this.values = new Float[vectorLength];
        for(int i = 0; i < values.length; i++) {
            this.values[i] = 0f;
        }
    }

    public Vector(final Float ...values) {
        assert values.length > 0;
        this.values = new Float[values.length];
        for(int i = 0; i < values.length; i++) {
            this.values[i] = values[i];
        }
    }

    public Vector(final List<Float> floatList) {
        Float[] floats = new Float[floatList.size()];
        this.values = floatList.toArray(floats);
    }

    public Vector add(final Vector vector) {
        assert this.values.length == vector.values.length;
        final Float[] values = new Float[this.values.length];
        for(int i = 0; i < this.values.length; i++) {
            values[i] = this.values[i] + vector.values[i];
        }
        return new Vector(values);
    }

    public Vector multiply(final Vector vector) {
        assert this.values.length == vector.values.length;
        final Float[] values = new Float[this.values.length];
        for(int i = 0; i < this.values.length; i++) {
            values[i] = this.values[i] * vector.values[i];
        }
        return new Vector(values);
    }

    public Vector sigmoid() {
        final Float[] values = new Float[this.values.length];
        for(int i = 0; i < this.values.length; i++) {
            values[i] = NeuralFunctions.sigmoid(this.values[i]);
        }
        return new Vector(values);
    }

    public Vector tanh() {
        final Float[] values = new Float[this.values.length];
        for(int i = 0; i < this.values.length; i++) {
            values[i] = NeuralFunctions.tanh(this.values[i]);
        }
        return new Vector(values);
    }

    public Vector concatenate(final Vector vector) {
        return new Vector((Float[]) ArrayUtils.addAll(this.values, vector.values));
    }

    public List<Float> toList() {
        return Arrays.asList(this.values);
    }

}
