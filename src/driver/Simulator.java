package driver;

import java.util.ArrayList;
import java.util.List;

import neural_net.NeuralNetworkModel;
import neural_net.NonRecurrentNeuralNetwork;
import neural_net.NonRecurrentRBFNeuralNetwork;
import solver.ActivationFunction;
import solver.ConvergenceStoppingConditionUsingLinearRegression;
import solver.SigmoidActivationFunction;
import solver.Solver;
import solver.StoppingCondition;
import validation.DataPointGenerator;
import validation.KFoldCrossValidation;
import validation.RosenbrockDataPointGenerator;
import validation.Validation;

/**
 * Simulator class. Effectively the heart of this framework. Holds all model
 * params, controls which model to build, how to generate data points, how to
 * validate...
 * 
 * I will need to refactor this once done with the first project.
 * 
 * @author cauth0n
 * 
 */
public class Simulator {

	public static double maxDist = -1 * Double.MAX_VALUE;
	private ActivationFunction activationFunction;
	private NeuralNetworkModel neuralNet;
	private Validation validation;
	private Solver solver;
	private StoppingCondition stoppingCondition;
	private List<Double> inputVector;
	private int numInputNeurons = 5;
	private int numOutputNeurons = 1;
	private int numHiddenLayers = 1;
	private int numNeuronsPerHiddenLayer = 100;
	private double eta = .4;
	private double alpha = 0;

	private int rosenbrockVectorSize = numInputNeurons;
	private double dataPointRange = 2;
	private int numDataPoints = 100;
	private int k = 10;
	private double stoppingConditionSlope = 0.00001;

	public Simulator() {
		getInitialInputVector();
		buildKFoldCrossValidation();
		//buildBackPropModel();

		buildRBFModel();
	}

	/**
	 * Build a k fold cross validation method.
	 */
	public void buildKFoldCrossValidation() {
		DataPointGenerator dataPointGenerator = getRosenbrockDataPointGenerator();
		validation = new KFoldCrossValidation(numDataPoints, dataPointGenerator, k);
		stoppingCondition = new ConvergenceStoppingConditionUsingLinearRegression(stoppingConditionSlope);
	}

	/**
	 * Initializes a new data point generator and returns it
	 * 
	 * @return
	 */
	public DataPointGenerator getRosenbrockDataPointGenerator() {
		DataPointGenerator dpg = new RosenbrockDataPointGenerator(rosenbrockVectorSize, dataPointRange);
		return dpg;
	}

	/**
	 * Method to build and train/test a rbf model.
	 */
	public void buildRBFModel() {
		sigmoidalActivation();
		neuralNet = new NonRecurrentRBFNeuralNetwork(activationFunction, numInputNeurons, inputVector, numOutputNeurons, numNeuronsPerHiddenLayer, dataPointRange);
		neuralNet.buildModelStructure();
		solver = new Solver(neuralNet, validation, alpha, eta, stoppingCondition);
		solver.useRadialBaseStrategy();
	}

	/**
	 * method to build and train/test a MLP NN
	 */
	public void buildBackPropModel() {
		sigmoidalActivation();
		neuralNet = new NonRecurrentNeuralNetwork(activationFunction, numInputNeurons, inputVector, numOutputNeurons, numHiddenLayers, numNeuronsPerHiddenLayer);
		neuralNet.buildModelStructure();
		solver = new Solver(neuralNet, validation, alpha, eta, stoppingCondition);
		solver.useBackPropStrategy();
	}

	/**
	 * Sets sigmoid activation to static var activationFunction
	 */
	public void sigmoidalActivation() {
		activationFunction = new SigmoidActivationFunction();
	}

	/**
	 * Initial input vector, just as a space holder
	 */
	public void getInitialInputVector() {
		inputVector = new ArrayList<>();
		for (int i = 1; i <= numInputNeurons; i++) {
			inputVector.add(new Double(0));
		}
	}
}
