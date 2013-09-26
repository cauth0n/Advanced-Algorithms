package neural_net;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import solver.ActivationFunction;
import solver.GaussianBasis;

/**
 * @author cauthon
 */
public class NonRecurrentRBFNeuralNetwork extends NeuralNetworkModel {

	private int numNeuronsPerHiddenLayer;
	private double dataPointRange;

	public NonRecurrentRBFNeuralNetwork(ActivationFunction outputActivationFunction, int numInputNeurons, List<Double> inputVector,
			int numOutputNeurons, int numNeuronsPerHiddenLayer, double dataPointRange) {
		super(outputActivationFunction, numInputNeurons, inputVector, numOutputNeurons);
		this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
		this.dataPointRange = dataPointRange;
	}

	@Override
	public void buildModelStructure() {
		neuralNetworkStructure = new MultiLayeredPerceptron(numInputNeurons, inputVector, numOutputNeurons, 1, numNeuronsPerHiddenLayer);
		constructInputOutputLayers();
		addHiddenLayer();
		stitchLayersTogether();
		fixInputConnectionWeightsForRBF();
		buildBasisFunction();
	}

	public void addHiddenLayer() {
		neuralNetworkStructure.addHiddenLayer(1, new RBFHiddenLayer(numNeuronsPerHiddenLayer));
	}

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

	public void fixInputConnectionWeightsForRBF() {
		for (Connection c : neuralNetworkStructure.getLayers().get(0).getConnectionVector()) {
			c.setWeight(getRandomValueInRange());
		}
	}

	public void buildBasisFunction() {
		List<Double> centerVector = new ArrayList<>();
		for (Layer l : neuralNetworkStructure.getLayers()) {
			if (l.getLayerType().equals("RBFHIDDEN")) {
				for (Neuron n : l.getNeuronVector()) {
					centerVector.clear();
					for (Connection c : n.getIncomingConnectionsToThisNeuron()) {
						centerVector.add(c.getWeight());
					}
					n.setActivationFunction(new GaussianBasis(centerVector));
				}
			}
		}
	}
}
