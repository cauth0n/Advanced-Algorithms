package neural_net;

import java.util.ArrayList;
import java.util.List;

public class MultiLayeredStructure extends NeuralNetStructure {

	private List<InputLayer> layers;

	public MultiLayeredStructure(int numInputNeurons, int numOutputNeurons) {
		super(numInputNeurons, numOutputNeurons);
		layers = new ArrayList<>();
	}

	public void addMoreLayers() {

	}

}
