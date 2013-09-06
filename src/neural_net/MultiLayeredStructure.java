package neural_net;

import java.util.ArrayList;

public class MultiLayeredStructure extends NeuralNetStructure {

	public MultiLayeredStructure(int numInputNeurons, int numOutgoingConnectionsPerInputNeuron, int numOutputNeurons) {
		super(numInputNeurons, numOutgoingConnectionsPerInputNeuron, numOutputNeurons);
		layers = new ArrayList<>();
	}

	public void addHiddenLayer(int layerPlacement, int numNeuronsInThisLayer, int numOutgoingConnectionsPerNeuronInThisLayer) {
		if (layerPlacement <= 0 || layerPlacement >= layers.size() - 1) {
			System.out.println("Cannot place a new layer in the input or output layer.");
		}
		layers.add(layerPlacement - 1, new HiddenLayer(numNeuronsInThisLayer, numOutgoingConnectionsPerNeuronInThisLayer));
	}

	@Override
	public void constructLayers() {
		layers = new ArrayList<>();
		layers.add(inputLayer); // layer 0
		layers.add(outputLayer); // layer 1
	}

	public int getNumLayers() {
		return layers.size();
	}

}
