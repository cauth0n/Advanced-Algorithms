package solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import neural_net.Layer;
import neural_net.NeuralNetworkStructure;
import neural_net.Neuron;
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
		validation.normalizeOutputs();

		double errorFromTrainingRounds = Double.MAX_VALUE;
		stoppingCondition.reset();

		while (!stoppingCondition.isDone()) {
			errorFromTrainingRounds = train();
			stoppingCondition.postRoundOperation(errorFromTrainingRounds);
		}

		System.out.println("Final structure: ");
		System.out.print(machineLearningModel.getModelStructure().toString());

	}

	public double train() {
		double errorFromTrainRound = 0.0;
		for (DataPoint d : validation.getTrainingSet()) {
			errorFromTrainRound += solveStrategy.mainTrainingLoop(d);

		}
		errorFromTrainRound /= validation.getTestSet().size();
		return errorFromTrainRound;
	}

	public double test() {
		double output = 0.0;
		for (DataPoint d : validation.getTestSet()) {

			System.out.println(d.toString());
			output = solveStrategy.mainTestLoop(d);
			output = d.getNormalizedOutput() - output;
		}
		return output;
	}

	public void assignCentersFromDataPool() {
		for (Layer l : ((NeuralNetworkStructure) (machineLearningModel.getModelStructure())).getLayers()) {
			if (l.getLayerType().equals("RBFHIDDEN")) {
				for (Neuron n : l.getNeuronVector()) {
					List<Double> centers = getCentersFromRandomDataPoint();
					n.setActivationFunction(new GaussianBasis(centers, l.getNeuronVector().size()));
				}
			}
		}
	}

	public List<Double> getCentersFromRandomDataPoint() {
		List<Double> toRet = new ArrayList<>();
		Random rand = new Random();

		int spot = rand.nextInt(validation.getTrainingSet().size());
		for (Double d : validation.getTrainingSet().get(spot).getInputValues()) {
			toRet.add(d);
		}

		return toRet;
	}

	public void useRadialBaseStrategy() {

		validation.contructCrossValidationMethod();
		validation.normalizeOutputs();
		assignCentersFromDataPool();
		solveStrategy = new RadialBasisStrategy((NeuralNetworkStructure) machineLearningModel.getModelStructure(), alpha, eta, validation.getTrainingSet());

		double errorFromTrainingRounds = Double.MAX_VALUE;
		stoppingCondition.reset();

		while (!stoppingCondition.isDone()) {
			errorFromTrainingRounds = train();
			stoppingCondition.postRoundOperation(errorFromTrainingRounds);
		}

		// test();
	}
}
