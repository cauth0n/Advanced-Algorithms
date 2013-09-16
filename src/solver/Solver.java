package solver;

import neural_net.NeuralNetworkStructure;
import validation.Validation;
import driver.MachineLearningModel;

/**
 * @author cauthon
 */
public class Solver {

	private MachineLearningModel machineLearningModel;
	private Validation validation;
	private MachineLearningAlgorithmStrategy solveStrategy;

	public Solver(MachineLearningModel machineLearningModel, Validation validation) {
		this.machineLearningModel = machineLearningModel;
		this.validation = validation;
	}

	public void useBackPropStrategy(int numIterations, double targetValue) {
		solveStrategy = new BackPropagationStrategy((NeuralNetworkStructure) machineLearningModel.getModelStructure());
		solveStrategy.mainLoop(numIterations, targetValue);
		// this cast is not pretty, but I don't know what else to do.
	}

	public void useRadialBaseStrategy() {

	}

}
