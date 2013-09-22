package solver;

import neural_net.NeuralNetworkStructure;
import validation.DataPoint;
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
	private StoppingCondition stoppingCondition;

	public Solver(MachineLearningModel machineLearningModel, Validation validation, double alpha, double eta, StoppingCondition stoppingCondition) {
		this.machineLearningModel = machineLearningModel;
		this.validation = validation;
		this.alpha = alpha;
		this.eta = eta;
		this.stoppingCondition = stoppingCondition;
	}

	public void useBackPropStrategy() {
		solveStrategy = new BackPropagationStrategy((NeuralNetworkStructure) machineLearningModel.getModelStructure(), alpha, eta);
		// this cast is not pretty, but I don't know what else to do.

		validation.contructCrossValidationMethod();
		train();
		test();

	}

	public void train() {
		for (DataPoint d : validation.getTrainingSet()) {
			System.out.println(d.toString());
			solveStrategy.mainTrainingLoop(d, stoppingCondition);

		}
	}

	public void test() {
		for (DataPoint d : validation.getTestSet()) {
			double output = solveStrategy.mainTestLoop(d);
			System.out.println("Target: " + d.getTargetOutput() + " Neural net output: " + output + " Difference: " + (d.getTargetOutput() - output));
		}
	}

	public void useRadialBaseStrategy() {

	}

}
