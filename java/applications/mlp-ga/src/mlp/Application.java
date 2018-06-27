package mlp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 22/12/2017.
 */
public class Application {

    public static void main(final String[] args) {
        final int numberOfInputs = 2;
        final List<Integer> neuronsPerLayer = new ArrayList<>();
        neuronsPerLayer.add(2); // 2 input neurons
        neuronsPerLayer.add(2); // 2 neurons in hidden layer
        neuronsPerLayer.add(1); // 1 output
        final int numberOfGenerations = 300000;
        List<EvaluationElement> evaluationElements = new ArrayList<>();

        // 00 --> 0
        final List<Float> inputs00 = new ArrayList<>();
        inputs00.add(0f);
        inputs00.add(0f);
        final List<Float> outputs00 = new ArrayList<>();
        outputs00.add(0f);
        final EvaluationElement evaluationElement00 = new EvaluationElement(inputs00, outputs00);
        evaluationElements.add(evaluationElement00);
        // 01 --> 1
        final List<Float> inputs01 = new ArrayList<>();
        inputs01.add(0f);
        inputs01.add(1f);
        final List<Float> outputs01 = new ArrayList<>();
        outputs01.add(1f);
        final EvaluationElement evaluationElement01 = new EvaluationElement(inputs01, outputs01);
        evaluationElements.add(evaluationElement01);
        // 10 --> 1
        final List<Float> inputs10 = new ArrayList<>();
        inputs10.add(1f);
        inputs10.add(0f);
        final List<Float> outputs10 = new ArrayList<>();
        outputs10.add(1f);
        final EvaluationElement evaluationElement10 = new EvaluationElement(inputs10, outputs10);
        evaluationElements.add(evaluationElement10);
        // 11 --> 0
        final List<Float> inputs11 = new ArrayList<>();
        inputs11.add(1f);
        inputs11.add(1f);
        final List<Float> outputs11 = new ArrayList<>();
        outputs11.add(0f);
        final EvaluationElement evaluationElement11 = new EvaluationElement(inputs11, outputs11);
        evaluationElements.add(evaluationElement11);
        final GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(30, 0.5f, numberOfInputs, neuronsPerLayer);
        final Mlp mlp300 = geneticAlgorithm.evolve(300, evaluationElements);
        System.out.println("Output after 300 generations");
        System.out.println("Output [0, 0] = " + mlp300.calculateOutputs(inputs00));
        System.out.println("Output [0, 1] = " + mlp300.calculateOutputs(inputs01));
        System.out.println("Output [1, 0] = " + mlp300.calculateOutputs(inputs10));
        System.out.println("Output [1, 1] = " + mlp300.calculateOutputs(inputs11));
        final Mlp mlp1000 = geneticAlgorithm.evolve(1000, evaluationElements);
        System.out.println("Output after 1000 generations");
        System.out.println("Output [0, 0] = " + mlp1000.calculateOutputs(inputs00));
        System.out.println("Output [0, 1] = " + mlp1000.calculateOutputs(inputs01));
        System.out.println("Output [1, 0] = " + mlp1000.calculateOutputs(inputs10));
        System.out.println("Output [1, 1] = " + mlp1000.calculateOutputs(inputs11));


    }
}
