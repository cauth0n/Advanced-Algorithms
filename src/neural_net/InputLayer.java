package neural_net;

import java.util.ArrayList;
import java.util.List;

import solver.NeuronFunction;

/**
 * Input layer class. Holds all information held by the input layer
 * 
 * @author cauth0n
 * 
 */
public class InputLayer extends Layer {

	private List<Connection> inputConnections; // unique, for input // layer.

	/**
	 * Constructor
	 * 
	 * @param numInputNeurons
	 * @param inputVector
	 */
	public InputLayer(NeuronFunction functionForNeuronsInThisLayer, int numInputNeurons) {
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
		inputConnections = new ArrayList<>();
		for (int i = 0; i < numNeurons; i++) {
			Neuron n = new Neuron(functionForNeuronsInThisLayer, 0);
			Connection c = new Connection(null, n, getRandomWeight());
			/*
			 * the initial weight bit here may need to change. I may need to
			 * input a vector of manually chosen input weights.
			 */
			n.addIncomingConnectionToThisNeuron(c);

			neurons.add(n);
			inputConnections.add(c);
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
