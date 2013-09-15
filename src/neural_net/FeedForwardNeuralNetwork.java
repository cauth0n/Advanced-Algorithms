package neural_net;

import java.util.List;

public class FeedForwardNeuralNetwork extends NeuralNetworkType {

	private int numHiddenLayers;
	private int numConnectionsPerHiddenNeuron;
	private int numNeuronsPerHiddenLayer;

	public FeedForwardNeuralNetwork(String neuralNetworkType, int numInputNeurons, int numConnectionsPerInputNeuron, int numOutputNeurons, int numHiddenLayers, int numNeuronsPerHiddenLayer, int numConnectionsPerHiddenNeuron, List<Double> inputVector) {
		super(neuralNetworkType, numInputNeurons, numConnectionsPerInputNeuron, inputVector, numOutputNeurons);
		this.numHiddenLayers = numHiddenLayers;
		this.numConnectionsPerHiddenNeuron = numConnectionsPerHiddenNeuron;
		this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
	}

	@Override
	public void buildNetworkStructure() {
		neuralNetStructure = new MultiLayeredStructure(numInputNeurons, numConnectionsPerInputNeuron, inputVector, numOutputNeurons);
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
	public void feedForward() {
		for (Layer l : neuralNetStructure.getLayers()) {
			switch (l.getLayerType()) {
			case "INPUT":// do nothing
				break;
			case "HIDDEN":
			case "OUTPUT":
				for (Neuron n : l.getNeuronVector()) {
					int newNeuronValue = 0;
					for (Connection prevConnection : n.getIncomingConnectionsToThisNeuron()) {
						newNeuronValue += prevConnection.getFromNeuron().getNeuronValue() * prevConnection.getWeight();
						// w*j part
					}
					n.setNodeValue(n.activate(newNeuronValue));
				}
				break;
			}
		}
	}

	@Override
	public void solve(double targetOutput) {
		for (Layer l : neuralNetStructure.getLayers()) {
			switch (l.getLayerType()) {
			case "OUTPUT":
				for (Neuron n : l.getNeuronVector()) {
					double diff = targetOutput - n.getNeuronValue(); 
				}
				break;
			}
		}
	}

}
