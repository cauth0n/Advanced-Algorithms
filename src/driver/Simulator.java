package driver;

import java.util.ArrayList;
import java.util.List;

import neural_net.NeuralNetworkModel;
import neural_net.NonRecurrentNeuralNetwork;
import neural_net.NonRecurrentRBFNeuralNetwork;
import solver.ActivationFunction;
import solver.ConvergenceStoppingConditionUsingLinearRegression;
import solver.LinearActivationFunction;
import solver.SigmoidActivationFunction;
import solver.Solver;
import solver.StoppingCondition;
import validation.DataPointGenerator;
import validation.KFoldCrossValidation;
import validation.RosenbrockDataPointGenerator;
import validation.Validation;

public class Simulator {

	private ActivationFunction activationFunction;
	private NeuralNetworkModel neuralNet;
	private Validation validation;
	private Solver solver;
	private StoppingCondition stoppingCondition;
	private List<Double> inputVector;
	private int numInputNeurons = 2;
	private int numOutputNeurons = 1;
	private int numHiddenLayers = 2;
	private int numNeuronsPerHiddenLayer = 4;
	private double eta = .1;
	private double alpha = 0;

	private int rosenbrockVectorSize = numInputNeurons;
	private double dataPointRange = 1;
	private int numDataPoints = 100;
	private int k = 10;
	private double stoppingEpsilon = 0.0001;

	public Simulator() {
		getInitialInputVector();
		buildKFoldCrossValidation();
		// buildBackPropModel();

		buildRBFModel();

	}

	public void buildKFoldCrossValidation() {
		DataPointGenerator dataPointGenerator = getRosenbrockDataPointGenerator();
		validation = new KFoldCrossValidation(numDataPoints, dataPointGenerator, k);
		stoppingCondition = new ConvergenceStoppingConditionUsingLinearRegression(stoppingEpsilon);
	}

	public DataPointGenerator getRosenbrockDataPointGenerator() {
		DataPointGenerator dpg = new RosenbrockDataPointGenerator(rosenbrockVectorSize, dataPointRange);
		return dpg;
	}

	public void buildRBFModel() {
		sigmoidalActivation();
		neuralNet = new NonRecurrentRBFNeuralNetwork(activationFunction, numInputNeurons, inputVector, numOutputNeurons, numNeuronsPerHiddenLayer, dataPointRange);
		neuralNet.buildModelStructure();
		solver = new Solver(neuralNet, validation, alpha, eta, stoppingCondition);
		solver.useRadialBaseStrategy();
	}

	public void buildBackPropModel() {
		sigmoidalActivation();
		neuralNet = new NonRecurrentNeuralNetwork(activationFunction, numInputNeurons, inputVector, numOutputNeurons, numHiddenLayers, numNeuronsPerHiddenLayer);
		neuralNet.buildModelStructure();
		solver = new Solver(neuralNet, validation, alpha, eta, stoppingCondition);
		solver.useBackPropStrategy();
	}

	public void linearActivation() {
		activationFunction = new LinearActivationFunction();
	}

	public void sigmoidalActivation() {
		activationFunction = new SigmoidActivationFunction();
	}

	public void getInitialInputVector() {
		inputVector = new ArrayList<>();
		for (int i = 1; i <= numInputNeurons; i++) {
			inputVector.add(new Double(0));
		}
	}
}
