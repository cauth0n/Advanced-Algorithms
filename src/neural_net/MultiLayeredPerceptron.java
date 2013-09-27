package neural_net;

import java.util.List;

/**
 * MLP class, holding everything a perceptron does, but with a numhidden layers
 * variable.
 * 
 * 
 * I may need to add more to this class as I get further.
 * 
 * @author cauth0n
 * 
 * 
 * 
 */
public class MultiLayeredPerceptron extends NeuralNetworkStructure {

	/**
	 * Constructor.
	 * 
	 * @param numInputNeurons
	 * @param inputVector
	 * @param numOutputNeurons
	 * @param numHiddenLayers
	 * @param numHiddenNeuronsPerLayer
	 */
	public MultiLayeredPerceptron(int numInputNeurons, List<Double> inputVector, int numOutputNeurons, int numHiddenLayers, int numHiddenNeuronsPerLayer) {
		super(numInputNeurons, inputVector, numOutputNeurons);
	}
}
