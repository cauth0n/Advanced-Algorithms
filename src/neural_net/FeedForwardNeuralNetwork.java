package neural_net;

public class FeedForwardNeuralNetwork extends NeuralNetworkType {

	private int numHiddenLayers;
	private int numConnectionsPerHiddenNeuron;
	private int numNeuronsPerHiddenLayer;

	public FeedForwardNeuralNetwork(int numInputNeurons, int numConnectionsPerInputNeuron, int numOutputNeurons, int numHiddenLayers, int numNeuronsPerHiddenLayer, int numConnectionsPerHiddenNeuron) {
		super(numInputNeurons, numConnectionsPerInputNeuron, numOutputNeurons);
		this.numHiddenLayers = numHiddenLayers;
		this.numConnectionsPerHiddenNeuron = numConnectionsPerHiddenNeuron;
		this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
	}

	@Override
	public void buildNetworkStructure() {
		neuralNetStructure = new MultiLayeredStructure(numInputNeurons, numConnectionsPerInputNeuron, numOutputNeurons);
		neuralNetStructure.constructInputOutputLayers();
		for (int i = 1; i < numHiddenLayers + 1; i++) {
			((MultiLayeredStructure) neuralNetStructure).addHiddenLayer(i, numNeuronsPerHiddenLayer, numConnectionsPerHiddenNeuron);
		}
		neuralNetStructure.stitchLayersTogether();
	}

	@Override
	public NeuralNetworkType getNetworkType() {
		return this;
	}

	@Override
	public void validateStructuralParameters() {

	}

}
