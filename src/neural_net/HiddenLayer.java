package neural_net;

import java.util.List;

public class HiddenLayer extends Layer {

	public HiddenLayer(int numNeurons, int numOutgoingConnectionsPerNeuron) {
		super(numNeurons, numOutgoingConnectionsPerNeuron);
		layerType = "HIDDEN";
	}

	public List<Connection> getConnections() {
		return connections;
	}

	@Override
	public void buildLayer(List<Neuron> downStreamNeurons) {

		for (int i = 0; i < numNeurons; i++) {
			neurons.add(new ActivatingNeuron((ActivationFunction) NeuralNetworkType.activationFunction));
		}
		for (int i = 0; i < neurons.size(); i++) {

			int numRemainingOutgoingConnectionsPerNeuron = numOutgoingConnectionsPerNeuron;
			int numRemainingDownStreamNeurons = downStreamNeurons.size() - 1;

			while (numRemainingOutgoingConnectionsPerNeuron >= 0 && numRemainingDownStreamNeurons >= 0) {
				Connection c = new Connection(neurons.get(i), downStreamNeurons.get(numRemainingDownStreamNeurons), initialWeight);
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

	@Override
	public void feedForward() {
		for ()
	}
}
