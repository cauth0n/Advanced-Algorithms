package neural_net;

import java.util.ArrayList;

public class TwoLayeredStructure extends NeuralNetStructure {

	private final int numLayers = 2; // two layered network means 2 layers.

	public TwoLayeredStructure(int numInputNeurons, int numOutputNeurons) {
		super(numInputNeurons, numOutputNeurons);
	}

	@Override
	public void constructLayers() {
		layers = new ArrayList<>(numLayers);
		layers.add(inputLayer);
		layers.add(outputLayer);

	}

}
