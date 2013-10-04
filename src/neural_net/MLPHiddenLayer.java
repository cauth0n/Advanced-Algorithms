package neural_net;

import java.util.List;

import solver.NeuronFunction;

/**
 * Class to define default hidden layers (not rbf hidden layers)
 * 
 * @author cauth0n
 * 
 */
public class MLPHiddenLayer extends HiddenLayer {

	/**
	 * 
	 * Constructor.
	 * 
	 * @param numNeurons
	 */
	public MLPHiddenLayer(List<NeuronFunction> neuronFunctionality, int numNeurons) {
		super(neuronFunctionality, numNeurons);
		layerType = "HIDDEN";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Method to build this layer. All incoming/outgoing connections are built
	 * 
	 * @see neural_net.Layer#buildLayer(java.util.List)
	 */
	@Override
	public void buildLayer(List<Neuron> downStreamNeurons) {

		for (int i = 0; i < numNeurons; i++) {
			neurons.add(new Neuron(neuronFunctionality.get(i), initialNodeValue));
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
