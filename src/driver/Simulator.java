package driver;

import java.util.ArrayList;
import java.util.List;

import neural_net.FeedForwardNeuralNetwork;
import neural_net.Layer;
import neural_net.NeuralNetwork;

public class Simulator {

	private NeuralNetwork neuralNet;
	private List<Double> inputVector;
	private String neuralNetworkType = "BACKPROP";
	private int numInputNeurons = 2;
	private int numOutputNeurons = 1;
	private int numHiddenLayers = 1;
	private int numNeuronsPerHiddenLayer = 4;
	private int numConnectionsPerInputNeuron = 4;
	private int numConnectionsPerHiddenNeuron = 1;// equal to output or next
													// hidden layer
	private int targetOutput = 100;

	public Simulator() {
		findRosenbrockInputVector();

		// neuralNet = new FeedForwardNeuralNetwork(20, 20, 3, 3, 20, 20); //3
		// hidden, 3 output, 20 input

		// neuralNet = new FeedForwardNeuralNetwork(5, 20, 1, 1, 20, 20);
		// //single hidden, single output, 5 input

		// neuralNet = new FeedForwardNeuralNetwork(1, 2, 1, 1, 2, 1);
		// most basic.

		neuralNet = new FeedForwardNeuralNetwork(neuralNetworkType, numInputNeurons, numConnectionsPerInputNeuron, numOutputNeurons, numHiddenLayers, numNeuronsPerHiddenLayer, numConnectionsPerHiddenNeuron, inputVector);

		neuralNet.buildNetworkStructure();

		for (Layer l : ((FeedForwardNeuralNetwork) neuralNet.getNetworkType()).getNetStructure().getLayers()) {
			System.out.println(l.toString());
		}
	}

	public void mainLoop(int numIterations) {
		for (int i = 0; i < numIterations; i++) {
			neuralNet.feedForward();
			neuralNet.solve(targetOutput);
		}
	}

	public void findRosenbrockInputVector() {
		inputVector = new ArrayList<>();
		for (int i = 1; i <= numInputNeurons; i++) {
			inputVector.add(new Double(i));
		}
	}
}
