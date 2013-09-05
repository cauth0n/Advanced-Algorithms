package neural_net;

import java.util.ArrayList;
import java.util.List;

public abstract class Layer {

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
	public Layer(String layerType, int numNeurons, int numConnectionsPerNeuron) {
		this.layerType = layerType;
		neurons = new ArrayList<>(numNeurons);
		this.numConnectionsPerNeuron = numConnectionsPerNeuron;
		connections = new ArrayList<>(numConnectionsPerNeuron * numNeurons);
	}

	public abstract void buildLayer(List<Neuron> upStreamNeurons);

}
