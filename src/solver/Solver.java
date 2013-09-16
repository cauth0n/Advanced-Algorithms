package solver;

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

/*	public void useBackPropStrategy() {
		solveStrategy = new BackPropagationStrategy(machineLearningModel.getModelStructure());
	}*/

	public void useRadialBaseStrategy() {

	}

}
