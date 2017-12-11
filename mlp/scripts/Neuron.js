class Neuron {

    constructor(numberOfConnections) {
        this.weights = [];
        for(var i = 0; i < numberOfConnections; i++) {
            this.weights.push(Math.random());
        }
        this.bias = Math.random();
        this.deltaOutput = 0;
        this.output = 0;
    }

    calculateOutput(inputs) {
        var weightedInput = this.calculateWeightedInput(inputs);
        weightedInput += this.bias;
        this.output = 1 / (1 + Math.exp(-weightedInput));
    }

    calculateWeightedInput(inputs) {
        var ret = 0;
        for(var i = 0; i < inputs.length; i++) {
            ret += inputs[i] * this.weights[i];
        }
        return ret;
    }

    adjustWeight(deltaWeight, i) {
        this.weights[i] += deltaWeight;
    }

    getOutput() {
        return this.output;
    }

    getDeltaOutput() {
        return this.deltaOutput;
    }

    setDeltaOutput(deltaOutput) {
        this.deltaOutput = deltaOutput;
    }

    getWeights() {
        return this.weights;
    }

    getWeight(i) {
        return this.weights[i];   
    }

    getBias() {
        return this.bias;
    }

    adjustBias(ijDeltaWeight) {
        this.bias += ijDeltaWeight;
    }

    print() {
        console.log("weights = " + this.weights);
        console.log("deltaOutput = " + this.deltaOutput);
        console.log("output = " + this.output);
        console.log("bias = " + this.bias);
    }

}