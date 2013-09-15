package neural_net;

import java.util.ArrayList;
import java.util.List;

public class MultiLayeredStructure extends NeuralNetStructure {

	public MultiLayeredStructure(int numInputNeurons, int numOutgoingConnectionsPerInputNeuron, List<Double> inputVector, int numOutputNeurons) {
		super(numInputNeurons, numOutgoingConnectionsPerInputNeuron, inputVector, numOutputNeurons);
		layers = new ArrayList<>();
	}

	@Override
	public void constructInputOutputLayers() {
		layers = new ArrayList<>();
		layers.add(inputLayer); // layer 0
		layers.add(outputLayer); // layer 1
	}

	public void addHiddenLayer(int layerPlacement, int numNeuronsInThisLayer, int numOutgoingConnectionsPerNeuronInThisLayer) {
		if (layerPlacement <= 0 || layerPlacement == layers.size()) {
			System.out.println("Cannot place a new layer in the input or output layer.");
		}
		layers.add(layerPlacement, new HiddenLayer(numNeuronsInThisLayer, numOutgoingConnectionsPerNeuronInThisLayer));
	}

	public int getNumLayers() {
		return layers.size();
	}

}
