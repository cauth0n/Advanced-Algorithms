package driver;

import neural_net.ActivationFunction;
import neural_net.DataFunction;
import neural_net.FeedForwardNeuralNetwork;
import neural_net.LinearActivationFunction;
import neural_net.NeuralNetwork;

public class Simulator {

	public static final ActivationFunction activationFunction = new LinearActivationFunction();
	public static final DataFunction dataFunction = new DataFunction();
	NeuralNetwork neuralNet;

	public Simulator() {
		neuralNet = new FeedForwardNeuralNetwork();
		neuralNet.buildNetworkStructure();
	}

}
