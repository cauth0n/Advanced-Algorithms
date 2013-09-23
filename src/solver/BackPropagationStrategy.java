package solver;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.NeuralNetworkStructure;
import neural_net.Neuron;
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
		double output = getNNOutput();
		System.out.println(d.toString());
		return output;
	}

	public void mainTrainingLoop(DataPoint d, StoppingCondition stoppingCondition) {
		stoppingCondition.reset();
		targetOutput = d.getTargetOutput();
		stoppingCondition.setTarget(targetOutput);
		int counter = 0;
		while (!stoppingCondition.isDone()) {
			feedForward(d.getInputValues());
			backPropagateError();
			backPropagateWeightErrors();
			updateWeights();
			stoppingCondition.postRoundOperation(getNNOutput());
			counter++;
			// System.out.println("Target output: " +
			// d.getTargetOutput() + " current output: " +
			// getNNOutput());
		}

		// System.out.println(neuralNetStructure.toString());
		// System.out.println(counter + " Iterations");
	}

}
