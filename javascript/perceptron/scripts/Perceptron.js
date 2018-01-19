class Perceptron {

    constructor(numberOfInputs, bias) {
        this.numberOfInputs = numberOfInputs;
        this.bias = bias;
        this.weights = this.initRandomWeights(numberOfInputs);
        this.learningTax = 0.5;
    }

    initRandomWeights(numberOfInputs) {
        var ret = [];
        for(var i = 0; i < numberOfInputs; i++) {
            var weight = Math.random();
            ret.push(weight);
        }
        return ret;
    }

    calculateOutput(inputs) {
        var ret = 0;
        for(var i = 0; i < this.numberOfInputs; i++) {
            ret +=  inputs[i] * this.weights[i];
        }
        ret -= this.bias;
        if(ret > 0) {
            ret = 1;
        } else {
            ret = 0;
        }
        return ret;
    }

    train(trainingSet) {
        var errors = true;
        while(errors) {
            errors = false;
            for(var i = 0; i < trainingSet.length; i++) {
                if(this.trainWithElement(trainingSet[i])) {
                    errors = true;
                }
            }
        }
    }

    trainWithElement(trainingElement) {
        var output = this.calculateOutput(trainingElement.inputs);
        this.updateWeights(output, trainingElement);
        return output !== trainingElement.desiredOutput;
    }

    updateWeights(output, trainingElement) {
        for(var i = 0; i < this.numberOfInputs; i++) {
            this.updateWeight(output, trainingElement, i);
        } 
    }

    updateWeight(output, trainingElement, i) {
        this.weights[i] = this.weights[i] + this.learningTax * (trainingElement.desiredOutput - output) * trainingElement.inputs[i];
    }

}