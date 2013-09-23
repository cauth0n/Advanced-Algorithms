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

		double errorFromTrainingRounds = Double.MAX_VALUE;
		stoppingCondition.reset();
		stoppingCondition.postRoundOperation(errorFromTrainingRounds);

		int counter = 0;
		double min = 9999, max = 0.0;
		while (!stoppingCondition.isDone()) {
			errorFromTrainingRounds = train();
			// System.out.println(errorFromTrainingRounds);
			min = Math.min(min, errorFromTrainingRounds);
			max = Math.max(max, errorFromTrainingRounds);
			System.out.println("Max error: " + max + "   Min error: " + min);
			counter++;
			stoppingCondition.postRoundOperation(errorFromTrainingRounds);
		}

		System.out.println("Final structure: ");
		System.out.print(machineLearningModel.getModelStructure().toString());

	}

	public double train() {
		double errorFromTrainRound = 0.0;
		for (DataPoint d : validation.getTrainingSet()) {
			errorFromTrainRound += solveStrategy.mainTrainingLoop(d, stoppingCondition);
		}
		errorFromTrainRound /= validation.getTestSet().size();
		return errorFromTrainRound;
	}

	public double test() {
		double output = 0.0;
		for (DataPoint d : validation.getTestSet()) {

			output = solveStrategy.mainTestLoop(d);
			output = d.getTargetOutput() - output;
		}
		return output;
	}

	public void useRadialBaseStrategy() {
		solveStrategy = new RadialBasisStrategy((NeuralNetworkStructure) machineLearningModel.getModelStructure(), alpha, eta);
		validation.contructCrossValidationMethod();
		train();
		test();
	}
}
