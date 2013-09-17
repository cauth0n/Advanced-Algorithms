package solver;

import neural_net.NeuralNetworkStructure;
import validation.Validation;
import driver.MachineLearningModel;

/**
 * @author cauthon
 */
public class Solver {

	private double alpha;
	private double eta;
	private MachineLearningModel machineLearningModel;
	private Validation validation;
	private MachineLearningAlgorithmStrategy solveStrategy;

	public Solver(MachineLearningModel machineLearningModel, Validation validation, double alpha, double eta) {
		this.machineLearningModel = machineLearningModel;
		this.validation = validation;
		this.alpha = alpha;
		this.eta = eta;
	}

	public void useBackPropStrategy(int numIterations, double targetValue) {
		solveStrategy = new BackPropagationStrategy((NeuralNetworkStructure) machineLearningModel.getModelStructure(), alpha, eta);
		// this cast is not pretty, but I don't know what else to do.
		solveStrategy.mainLoop(numIterations, targetValue);
	}

	public void useRadialBaseStrategy() {

	}

}
