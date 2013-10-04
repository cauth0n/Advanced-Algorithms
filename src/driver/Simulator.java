package driver;

import neural_net.AbstractNeuralNetworkModelFactory;
import neural_net.MLPModelFactory;
import neural_net.NeuralNetworkStructuralInfo;
import neural_net.RBFModelFactory;
import solver.ConvergenceStoppingConditionUsingLinearRegression;
import solver.NeuronFunction;
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
	private AbstractNeuralNetworkModelFactory neuralNet;
	private Validation validation;
	private Solver solver;
	private StoppingCondition stoppingCondition;
	private int numInputNeurons = 5;
	private int numOutputNeurons = 1;
	private int numHiddenLayers = 1;
	private int numHiddenLayerNeurons = 100;
	private double eta = .4;
	private double alpha = 0;

	private int rosenbrockVectorSize = numInputNeurons;
	private double dataPointRange = 2;
	private int numDataPoints = 100;
	private int k = 10;
	private double stoppingConditionSlope = 0.00001;

	public Simulator() {

		buildKFoldCrossValidation();
		buildMLPModel(getStructuralInfo());

		// buildRBFModel(getStructuralInfo());
	}

	public NeuralNetworkStructuralInfo getStructuralInfo() {
		return new NeuralNetworkStructuralInfo(numInputNeurons, numHiddenLayerNeurons, numHiddenLayers, numOutputNeurons);
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
	public void buildRBFModel(NeuralNetworkStructuralInfo structuralInfo) {
		neuralNet = new RBFModelFactory(structuralInfo);
		neuralNet.buildModelStructure();
		solver = new Solver(neuralNet, validation, alpha, eta, stoppingCondition);
		solver.useRadialBaseStrategy();
	}

	/**
	 * method to build and train/test a MLP NN
	 */
	public void buildMLPModel(NeuralNetworkStructuralInfo structuralInfo) {
		neuralNet = new MLPModelFactory(structuralInfo);
		neuralNet.buildModelStructure();
		solver = new Solver(neuralNet, validation, alpha, eta, stoppingCondition);
		solver.useBackPropStrategy();
	}

}
