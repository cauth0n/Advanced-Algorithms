package neural_net;

import java.util.List;

import solver.NeuronFunction;

/**
 * Abstract class to define what a hidden layer shoudl look like.
 * 
 * This class may be unnecessary.
 * 
 * @author cauthon
 */
public abstract class HiddenLayer extends Layer {

	/**
	 * Constructor
	 * 
	 * @param numNeurons
	 */
	public HiddenLayer(List<NeuronFunction> neuronFunctionality, int numNeurons) {
		super(neuronFunctionality, numNeurons);
	}

}
