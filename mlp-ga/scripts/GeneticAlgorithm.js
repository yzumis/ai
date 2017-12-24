class GeneticAlgorithm {

    constructor(populationSize, mutationRate, numberOfInputs, neuronsPerLayer) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.numberOfInputs = numberOfInputs;
        this.neuronsPerLayer = neuronsPerLayer; 
    }

    evolve(numberOfGenerations, evaluationSet) {
        var population = this.initializePopulation();
        for(var i = 0; i < numberOfGenerations; i++) {
            this.selection(population, evaluationSet);
            population = this.reproduction(population);
        }
        this.selection(population, evaluationSet);
        return population[this.selectBestIndividual(population)].mlp;
    }

    initializePopulation() {
        var ret = [];
        for(var i = 0; i < this.populationSize; i++) {
            var mlp = new Mlp(this.numberOfInputs, this.neuronsPerLayer, null);
            var individual = new Individual(mlp);
            ret.push(individual);
        }
        return ret;
    }

    selection(population, evaluationSet) {
        for(var i = 0; i < this.populationSize; i++) {
            population[i].calculateFitness(evaluationSet);
        }
    }

    reproduction(population) {
        var ret = [];
        var totalPopulationFitness = this.calculateTotalPopulationFitness(population);
        for(var i = 0; i < this.populationSize; i++) {
            //var individualIndex1 = this.selectBestIndividual(population);
            //var individualIndex2 = this.selectBestIndividual2(population, individualIndex1);
            //var individual1 = population[individualIndex1];
            //var individual2 = population[individualIndex2];
            var individual1 = this.pickRandomIndividual(population, totalPopulationFitness);
            var individual2 = this.pickRandomIndividual(population, totalPopulationFitness);
            ret.push(Individual.reproduce(individual1, individual2, this.mutationRate));
        }
        return ret;
    }

    calculateTotalPopulationFitness(population) {
        var ret = 0;
        for(var i = 0; i < this.populationSize; i++) {
            ret += population[i].getFitness();
        }
        return ret;
    }

    pickRandomIndividual(population, totalPopulationFitness) {
        var random = Math.random();
        var currentFitness = 0;
        for(var i = 0; i < this.populationSize; i++) {
            currentFitness += population[i].getFitness();
            if(currentFitness / totalPopulationFitness > random) {
                return population[i];
            }
        }
        return population[this.populationSize - 1];
    }

    selectBestIndividual(population) {
        var ret = -1;
        for(var i = 0; i < this.populationSize; i++) {
            if((ret === -1) || (population[ret].getFitness() < population[i].getFitness())) {
                ret = i;
            }
        }
        return ret;
    }

    selectBestIndividual2(population, bestIndividualIndex) {
        var ret = -1;
        for(var i = 0; i < this.populationSize; i++) {
            if(i !== bestIndividualIndex) {
                if((ret === -1) || (population[ret].getFitness() < population[i].getFitness())) {
                    ret = i;
                }
            }
        }
        return ret;
    }

}