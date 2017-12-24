class Mlp {

    constructor(numberOfInputs, neuronsPerLayer, neuronLayers) {
        if(neuronLayers === null) {
            this.neuronLayers = [];
            for (var i = 0; i < neuronsPerLayer.length; i++) {
                var neuronLayer = this.initializeNeuronLayer(numberOfInputs, neuronsPerLayer[i], i);
                this.neuronLayers.push(neuronLayer);
            }
        } else {
            this.neuronLayers = neuronLayers;
        }
    }

    initializeNeuronLayer(numberOfInputs, numberOfNeurons, numberOflayer) {
        var ret = [];
        for(var i = 0; i < numberOfNeurons; i++) {
            var neuron;
            if(numberOflayer === 0) {
                neuron = new Neuron(numberOfInputs, null, null);
            } else {
                var middleLayerNumberOfInputs = this.neuronLayers[numberOflayer - 1].length;
                neuron = new Neuron(middleLayerNumberOfInputs, null, null);
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

    print() {
        for(var i = 0; i < this.neuronLayers.length; i++) {
            for(var j = 0; j < this.neuronLayers[i].length; j++) {
                console.log("[" + i + "][" + j + "]");
                this.neuronLayers[i][j].print();
            }
        }
    }

    static reproduce(mlp1, mlp2, mutationRate) {
        var neuronLayers = [];
        for(var i = 0; i < mlp1.neuronLayers.length; i++) {
            var neuronLayer = [];
            for(var j = 0; j < mlp1.neuronLayers[i].length; j++) {
                var neuron = Neuron.reproduce(mlp1.neuronLayers[i][j], mlp2.neuronLayers[i][j], mutationRate);
                neuronLayer.push(neuron)
            }
            neuronLayers.push(neuronLayer);
        }
        return new Mlp(null, null, neuronLayers);
    }

}