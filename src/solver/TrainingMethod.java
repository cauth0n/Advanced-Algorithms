package solver;

import neural_net.AbstractNeuralNetworkStructureFactory;
import validation.FunctionApproxDataPoint;

public abstract class TrainingMethod {

	protected AbstractNeuralNetworkStructureFactory neuralNetworkStructure;

	public TrainingMethod(
			AbstractNeuralNetworkStructureFactory neuralNetworkStructure) {
		this.neuralNetworkStructure = neuralNetworkStructure;
	}

	/**
	 * Main training loop. Takes in a data point, performs whatever learning
	 * operation, then returns error associated with that training round.
	 * 
	 * @param d
	 *            data point to train on
	 * @return error from data point's training round
	 */
	public abstract double mainTrainingLoop(FunctionApproxDataPoint d);

}
