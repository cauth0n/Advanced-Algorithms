package neural_net;

import java.util.List;

import driver.Simulator;

public class OutputLayer extends Layer {

	/***
	 * Output layer constructor. I use numNeurons as the third parameter in the
	 * super call because the numOutgoingConnections in the abstract Layer class
	 * should be equal to the number of neurons, for ONLY the output layer.
	 * 
	 * Incidentally, this will be where I make my first call when constructing
	 * my network.
	 * 
	 * @param numNeurons
	 *            number of neurons at this layer.
	 */
	public OutputLayer(int numNeurons) {
		super(numNeurons, numNeurons);
		layerType = "OUTPUT";
	}

	@Override
	public void buildLayer(List<Neuron> upStreamNeurons) {
		for (int i = 0; i < numNeurons; i++) {
			neurons.add(new ActivatingNeuron((ActivationFunction) NeuralNetworkType.activationFunction));
			connections.add(new Connection(neurons.get(i), null, initialWeight));
		}
	}
}
