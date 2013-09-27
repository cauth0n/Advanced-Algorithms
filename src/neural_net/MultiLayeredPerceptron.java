package neural_net;

import java.util.List;

public class MultiLayeredPerceptron extends NeuralNetworkStructure {

	public MultiLayeredPerceptron(int numInputNeurons, List<Double> inputVector, int numOutputNeurons, int numHiddenLayers, int numHiddenNeuronsPerLayer) {
		super(numInputNeurons, inputVector, numOutputNeurons);
	}
}
