package neural_net;

import java.util.ArrayList;
import java.util.List;

import driver.Simulator;

public class InputLayer extends Layer {

	// all connections weights initialized to 1. Arbitrary, and it will change
	// as the algorithm performs.
	private final double initialWeight = 1;

	private List<Connection> connections;
	private List<Neuron> neurons;

	private int numConnectionsPerNeuron;
	private String layerType;

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
	public InputLayer(String layerType, int numNeurons, int numConnectionsPerNeuron) {
		super(layerType, numNeurons);
		neurons = new ArrayList<>(numNeurons);
		this.numConnectionsPerNeuron = numConnectionsPerNeuron;
		connections = new ArrayList<>(numConnectionsPerNeuron * numNeurons);
	}

	public List<Double> getWeightVector() {
		List<Double> weightVector = new ArrayList<>(connections.size());
		for (int i = 0; i < connections.size(); i++) {
			weightVector.add(connections.get(i).getWeight());
		}
		return weightVector;
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
	public void buildLayer(List<Neuron> upStreamNeurons) {
		if (upStreamNeurons.size() != numConnectionsPerNeuron) {
			System.out.println("Incoming connections with incoming neurons were not equal. Critical error.");
		}

		for (int i = 0; i < neurons.size(); i++) {
			neurons.add(new Neuron(Simulator.activationFunction));
		}
		switch (layerType) {

		case "INPUT":
			for (int i = 0; i < neurons.size(); i++) {
				for (int j = 0; j < numConnectionsPerNeuron; j++) {
					connections.add(new Connection(null, neurons.get(i), initialWeight));
				}
			}
		case "HIDDEN":
			for (int i = 0; i < neurons.size(); i++) {
				for (int j = 0; j < numConnectionsPerNeuron; j++) {
					connections.add(new Connection(upStreamNeurons.get(j), neurons.get(i), initialWeight));
				}
			}
		case "OUTPUT":
			for (int i = 0; i < neurons.size(); i++) {
				for (int j = 0; j < numConnectionsPerNeuron; j++) {
					connections.add(new Connection(upStreamNeurons.get(j), null, initialWeight));
				}
			}
		default:
			System.out.println("Incorrect layer type. Continuing, but connections list is null");
		}
	}

	public List<Connection> getConnections() {
		return connections;
	}

	public List<Neuron> getNeurons() {
		return neurons;
	}

	public String getLayerType() {
		return layerType;
	}

}
