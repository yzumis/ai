class Mlp {

    constructor(numberOfInputs, neuronsPerLayer, learningRate) {
        this.neuronLayers = [];
        for (var i = 0; i < neuronsPerLayer.length; i++) {
            var neuronLayer = this.initializeNeuronLayer(numberOfInputs, neuronsPerLayer[i], i);
            this.neuronLayers.push(neuronLayer);
        }
        this.learningRate = learningRate;
        this.debug = true;
    }

    initializeNeuronLayer(numberOfInputs, numberOfNeurons, numberOflayer) {
        var ret = [];
        for(var i = 0; i < numberOfNeurons; i++) {
            var neuron;
            if(numberOflayer === 0) {
                neuron = new Neuron(numberOfInputs);
            } else {
                var middleLayerNumberOfInputs = this.neuronLayers[numberOflayer - 1].length;
                neuron = new Neuron(middleLayerNumberOfInputs);
            }
            ret.push(neuron);
        }
        return ret;
    }

    calculateOutputs(inputs) {
        for(var i = 0; i < this.neuronLayers.length; i++) {
            for(var j = 0; j < this.neuronLayers[i].length; j++) {
                if(i === 0) {
                    this.neuronLayers[i][j].calculateOutput(inputs);
                } else {
                    var middleLayerInputs = this.getLayerOutputs(this.neuronLayers[i - 1]);
                    this.neuronLayers[i][j].calculateOutput(middleLayerInputs);
                }
            }
        }
        return this.getLayerOutputs(this.neuronLayers[this.neuronLayers.length - 1]);
    }

    getLayerOutputs(neuronLayer) {
        var ret = [];
        for(var i = 0; i < neuronLayer.length; i++) {
            ret.push(neuronLayer[i].getOutput());
        }
        return ret;
    }

    train(trainingElement) {
        this.calculateOutputs(trainingElement.inputs);
        // train output layer
        var outputLayer = this.neuronLayers.length - 1;
        this.trainOutputLayer(trainingElement.desiredOutputs, this.neuronLayers[outputLayer], this.neuronLayers[outputLayer - 1]);
        // train inner layers
        for(var i = this.neuronLayers.length - 2; i > 0; i--) {
            this.trainInnerLayer(this.neuronLayers[i], this.neuronLayers[i - 1], this.neuronLayers[i + 1]);
        }
        // train input layer
        this.trainInputLayer(this.neuronLayers[0], trainingElement.inputs, this.neuronLayers[1]);
    }

    trainOutputLayer(desiredOutputs, currentLayer, previousLayer) {
        for(var i = 0; i < currentLayer.length; i++) {
            var currentNeuron = currentLayer[i];
            for(var j = 0; j < currentNeuron.getWeights().length; j++) {
                var jOutput = currentNeuron.getOutput();
                var jTargetOutput = desiredOutputs[i];
                var iOutput = previousLayer[j].getOutput();
                var jDeltaOutput = (jOutput - jTargetOutput) * jOutput * (1 - jOutput);
                var ijDeltaWeight = -this.learningRate  * iOutput * jDeltaOutput;
                currentNeuron.setDeltaOutput(jDeltaOutput);
                currentNeuron.adjustWeight(ijDeltaWeight, j);
            }
            // Train bias:
            var jOutput = currentNeuron.getOutput();
            var jTargetOutput = desiredOutputs[i];
            var iOutput = 1;
            var jDeltaOutput = (jOutput - jTargetOutput) * jOutput * (1 - jOutput);
            var ijDeltaWeight = -this.learningRate  * iOutput * jDeltaOutput;
            currentNeuron.adjustBias(ijDeltaWeight);

        }
    }

    trainInnerLayer(currentLayer, previousLayer, nextLayer) {
        for(var i = 0; i < currentLayer.length; i++) {
            var currentNeuron = currentLayer[i];
            for(var j = 0; j < currentNeuron.getWeights().length; j++) {
                var jOutput = currentNeuron.getOutput();
                var iOutput = previousLayer[j].getOutput();
                var summation = 0;
                for(var l = 0; l  < nextLayer.length; l++) {
                    summation += nextLayer[l].getDeltaOutput() * nextLayer[l].getWeight(i);
                }
                var jDeltaOutput = summation * jOutput * (1 - jOutput);
                var ijDeltaWeight = -this.learningRate * iOutput * jDeltaOutput;
                currentNeuron.setDeltaOutput(jDeltaOutput);
                currentNeuron.adjustWeight(ijDeltaWeight, j);
            }
            // Train bias:
            var jOutput = currentNeuron.getOutput();
            var iOutput = 1;
            var summation = 0;
            for(var l = 0; l  < nextLayer.length; l++) {
                summation += nextLayer[l].getDeltaOutput() * nextLayer[l].getWeight(i);
            }
            var jDeltaOutput = summation * jOutput * (1 - jOutput);
            var ijDeltaWeight = -this.learningRate  * iOutput * jDeltaOutput;
            currentNeuron.adjustBias(ijDeltaWeight);
        }
    }

    trainInputLayer(currentLayer, inputs, nextLayer) {
        for(var i = 0; i < currentLayer.length; i++) {
            var currentNeuron = currentLayer[i];
            for(var j = 0; j < currentNeuron.getWeights().length; j++) {
                var jOutput = currentNeuron.getOutput();
                var iOutput = inputs[j];
                var summation = 0;
                for(var l = 0; l  < nextLayer.length; l++) {
                    summation += nextLayer[l].getDeltaOutput() * nextLayer[l].getWeight(i);
                }
                var jDeltaOutput = summation * jOutput * (1 - jOutput);
                var ijDeltaWeight = -this.learningRate  * iOutput * jDeltaOutput;
                currentNeuron.adjustWeight(ijDeltaWeight, j);
            }
            // Train bias:
            var jOutput = currentNeuron.getOutput();
            var iOutput = 1;
            var summation = 0;
            for(var l = 0; l  < nextLayer.length; l++) {
                summation += nextLayer[l].getDeltaOutput() * nextLayer[l].getWeight(i);
            }
            var jDeltaOutput = summation * jOutput * (1 - jOutput);
            var ijDeltaWeight = -this.learningRate  * iOutput * jDeltaOutput;
            currentNeuron.adjustBias(ijDeltaWeight);
        }
    }

    print() {
        for(var i = 0; i < this.neuronLayers.length; i++) {
            for(var j = 0; j < this.neuronLayers[i].length; j++) {
                console.log("[" + i + "][" + j + "]");
                this.neuronLayers[i][j].print();
            }
        }
    }

}