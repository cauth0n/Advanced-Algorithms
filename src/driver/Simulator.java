package driver;

import neural_net.FeedForwardNeuralNetwork;
import neural_net.Layer;
import neural_net.NeuralNetwork;

public class Simulator {


	NeuralNetwork neuralNet;

	public Simulator() {
		// neuralNet = new FeedForwardNeuralNetwork(20, 20, 3, 3, 20, 20); //3
		// hidden, 3 output, 20 input
		// neuralNet = new FeedForwardNeuralNetwork(5, 20, 1, 1, 20, 20);
		// //single hidden, single output, 5 input
		neuralNet = new FeedForwardNeuralNetwork(1, 2, 1, 1, 2, 1); // most
																	// basic.
		neuralNet.buildNetworkStructure();
		for (Layer l : ((FeedForwardNeuralNetwork) neuralNet.getNetworkType()).getNetStructure().getLayers()) {
			System.out.println(l.toString());
		}
	}

}
