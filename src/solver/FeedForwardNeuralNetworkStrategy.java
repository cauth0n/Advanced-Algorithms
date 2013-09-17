package solver;

import neural_net.NeuralNetworkStructure;

/**
 * @author cauthon
 */
public abstract class FeedForwardNeuralNetworkStrategy extends NeuralNetworkAlgorithmStrategy {

	protected NeuralNetworkStructure neuralNetStructure;
	protected double alpha;
	protected double eta;

	public FeedForwardNeuralNetworkStrategy(NeuralNetworkStructure neuralNetStructure, double alpha, double eta) {
		this.neuralNetStructure = neuralNetStructure;
		this.alpha = alpha;
		this.eta = eta;
	}

	public abstract void feedForward();

	public abstract void backPropagateError();

}
