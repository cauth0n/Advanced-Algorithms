package neural_net;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import solver.ActivationFunction;
import solver.GaussianBasis;

/**
 * Class for representing non recurrent rbf networks
 * 
 * @author cauthon
 */
public class NonRecurrentRBFNeuralNetwork extends NeuralNetworkModel {

	private int numNeuronsPerHiddenLayer;
	private double dataPointRange;

	/**
	 * 
	 * Constructor
	 * 
	 * @param outputActivationFunction
	 * @param numInputNeurons
	 * @param inputVector
	 * @param numOutputNeurons
	 * @param numNeuronsPerHiddenLayer
	 * @param dataPointRange
	 */
	public NonRecurrentRBFNeuralNetwork(ActivationFunction outputActivationFunction, int numInputNeurons, List<Double> inputVector, int numOutputNeurons, int numNeuronsPerHiddenLayer, double dataPointRange) {
		super(outputActivationFunction, numInputNeurons, inputVector, numOutputNeurons);
		this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
		this.dataPointRange = dataPointRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Builds the structure of the model
	 * 
	 * @see driver.MachineLearningModel#buildModelStructure()
	 */
	@Override
	public void buildModelStructure() {
		neuralNetworkStructure = new MultiLayeredPerceptron(numInputNeurons, inputVector, numOutputNeurons, 1, numNeuronsPerHiddenLayer);
		constructInputOutputLayers();
		addHiddenLayer();
		stitchLayersTogether();
		fixInputConnectionWeightsForRBF();
		buildBasisFunction();
	}

	/**
	 * Adds a single hidden layer to the network
	 */
	public void addHiddenLayer() {
		neuralNetworkStructure.addHiddenLayer(1, new RBFHiddenLayer(numNeuronsPerHiddenLayer));
	}

	/**
	 * 
	 * Returns a random value in the range of data points
	 * 
	 * @return rand value
	 */
	public double getRandomValueInRange() {
		Random rand = new Random();
		double returnValue = 0.0;
		if (rand.nextBoolean()) {
			returnValue = dataPointRange * rand.nextDouble();
		} else {
			returnValue = -1 * dataPointRange * rand.nextDouble();
		}

		return returnValue;
	}

	/**
	 * Builds connection inputs for a rbf network
	 */
	public void fixInputConnectionWeightsForRBF() {
		for (Connection c : neuralNetworkStructure.getLayers().get(0).getConnectionVector()) {
			c.setWeight(getRandomValueInRange());
		}
	}

	/**
	 * Construct basis function in hidden layers. I believe this is getting
	 * Overridden later.
	 */
	public void buildBasisFunction() {
		List<Double> centerVector;
		for (Layer l : neuralNetworkStructure.getLayers()) {
			if (l.getLayerType().equals("RBFHIDDEN")) {
				for (Neuron n : l.getNeuronVector()) {
					centerVector = new ArrayList<>();
					for (Connection c : n.getIncomingConnectionsToThisNeuron()) {
						centerVector.add(c.getWeight());
					}
					n.setActivationFunction(new GaussianBasis(centerVector));
				}
			}
		}
	}
}
