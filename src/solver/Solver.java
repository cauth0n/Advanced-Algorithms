package solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import neural_net.AbstractNeuralNetworkStructureFactory;
import neural_net.Layer;
import neural_net.Neuron;
import validation.DataPoint;
import validation.Validation;
import driver.MachineLearningModel;

/**
 * Class to solve whatever algorithm is thrown at is. Is effectively the client
 * of many strategy patterns.
 * 
 * @author cauthon
 */
public class Solver {

	private double alpha;
	private double eta;
	private MachineLearningModel machineLearningModel;
	private Validation validation;
	private MachineLearningAlgorithmStrategy solveStrategy;
	private StoppingCondition stoppingCondition;

	/**
	 * Constructor. All fields required for object instantiation
	 * 
	 * @param machineLearningModel
	 *            model of machine learning
	 * @param validation
	 *            method of validation
	 * @param alpha
	 *            whatever learning rate alpha may be
	 * @param eta
	 *            whatever learning rate eta may be
	 * @param stoppingCondition
	 *            how to determine when a training round is complete.
	 */
	public Solver(MachineLearningModel machineLearningModel, Validation validation, double alpha, double eta, StoppingCondition stoppingCondition) {
		this.machineLearningModel = machineLearningModel;
		this.validation = validation;
		this.alpha = alpha;
		this.eta = eta;
		this.stoppingCondition = stoppingCondition;
	}

	/**
	 * Use the backprop algorithm. Strategy pattern call here.
	 * 
	 * The key part of this method is the first line (solveStrategy = ...) This
	 * effectively sets the instance variable to a certain strategy, as defined
	 * by the method.
	 * 
	 * Handles iterations of back prop training and validation.
	 */
	public void useBackPropStrategy() {
		solveStrategy = new BackPropagationStrategy((AbstractNeuralNetworkStructureFactory) machineLearningModel.getModelStructure(), alpha, eta);
		// this cast is not pretty, but I don't know what else to do.

		validation.contructCrossValidationMethod();
		validation.normalizeOutputs();

		List<Long> timeStamps = new ArrayList<>();
		List<Double> err = new ArrayList<>();
		long startTime;
		long endTime;
		System.out.println("Initial structure for backprop: \n" + ((AbstractNeuralNetworkStructureFactory) machineLearningModel.getModelStructure()).toString());
		for (int i = 0; i < 10; i++) {
			double errorFromTrainingRounds = Double.MAX_VALUE;
			startTime = System.currentTimeMillis();
			stoppingCondition.reset();
			System.out.println("Fold " + i);
			for (int j = 0; j < 1000; j++) {
				// while (!stoppingCondition.isDone()) {
				errorFromTrainingRounds = train();
				stoppingCondition.postRoundOperation(errorFromTrainingRounds);
			}
			endTime = System.currentTimeMillis();
			timeStamps.add(endTime - startTime);
			err.add(validate());
		}
		System.out.println("Back Prop Times");
		for (Long l : timeStamps) {
			System.out.println(l + "");
		}
		System.out.println("\n Back Prop Error");
		for (Double d : err) {
			System.out.println(d + "");
		}
		System.out.println("\nFinal structure for backprop: \n" + ((AbstractNeuralNetworkStructureFactory) machineLearningModel.getModelStructure()).toString());
	}

	/**
	 * Method which defines how to train a specific neural network model.
	 * 
	 * Loops through all data points, gathering sum of normalized errors.
	 * 
	 * @return summed error across all data points.
	 */
	public double train() {
		double errorFromTrainRound = 0.0;
		for (DataPoint d : validation.getTrainingSet()) {
			errorFromTrainRound += solveStrategy.mainTrainingLoop(d);
		}
		errorFromTrainRound /= validation.getValidationSet().size();
		return errorFromTrainRound;
	}

	/**
	 * Method to validate how much error is produced from the runthrough of the
	 * validation set in the model
	 * 
	 * @return sum of normalized error.
	 */
	public double validate() {
		double output = 0.0;
		for (DataPoint d : validation.getValidationSet()) {
			output = solveStrategy.mainTestLoop(d);
			output += Math.abs(d.getNormalizedOutput() - output);
		}
		return output;
	}

	/**
	 * Method specific to radial basis neural networks. Places a number of
	 * centers at the Gaussian in each hidden layer, where the centers are
	 * randomly gathered from all the data points.
	 */
	public void assignCentersFromDataPool() {
		for (Layer l : ((AbstractNeuralNetworkStructureFactory) (machineLearningModel.getModelStructure())).getLayers()) {
			if (l.getLayerType().equals("RBFHIDDEN")) {
				for (Neuron n : l.getNeuronVector()) {
					List<Double> centers = getCentersFromRandomDataPoint();
					n.setActivationFunction(new GaussianBasis(centers));
				}
			}
		}
	}

	/**
	 * private method used for RBF nets. As the name suggests, it gets centers
	 * for a Gaussian from random data points.
	 * 
	 * @return vector corresponding to Gaussian center
	 */
	private List<Double> getCentersFromRandomDataPoint() {
		List<Double> toRet = new ArrayList<>();
		Random rand = new Random();

		int spot = rand.nextInt(validation.getTrainingSet().size());
		for (Double d : validation.getTrainingSet().get(spot).getInputValues()) {
			toRet.add(d);
		}

		return toRet;
	}

	/**
	 * Method to use the RBF strategy to solve RBF nets. Very similar to the
	 * backprop strategy.
	 * 
	 */
	public void useRadialBaseStrategy() {

		validation.contructCrossValidationMethod();
		validation.normalizeOutputs();
		assignCentersFromDataPool();
		solveStrategy = new RadialBasisStrategy((AbstractNeuralNetworkStructureFactory) machineLearningModel.getModelStructure(), alpha, eta);
		List<Long> timeStamps = new ArrayList<>();
		List<Double> err = new ArrayList<>();
		long startTime;
		long endTime;
		System.out.println("Initial structure for RBF: \n" + ((AbstractNeuralNetworkStructureFactory) machineLearningModel.getModelStructure()).toString());
		for (int i = 0; i < 10; i++) {
			double errorFromTrainingRounds = Double.MAX_VALUE;
			startTime = System.currentTimeMillis();
			stoppingCondition.reset();
			System.out.println("Fold " + i);
			while (!stoppingCondition.isDone()) {
				errorFromTrainingRounds = train();
				stoppingCondition.postRoundOperation(errorFromTrainingRounds);
			}
			endTime = System.currentTimeMillis();
			timeStamps.add(endTime - startTime);
			err.add(validate());
		}
		System.out.println("RBF Times");
		for (Long l : timeStamps) {
			System.out.println(l + "");
		}
		System.out.println("\n RBF Error");
		for (Double d : err) {
			System.out.println(d + "");
		}
		System.out.println("\nFinal structure for RBF: \n" + ((AbstractNeuralNetworkStructureFactory) machineLearningModel.getModelStructure()).toString());
	}
}
