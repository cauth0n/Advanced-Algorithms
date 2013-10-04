package neural_net;

public class NeuralNetworkStructuralInfo {

	private int numInputNeurons;
	private int numHiddenLayerNeurons;
	private int numHiddenLayers;
	private int numOutputNeurons;

	public NeuralNetworkStructuralInfo(int numInputNeurons, int numHiddenLayerNeurons, int numHiddenLayers, int numOutputNeurons) {
		this.numInputNeurons = numInputNeurons;
		this.numHiddenLayerNeurons = numHiddenLayerNeurons;
		this.numHiddenLayers = numHiddenLayers;
		this.numOutputNeurons = numOutputNeurons;
	}

	public int getNumInputNeurons() {
		return numInputNeurons;
	}

	public int getNumHiddenLayerNeurons() {
		return numHiddenLayerNeurons;
	}

	public int getNumHiddenLayers() {
		return numHiddenLayers;
	}

	public int getNumOutputNeurons() {
		return numOutputNeurons;
	}

}
