package neural_net;

import java.util.ArrayList;
import java.util.List;

public class TwoLayeredStructure extends NeuralNetStructure {

	public TwoLayeredStructure(int numInputNeurons, int numOutgoingConnectionsPerInputNeuron, List<Double> inputVector, int numOutputNeurons) {
		super(numInputNeurons, numOutgoingConnectionsPerInputNeuron, inputVector, numOutputNeurons);
	}

	@Override
	public void constructInputOutputLayers() {
		layers = new ArrayList<>();
		layers.add(inputLayer); // layer 0
		layers.add(outputLayer); // layer 1
	}

}
