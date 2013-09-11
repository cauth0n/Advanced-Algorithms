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

				connections.add(new Connection(neurons.get(i), downStreamNeurons.get(numRemainingDownStreamNeurons), initialWeight));
				numRemainingOutgoingConnectionsPerNeuron--;
				numRemainingDownStreamNeurons--;
			}

			// for (int j = 0; j < numOutgoingConnectionsPerNeuron; j++) {
			// for (int l = 0; l < downStreamNeurons.size(); l++) {
			// connections.add(new Connection(neurons.get(i),
			// downStreamNeurons.get(l), initialWeight));
			// }
			// }

		}
	}

}
