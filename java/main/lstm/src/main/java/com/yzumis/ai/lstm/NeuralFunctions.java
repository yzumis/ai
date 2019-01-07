package com.yzumis.ai.lstm;

public class NeuralFunctions {

    public static Double sigmoid(final Double value) {
        return 1 / (1 + Math.exp(-value));
    }

    public static Double tanh(final Double value) {
        return Math.tanh(value);
    }

}
