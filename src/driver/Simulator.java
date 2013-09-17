package driver;

import java.util.ArrayList;
import java.util.List;

import neural_net.NeuralNetworkModel;
import neural_net.NonRecurrentNeuralNetwork;
import solver.ActivationFunction;
import solver.SigmoidActivationFunction;
import solver.Solver;
import validation.KFoldCrossValidation;
import validation.Validation;

public class Simulator {

	private ActivationFunction activationFunction;
	private NeuralNetworkModel neuralNet;
	private Validation validation;
	private Solver solver;
	private List<Double> inputVector;
	private int numInputNeurons = 2;
	private int numOutputNeurons = 1;
	private int numHiddenLayers = 2;
	private int numNeuronsPerHiddenLayer = 4;
	private double targetOutput = 100.0;
	private int numIterations = 4000;
	private double eta = .00001;
	private double alpha = .5;

	public Simulator() {
		findRosenbrockInputVector();
		linearActivation();

		neuralNet = new NonRecurrentNeuralNetwork(activationFunction, numInputNeurons, inputVector, numOutputNeurons, numHiddenLayers,
				numNeuronsPerHiddenLayer);

		neuralNet.buildModelStructure();

		// System.out.println(neuralNet.toString());

		validation = new KFoldCrossValidation();
		solver = new Solver(neuralNet, validation, alpha, eta);
		solver.useBackPropStrategy(numIterations, targetOutput);
	}

	public void linearActivation() {
		activationFunction = new SigmoidActivationFunction();
	}

	public void findRosenbrockInputVector() {
		inputVector = new ArrayList<>();
		for (int i = 1; i <= numInputNeurons; i++) {
			inputVector.add(new Double(i));
		}
	}
}
