package solver;

import neural_net.NeuralNetworkStructure;
import validation.DataPoint;

/**
 * @author cauthon
 */
public class BackPropagationStrategy extends FeedForwardNeuralNetworkStrategy {

	public BackPropagationStrategy(NeuralNetworkStructure neuralNetStructure, double alpha, double eta) {
		super(neuralNetStructure, alpha, eta);
	}

	public double mainTestLoop(DataPoint d) {
		feedForward(d.getInputValues());
		return getNNOutput();
	}

	public double mainTrainingLoop(DataPoint d, StoppingCondition stoppingCondition) {
		double errorFromThisRound = 0.0;
		targetOutput = d.getTargetOutput();
		feedForward(d.getInputValues());
		backPropagateError();
		backPropagateWeightErrors();
		updateWeights();
		errorFromThisRound = Math.abs(getNNOutput() - d.getTargetOutput());
		return errorFromThisRound;
	}

}
