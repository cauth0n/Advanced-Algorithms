package neural_net;

import java.util.List;

public abstract class NeuralNetStructure {

	public final String input = "INPUT";
	public final String output = "OUTPUT";
	public final String hidden = "HIDDEN";

	protected List<InputLayer> layers;

	protected InputLayer inputLayer;
	protected InputLayer outputLayer;

	protected int numInputNeurons;
	protected int numOutputNeurons;

	public NeuralNetStructure(int numInputNeurons, int numOutputNeurons) {
		inputLayer = new InputLayer(input, numInputNeurons, numInputNeurons);
	}

	public abstract void constructLayers();

}
