package neural_net;

import java.util.List;

public class Layering extends NeuralNetStructure {

	private List<Neuron> nodeVector;
	private List<Connection> connectionVector;

	private int numNeuronsAtThisLayer;

	public Layering(int numNeuronsAtThisLayer) {
		this.numNeuronsAtThisLayer = numNeuronsAtThisLayer;
	}

	public List<Neuron> getNodeVector() {
		return nodeVector;
	}

	public List<Connection> getConnectionVector() {
		return connectionVector;
	}

	public int getNumNeuronsAtThisLayer() {
		return numNeuronsAtThisLayer;
	}
	
	

}
