package solver;

import java.util.List;
import java.util.Scanner;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.NeuralNetworkStructure;
import neural_net.Neuron;

/**
 * @author cauthon
 */
public abstract class FeedForwardNeuralNetworkStrategy extends NeuralNetworkAlgorithmStrategy {

	protected NeuralNetworkStructure neuralNetStructure;
	protected double alpha;
	protected double eta;
	protected double targetOutput;

	public FeedForwardNeuralNetworkStrategy(NeuralNetworkStructure neuralNetStructure, double alpha, double eta) {
		this.neuralNetStructure = neuralNetStructure;
		this.alpha = alpha;
		this.eta = eta;
	}

	public void print() {
		System.out.println(neuralNetStructure.toString());
	}

	public void pause() {
		System.out.println(neuralNetStructure.toString());
		Scanner in = new Scanner(System.in);
		System.out.println("Press enter to continue");
		String go = in.next();
	}

	public double getNNOutput() {
		return neuralNetStructure.getLayers().get(neuralNetStructure.getLayers().size() - 1).getNeuronVector().get(0).getNeuronValue();
	}

}
