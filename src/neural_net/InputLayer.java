package neural_net;

import java.util.ArrayList;
import java.util.List;

public class InputLayer extends Layer {

	private List<Connection> inputConnections; // unique, for input layer.
	private List<Double> inputVector;

	public InputLayer(int numInputNeurons, int numOutgoingConnectionsPerInputNeuron, List<Double> inputVector) {
		super(numInputNeurons, numOutgoingConnectionsPerInputNeuron);
		this.inputVector = inputVector;
		layerType = "INPUT";
	}

	@Override
	public void buildLayer(List<Neuron> downStreamNeurons) {
		inputConnections = new ArrayList<>();
		for (int i = 0; i < numNeurons; i++) {
			Neuron n = new Neuron(NeuralNetworkType.activationFunction, inputVector.get(i));
			Connection c = new Connection(null, n, initialWeight);
			/*
			 * the initial weight bit here may need to change. I may need to
			 * input a vector of manually chosen input weights.
			 */
			n.addIncomingConnectionToThisNeuron(c);

			neurons.add(n);
			inputConnections.add(c);
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


}
