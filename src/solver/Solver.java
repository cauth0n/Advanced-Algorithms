package solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import neural_net.AbstractNeuralNetworkStructureFactory;
import neural_net.Layer;
import neural_net.Neuron;
import validation.ClassificationDataPoint;
import validation.DataPoint;
import validation.KFoldCrossValidation;
import validation.LetterRecognitionDataPoint;
import validation.Validation;
import driver.Inputter;
import driver.Simulator;

/**
 * Class to solve whatever algorithm is thrown at is. Is effectively the client
 * of many strategy patterns.
 * 
 * @author cauthon
 */
public class Solver {

	private TrainingMethod trainingMethod;
	private TestingMethod testingMethod;
	private Validation validation;
	private Inputter inputter;
	private List<DataPoint> allDataPoints;

	private StoppingCondition stoppingCondition;

	private double alpha;
	private double eta;

	public Solver() {

	}

	public void setTrainingMethod(TrainingMethod trainingMethod) {
		this.trainingMethod = trainingMethod;
	}

	public void setTestingMethod(TestingMethod testingMethod) {
		this.testingMethod = testingMethod;
	}

	public void inputLetterRecognizer() {
		inputter = new Inputter(Simulator.letterRecognition);
		inputter.input();
		allDataPoints = new ArrayList<>();
		for (String s : inputter.getData()) {
			LetterRecognitionDataPoint ldp = new LetterRecognitionDataPoint();
			ldp.format(s);
			allDataPoints.add(ldp);
		}
	}

	public void useValidationMethod() {
		int k = Simulator.k;
		validation = new KFoldCrossValidation(allDataPoints.size(), k);
		validation.divideDataPoints(allDataPoints);
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				// TODO
			}
		}
	}

	public void train() {

	}

	public void test() {

	}

	/**
	 * Method specific to radial basis neural networks. Places a number of
	 * centers at the Gaussian in each hidden layer, where the centers are
	 * randomly gathered from all the data points.
	 */
	public void assignCentersFromDataPool() {
		for (Layer l : ((AbstractNeuralNetworkStructureFactory) (machineLearningModel
				.getModelStructure())).getLayers()) {
			if (l.getLayerType().equals("RBFHIDDEN")) {
				for (Neuron n : l.getNeuronVector()) {
					List<Double> centers = getCentersFromRandomDataPoint();
					n.setActivationFunction(new GaussianFunction(centers));
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

		validation.divideDataPoints();
		validation.normalizeOutputs();
		assignCentersFromDataPool();
		solveStrategy = new RadialBasisTraining(
				(AbstractNeuralNetworkStructureFactory) machineLearningModel
						.getModelStructure(),
				alpha, eta);
		List<Long> timeStamps = new ArrayList<>();
		List<Double> err = new ArrayList<>();
		long startTime;
		long endTime;
		System.out.println("Initial structure for RBF: \n"
				+ ((AbstractNeuralNetworkStructureFactory) machineLearningModel
						.getModelStructure()).toString());
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
		System.out.println("\nFinal structure for RBF: \n"
				+ ((AbstractNeuralNetworkStructureFactory) machineLearningModel
						.getModelStructure()).toString());
	}
}
