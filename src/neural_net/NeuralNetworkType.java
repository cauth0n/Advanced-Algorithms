package neural_net;

public abstract class NeuralNetworkType implements NeuralNetwork {

	public static final NeuronFunction activationFunction = new LinearActivationFunction();
	public static final NeuronFunction dataFunction = new DataFunction();
	protected NeuralNetStructure neuralNetStructure;
	protected int numInputNeurons;
	protected int numConnectionsPerInputNeuron;
	protected int numOutputNeurons;

	public NeuralNetworkType(int numInputNeurons, int numConnectionsPerInputNeuron, int numOutputNeurons) {

		this.numInputNeurons = numInputNeurons;
		this.numConnectionsPerInputNeuron = numConnectionsPerInputNeuron;
		this.numOutputNeurons = numOutputNeurons;
	}

	public NeuralNetStructure getNetStructure() {
		return neuralNetStructure;
	}

	public abstract void validateStructuralParameters();

}
