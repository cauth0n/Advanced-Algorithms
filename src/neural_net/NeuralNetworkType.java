package neural_net;

import java.util.List;

public abstract class NeuralNetworkType implements NeuralNetwork {

	public static final ActivationFunction activationFunction = new LinearActivationFunction();
	protected String neuralNetworkType;
	protected NeuralNetStructure neuralNetStructure;
	protected int numInputNeurons;
	protected int numConnectionsPerInputNeuron;
	protected int numOutputNeurons;
	protected List<Double> inputVector;

	public NeuralNetworkType(String neuralNetworkType, int numInputNeurons, int numConnectionsPerInputNeuron, List<Double> inputVector, int numOutputNeurons) {
		this.neuralNetworkType = neuralNetworkType;
		this.numInputNeurons = numInputNeurons;
		this.numConnectionsPerInputNeuron = numConnectionsPerInputNeuron;
		this.inputVector = inputVector;
		this.numOutputNeurons = numOutputNeurons;
	}

	public NeuralNetStructure getNetStructure() {
		return neuralNetStructure;
	}

}
