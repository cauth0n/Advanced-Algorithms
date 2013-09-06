package neural_net;

import java.util.List;

import driver.Simulator;

public class InputLayer extends Layer {

	public InputLayer(int numInputNeurons, int numOutgoingConnectionsPerInputNeuron) {
		super(numInputNeurons, numOutgoingConnectionsPerInputNeuron);
		layerType = "INPUT";
	}

	@Override
	public void buildLayer(List<Neuron> downStreamNeurons) {
		if (downStreamNeurons.size() != connections.size()) {
			System.out.println("Downstream neurons do not equal the connections coming in to them. Critical. This is in InputLayer.");
		}
		for (int i = 0; i < neurons.size(); i++) {
			neurons.add(new DataNeuron(Simulator.dataFunction));
		}
		for (int i = 0; i < neurons.size(); i++) {

			if (numOutgoingConnectionsPerInputNeuron == downStreamNeurons.size()) {
				for (int j = 0; j < downStreamNeurons.size(); j++) {
					connections.add(new Connection(neurons.get(i), downStreamNeurons.get(j), initialWeight));
				}
			} else {
				// Assume numOutgoingConnections = downstream neurons. If not, I
				// will need to implement some method to randomly assign
				// connections
				// to downstream neurons.
			}
		}
	}
}
