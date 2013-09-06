package neural_net;

import java.util.ArrayList;

public class TwoLayeredStructure extends NeuralNetStructure {

	public TwoLayeredStructure(int numInputNeurons, int numOutgoingConnectionsPerInputNeuron, int numOutputNeurons) {
		super(numInputNeurons, numOutgoingConnectionsPerInputNeuron, numOutputNeurons);
	}

	@Override
	public void constructLayers() {
		layers = new ArrayList<>();
		layers.add(inputLayer); // layer 0
		layers.add(outputLayer); // layer 1
	}

}
