package neural_net;

import java.util.List;

import driver.Simulator;

public class InputLayer extends Layer {

	public InputLayer(String layerType, int numNeurons) {
		super(layerType, numNeurons);
	}

	/***
	 * Builds a single input layer in the neural network.
	 * 
	 * Uses null in input layer, because no neurons are upstream.
	 * 
	 * The use of null will probably have to be changed later, so that I can put
	 * in or glean information from these layers.
	 * 
	 * @param upStreamNeurons
	 *            this parameter will be null in the input case. I am leaving it
	 *            here because this method is being overwritten, and I want to
	 *            make sure it is overwritten.
	 * 
	 */
	public void buildLayer(List<Neuron> upStreamNeurons) {
		// upStreamNeurons is null in this case.
		for (int i = 0; i < neurons.size(); i++) {
			neurons.add(new DataNeuron(Simulator.activationFunction));
		}
	}

}
