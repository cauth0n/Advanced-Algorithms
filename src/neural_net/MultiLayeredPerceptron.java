package neural_net;

import java.util.List;

public class MultiLayeredPerceptron extends NeuralNetworkStructure {

	private int numHiddenLayers;
	private int numHiddenNeuronsPerLayer;

	public MultiLayeredPerceptron(int numInputNeurons, List<Double> inputVector, int numOutputNeurons, int numHiddenLayers,
			int numHiddenNeuronsPerLayer) {
		super(numInputNeurons, inputVector, numOutputNeurons);
		this.numHiddenLayers = numHiddenLayers;
		this.numHiddenNeuronsPerLayer = numHiddenNeuronsPerLayer;

	}


}
