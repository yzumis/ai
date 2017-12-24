class Neuron {

    constructor(numberOfConnections, weights, bias) {
        if((weights === null) || (bias === null)) {
            this.weights = [];
            for(var i = 0; i < numberOfConnections; i++) {
                this.weights.push(Neuron.randomWeight());
            }
            this.bias = Math.random();
            this.output = 0;
        } else {
            this.weights = weights;
            this.bias = bias;
            this.output = 0;
        }
    }

    static randomWeight() {
        return 2 * Math.random() - 1;;
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

    getOutput() {
        return this.output;
    }

    print() {
        console.log("weights = " + this.weights);
        console.log("output = " + this.output);
        console.log("bias = " + this.bias);
    }

    static reproduce(neuron1, neuron2, mutationRate) {
        var weights = [];
        for(var i = 0; i < neuron1.weights.length; i++) {
            if(Math.random() < 0.5) {
                weights.push(neuron1.weights[i]);
            } else {
                weights.push(neuron2.weights[i]);
            }
            weights[weights.length - 1] = Neuron.randomDeltaMutation(weights[weights.length - 1], mutationRate);
        }
        var bias;
        if(Math.random() < 0.5) {
            bias = neuron1.bias;
        } else {
            bias = neuron2.bias;
        }
        bias = Neuron.randomDeltaMutation(bias, mutationRate);
        return new Neuron(null, weights, bias);
    }

    static randomDeltaMutation(value, mutationRate) {
        var deltaValue;
        if(Math.random() < mutationRate) { 
            deltaValue = 2 * Math.random() - 1;
        } else {
            deltaValue = 0;
        }
        
        return value + deltaValue;
    }

}