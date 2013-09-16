package driver;

import java.util.ArrayList;
import java.util.List;

import neural_net.NeuralNetworkModel;
import neural_net.NonRecurrentNeuralNetwork;
import solver.ActivationFunction;
import solver.LinearActivationFunction;
import solver.Solver;

public class Simulator {

	private ActivationFunction activationFunction;
	private NeuralNetworkModel neuralNet;
	private Solver solver;
	private List<Double> inputVector;
	private int numInputNeurons = 2;
	private int numOutputNeurons = 1;
	private int numHiddenLayers = 1;
	private int numNeuronsPerHiddenLayer = 4;
	private int numConnectionsPerInputNeuron = 4;
	private int numConnectionsPerHiddenNeuron = 1;// equal to output
													// or next
													// hidden layer
	private int targetOutput = 100;

	public Simulator() {
		findRosenbrockInputVector();
		linearActivation();

		// neuralNet = new FeedForwardNeuralNetwork(20, 20, 3, 3, 20,
		// 20); //3
		// hidden, 3 output, 20 input

		// neuralNet = new FeedForwardNeuralNetwork(5, 20, 1, 1, 20,
		// 20);
		// //single hidden, single output, 5 input

		// neuralNet = new FeedForwardNeuralNetwork(1, 2, 1, 1, 2, 1);
		// most basic.\

		neuralNet = new NonRecurrentNeuralNetwork(activationFunction, numInputNeurons, inputVector, numOutputNeurons, numHiddenLayers,
				numNeuronsPerHiddenLayer);

		/*
		 * neuralNet = new FeedForwardNeuralNetwork(neuralNetworkType,
		 * numInputNeurons, numConnectionsPerInputNeuron,
		 * numOutputNeurons, numHiddenLayers,
		 * numNeuronsPerHiddenLayer, numConnectionsPerHiddenNeuron,
		 * inputVector);
		 */

		neuralNet.buildModelStructure();

		System.out.println(neuralNet.toString());
	}

/*	public void mainLoop(int numIterations) {
		for (int i = 0; i < numIterations; i++) {
			neuralNet.feedForward();
			neuralNet.solve(targetOutput);
		}
	}*/

	public void linearActivation() {
		activationFunction = new LinearActivationFunction();
	}

	public void findRosenbrockInputVector() {
		inputVector = new ArrayList<>();
		for (int i = 1; i <= numInputNeurons; i++) {
			inputVector.add(new Double(i));
		}
	}
}
