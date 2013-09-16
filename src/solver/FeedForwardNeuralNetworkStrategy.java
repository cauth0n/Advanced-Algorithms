package solver;

import neural_net.NeuralNetworkStructure;

/**
 * @author cauthon
 */
public abstract class FeedForwardNeuralNetworkStrategy extends NeuralNetworkAlgorithmStrategy {

	protected NeuralNetworkStructure neuralNetStructure;

	public FeedForwardNeuralNetworkStrategy(NeuralNetworkStructure neuralNetStructure) {
		this.neuralNetStructure = neuralNetStructure;
	}

	public abstract void feedForward();

	public void solvingChain(double targetOutput) {
		feedForward();
		solve(targetOutput);
	}

}
