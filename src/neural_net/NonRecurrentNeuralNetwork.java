package neural_net;

import java.util.List;

import solver.ActivationFunction;

/**
 * @author cauthon
 */
public class NonRecurrentNeuralNetwork extends NeuralNetworkModel {
	private int numHiddenLayers;
	private int numNeuronsPerHiddenLayer;

	public NonRecurrentNeuralNetwork(ActivationFunction activationFunction, int numInputNeurons, List<Double> inputVector, int numOutputNeurons,
			int numHiddenLayers, int numNeuronsPerHiddenLayer) {
		super(activationFunction, numInputNeurons, inputVector, numOutputNeurons);
		this.numHiddenLayers = numHiddenLayers;
		this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
	}

	@Override
	public void buildModelStructure() {
		neuralNetworkStructure = new MultiLayeredPerceptron(numInputNeurons, inputVector, numOutputNeurons, numHiddenLayers, numNeuronsPerHiddenLayer);
		constructInputOutputLayers();
		addHiddenLayers();
		stitchLayersTogether();
	}

	public void addHiddenLayers() {
		for (int i = 1; i < numHiddenLayers + 1; i++) {
			neuralNetworkStructure.addHiddenLayer(i, new MLPHiddenLayer(numNeuronsPerHiddenLayer));
		}
	}

}
