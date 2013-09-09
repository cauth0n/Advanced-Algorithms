package neural_net;

public abstract class NeuralNetworkType implements NeuralNetwork {

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
