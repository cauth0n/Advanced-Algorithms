package neural_net;

import java.util.ArrayList;
import java.util.List;

public abstract class Layer {

	// all connections weights initialized to 1. Arbitrary, and it will change
	// as the algorithm performs.
	protected final double initialWeight = 1;

	protected List<Neuron> neurons;

	protected String layerType;

	/**
	 * This part is tricky. I am defining a Layer as a set of connections and a
	 * set of neurons, such that the connections lead INTO the neurons. Although
	 * it is arbitrary to make connections INTO or OUTGOING, I am defining a
	 * Layer as INTO.
	 * 
	 * 
	 * @param numNeurons
	 *            number of Neurons at this layer
	 * @param numConnectionsPerNeurons
	 *            number of incoming connections to neurons at this layer.
	 *            Because of the disclaimer above, this must be INCOMING
	 *            connections. NOT outgoing.
	 * 
	 */
	public Layer(String layerType, int numNeurons) {
		this.layerType = layerType;
		neurons = new ArrayList<>(numNeurons);
	}

	/***
	 * Builds a single layer in the neural network. As of v1.0, this is either
	 * an input, hidden, or output layer.
	 * 
	 * Uses null in input and output layers, because they have no nodes coming
	 * from or going to, respectively.
	 * 
	 * The use of null will probably have to be changed later, so that I can put
	 * in or glean information from these layers.
	 * 
	 * @param upStreamNeurons
	 *            Reference to neurons upstream, so that a connection can be
	 *            established.
	 */
	public abstract void buildLayer(List<Neuron> upStreamNeurons);

	public String getLayerType() {
		return layerType;
	}

	public List<Neuron> getNeurons() {
		return neurons;
	}

}
