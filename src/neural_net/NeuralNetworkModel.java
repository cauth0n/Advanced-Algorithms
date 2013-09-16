package neural_net;

import java.util.List;

import solver.ActivationFunction;
import driver.MachineLearningModel;
import driver.MachineLearningModelStructure;

public abstract class NeuralNetworkModel implements MachineLearningModel {

	public static ActivationFunction activationFunction;

	protected NeuralNetworkStructure neuralNetworkStructure;
	protected int numInputNeurons;
	protected List<Double> inputVector;
	protected int numOutputNeurons;
	protected int numHiddenLayers;
	protected int numNeuronsPerHiddenLayer;

	public NeuralNetworkModel(ActivationFunction activationFunction, int numInputNeurons, List<Double> inputVector, int numOutputNeurons,
			int numHiddenLayers, int numNeuronsPerHiddenLayer) {
		NeuralNetworkModel.activationFunction = activationFunction;
		this.numInputNeurons = numInputNeurons;
		this.inputVector = inputVector;
		this.numOutputNeurons = numOutputNeurons;
		this.numHiddenLayers = numHiddenLayers;
		this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
	}

	public abstract String toString();

	public MachineLearningModelStructure getModelStructure() {
		return neuralNetworkStructure;
	}

	public abstract void constructInputOutputLayers();

}
