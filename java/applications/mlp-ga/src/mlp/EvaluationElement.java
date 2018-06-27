package mlp;

import java.util.List;

/**
 * Created by Yzumi on 22/12/2017.
 */
public class EvaluationElement {

    private final List<Float> inputs;
    private final List<Float> desiredOutputs;

    public EvaluationElement(final List<Float> inputs, final List<Float> desiredOutputs) {
        this.inputs = inputs;
        this.desiredOutputs = desiredOutputs;
    }

    public List<Float> getInputs() {
        return inputs;
    }

    public List<Float> getDesiredOutputs() {
        return desiredOutputs;
    }

}
