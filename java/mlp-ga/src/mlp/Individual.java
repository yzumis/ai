package mlp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 22/12/2017.
 */
public class Individual {

    private final Mlp mlp;
    private float fitness;
    private List<List<Float>> outputsList;

    public Individual(final Mlp mlp) {
        this.mlp = mlp;
    }

    public Mlp getMlp() {
        return mlp;
    }

    public void calculateOutputs(List<EvaluationElement> evaluationElements) {
        this.outputsList = new ArrayList<>();
        for(final EvaluationElement evaluationElement: evaluationElements) {
            final List<Float> outputs = this.mlp.calculateOutputs(evaluationElement.getInputs());
            this.outputsList.add(outputs);
        }
    }

    public float getFitness() {
        return fitness;
    }

    public static Individual reproduce(final Individual individual1, final Individual individual2, final float mutationRate) {
        final Mlp mlp = Mlp.reproduce(individual1.mlp, individual2.mlp, mutationRate);
        return new Individual(mlp);
    }

    public float calculateOutputDifference(final List<EvaluationElement> evaluationElements) {
        float ret = 0;
        for(int i = 0; i < evaluationElements.size(); i++) {
            for(int j = 0; j < evaluationElements.get(i).getDesiredOutputs().size(); j++) {
                ret += Math.abs(outputsList.get(i).get(j) - evaluationElements.get(i).getDesiredOutputs().get(j));
            }
        }
        return ret;
    }

    public void calculateFitness(final List<Individual> population, final List<EvaluationElement> evaluationElements) {
        final float maximumOutputDifference = evaluationElements.size() * 4; // Four outputs per EvaluationElement
        final float currentOutputDifference = this.calculateOutputDifference(evaluationElements);
        this.fitness = 1 - (maximumOutputDifference - currentOutputDifference) / maximumOutputDifference;
    }

}
