package neural_net;

import java.util.List;

/**
 * @author cauthon
 */
public class RBFHiddenLayer extends HiddenLayer {

	public RBFHiddenLayer(int numNeurons) {
		super(numNeurons);
		layerType = "RBFHIDDEN";
	}

	@Override
	public void buildLayer(List<Neuron> downStreamNeurons) {
		for (int i = 0; i < numNeurons; i++) {
			neurons.add(new Neuron(null, initialNodeValue));
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
				if (c.getToNeuron().equals(n)) {
					n.addIncomingConnectionToThisNeuron(c);
				}
			}
		}
	}
}
