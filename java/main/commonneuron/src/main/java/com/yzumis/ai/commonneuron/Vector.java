package com.yzumis.ai.commonneuron;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.List;

public class Vector {

    private final Double[] values;

    public Vector(final int vectorLength) {
        assert vectorLength > 0;
        this.values = new Double[vectorLength];
        for(int i = 0; i < values.length; i++) {
            this.values[i] = 0.0;
        }
    }

    public Vector(final Double ...values) {
        assert values.length > 0;
        this.values = new Double[values.length];
        for(int i = 0; i < values.length; i++) {
            this.values[i] = values[i];
        }
    }

    public Vector(final List<Double> doubleList) {
        final Double[] doubles = new Double[doubleList.size()];
        this.values = doubleList.toArray(doubles);
    }

    public Vector add(final Vector vector) {
        assert this.values.length == vector.values.length;
        final Double[] values = new Double[this.values.length];
        for(int i = 0; i < this.values.length; i++) {
            values[i] = this.values[i] + vector.values[i];
        }
        return new Vector(values);
    }

    public Vector multiply(final Vector vector) {
        assert this.values.length == vector.values.length;
        final Double[] values = new Double[this.values.length];
        for(int i = 0; i < this.values.length; i++) {
            values[i] = this.values[i] * vector.values[i];
        }
        return new Vector(values);
    }

    public Vector sigmoid() {
        final Double[] values = new Double[this.values.length];
        for(int i = 0; i < this.values.length; i++) {
            values[i] = NeuralFunctions.sigmoid(this.values[i]);
        }
        return new Vector(values);
    }

    public Vector tanh() {
        final Double[] values = new Double[this.values.length];
        for(int i = 0; i < this.values.length; i++) {
            values[i] = NeuralFunctions.tanh(this.values[i]);
        }
        return new Vector(values);
    }

    public Vector concatenate(final Vector vector) {
        return new Vector((Double[]) ArrayUtils.addAll(this.values, vector.values));
    }

    public List<Double> toList() {
        return Arrays.asList(this.values);
    }

    public double get(final int index) {
        return this.values[index];
    }

    public int size() {
        return this.values.length;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for(int i = 0; i < this.values.length; i++) {
            stringBuilder.append(this.values[i]);
            stringBuilder.append(",");
        }
        if(stringBuilder.lastIndexOf(",") != -1) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(final Object object) {
        final boolean ret;
        if(object instanceof Vector) {
            final Vector vector = (Vector) object;
            return equalsAllValues(this.values, vector.values);
        } else {
            ret = false;
        }
        return ret;
    }

    private boolean equalsAllValues(Double[] valuesA, Double[] valuesB) {
        final boolean ret;
        if(valuesA.length == valuesB.length) {
            boolean allValuesEqual = true;
            for(int i = 0; i < valuesA.length; i++) {
                if(valuesA[i] != valuesB[i]) {
                    allValuesEqual = false;
                }
            }
            ret = allValuesEqual;
        } else {
            ret = false;
        }
        return ret;
    }
}
