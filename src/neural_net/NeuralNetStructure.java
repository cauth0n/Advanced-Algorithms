package neural_net;

import java.util.List;

public abstract class NeuralNetStructure {

	protected List<Layer> layers;

	protected InputLayer inputLayer;
	protected OutputLayer outputLayer;

	protected int numInputNeurons;
	protected int numOutputNeurons;

	public NeuralNetStructure(int numInputNeurons, int numOutgoingConnectionsPerInputNeuron, int numOutputNeurons) {
		inputLayer = new InputLayer(numInputNeurons, numOutgoingConnectionsPerInputNeuron);
		outputLayer = new OutputLayer(numOutputNeurons);
	}

	public abstract void constructLayers();

	public void stitchLayersTogether() {
		for (int i = layers.size() - 1; i >= 0; i--) {
			if (i == layers.size() - 1) {// output, downStreamNeurons are null.
				layers.get(i).buildLayer(null);
			} else { // evertyhing else.
				layers.get(i).buildLayer(layers.get(i + 1).getNeuronVector());
			}
		}
	}

}
