class Individual {

    constructor(mlp) {
        this.mlp = mlp;
        this.fitness = 0;
    }

    getFitness() {
        return this.fitness;
    }

    calculateFitness(evaluationSet) {
        var totalFitness = 0;
        for(var i = 0; i < evaluationSet.length; i++) {
            var evaluationElement = evaluationSet[i];
            var outputs = this.mlp.calculateOutputs(evaluationElement.inputs);
            totalFitness += this.calculateOutputsFitness(outputs, evaluationElement.desiredOutputs);
        }
        var maximumFitness = evaluationSet.length;
        this.fitness = totalFitness / maximumFitness;
    }

    calculateOutputsFitness(outputs, desiredOutputs) {
        var outputDifference = 0;
        for(var i = 0; i < desiredOutputs.length; i++) {
            outputDifference +=  Math.abs(desiredOutputs[i] - outputs[i]);
        }
        var maximumFitness = desiredOutputs.length;
        var ret = (maximumFitness - outputDifference) / maximumFitness;
        return ret;
    }

    static reproduce(individual1, individual2, mutationRate) {
        var mlp = Mlp.reproduce(individual1.mlp, individual2.mlp, mutationRate);
        return new Individual(mlp);
    }
}