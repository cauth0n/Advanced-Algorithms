package driver;

import neural_net.ActivationFunction;
import neural_net.FeedForwardNeuralNetwork;
import neural_net.LinearActivationFunction;
import neural_net.NeuralNetwork;

public class Simulator {

	public static final ActivationFunction activationFunction = new LinearActivationFunction();
	NeuralNetwork neuralNet;

	public Simulator() {
		neuralNet = new FeedForwardNeuralNetwork();
	}

}
