package com.yzumis.lstm;

public class NeuralFunctions {

    public static Float sigmoid(final Float value) {
        return 1 / (1 + (float)Math.exp(-value));
    }

    public static Float tanh(final Float value) {
        return (float)Math.tanh(value);
    }

}
