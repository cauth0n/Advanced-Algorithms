package neural_net;

import java.util.List;

import solver.ActivationFunction;

/**
 * Class to represent a nonrecurrent NN
 * 
 * @author cauthon
 */
public class NonRecurrentNeuralNetwork extends NeuralNetworkModel {
	private int numHiddenLayers;
	private int numNeuronsPerHiddenLayer;

	/**
	 * Constructor
	 * 
	 * @param activationFunction
	 * @param numInputNeurons
	 * @param inputVector
	 * @param numOutputNeurons
	 * @param numHiddenLayers
	 * @param numNeuronsPerHiddenLayer
	 */
	public NonRecurrentNeuralNetwork(ActivationFunction activationFunction, int numInputNeurons, List<Double> inputVector, int numOutputNeurons, int numHiddenLayers, int numNeuronsPerHiddenLayer) {
		super(activationFunction, numInputNeurons, inputVector, numOutputNeurons);
		this.numHiddenLayers = numHiddenLayers;
		this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Builds the model's structure
	 * 
	 * @see driver.MachineLearningModel#buildModelStructure()
	 */
	@Override
	public void buildModelStructure() {
		neuralNetworkStructure = new MultiLayeredPerceptron(numInputNeurons, inputVector, numOutputNeurons, numHiddenLayers, numNeuronsPerHiddenLayer);
		constructInputOutputLayers();
		addHiddenLayers();
		stitchLayersTogether();
	}

	/**
	 * Adds all the hidden layers to the model
	 * 
	 */
	public void addHiddenLayers() {
		for (int i = 1; i < numHiddenLayers + 1; i++) {
			neuralNetworkStructure.addHiddenLayer(i, new MLPHiddenLayer(numNeuronsPerHiddenLayer));
		}
	}

}
