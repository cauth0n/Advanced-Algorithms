package neural_net;

import java.util.List;

import solver.NeuronFunction;

/**
 * Input layer class. Holds all information held by the input layer
 * 
 * @author cauth0n
 * 
 */
public class InputLayer extends Layer {

	public InputLayer(List<NeuronFunction> functionForNeuronsInThisLayer, int numInputNeurons) {
		super(functionForNeuronsInThisLayer, numInputNeurons);
		layerType = "INPUT";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Build layer method. For the input layer, the input vector is placed in
	 * the neurons.
	 * 
	 * @see neural_net.Layer#buildLayer(java.util.List)
	 */
	@Override
	public void buildLayer(List<Neuron> downStreamNeurons) {
		for (int i = 0; i < numNeurons; i++) {
			Neuron n = new Neuron(neuronFunctionality.get(i), 0);
			neurons.add(n);
		}

		for (int i = 0; i < neurons.size(); i++) {
			int numRemainingOutgoingConnectionsPerNeuron = downStreamNeurons.size();
			int numRemainingDownStreamNeurons = downStreamNeurons.size() - 1;

			while (numRemainingOutgoingConnectionsPerNeuron >= 0 && numRemainingDownStreamNeurons >= 0) {
				Connection c = new Connection(neurons.get(i), downStreamNeurons.get(numRemainingDownStreamNeurons), getRandomWeight());
				connections.add(c);
				neurons.get(i).addOutgoingConnectionFromThisNeuron(c);
				numRemainingOutgoingConnectionsPerNeuron--;
				numRemainingDownStreamNeurons--;
			}
		}
		for (Neuron n : downStreamNeurons) {
			for (Connection c : connections) {
				if (c.getToNeuron() == n) {
					n.addIncomingConnectionToThisNeuron(c);
				}
			}
		}
	}
}
