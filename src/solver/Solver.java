package solver;

import java.io.FileNotFoundException;

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

		System.out.println("Final structure: ");
		System.out.print(machineLearningModel.getModelStructure().toString());

	}

	public void train() {
		int counter = 0;
		for (DataPoint d : validation.getTrainingSet()) {
			// System.out.println(d.toString());

			solveStrategy.mainTrainingLoop(d, stoppingCondition);
			System.out.println("Training " + counter);
			counter++;
		}
	}

	public void test() {
		for (DataPoint d : validation.getTestSet()) {
			double output = solveStrategy.mainTestLoop(d);
			System.out.println("Target: " + d.getTargetOutput() + " Neural net output: " + output + " Difference: " + (d.getTargetOutput() - output));
		}
	}

	public void useRadialBaseStrategy() {
		solveStrategy = new RadialBasisStrategy((NeuralNetworkStructure) machineLearningModel.getModelStructure(), alpha, eta);
		validation.contructCrossValidationMethod();
		train();
		test();
	}
}
