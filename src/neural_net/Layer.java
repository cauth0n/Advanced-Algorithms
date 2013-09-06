package neural_net;

import java.util.ArrayList;
import java.util.List;

public abstract class Layer {

	// all connections weights initialized to 1. Arbitrary, and it will change
	// as the algorithm performs.
	protected final double initialWeight = 1;

	protected List<Neuron> neurons;
	protected List<Connection> connections;

	protected int numOutgoingConnectionsPerInputNeuron;

	protected String layerType;

	public Layer(int numNeurons, int numOutgoingConnectionsPerNeuron) {
		this.numOutgoingConnectionsPerInputNeuron = numOutgoingConnectionsPerNeuron;
		neurons = new ArrayList<>(numNeurons);
		connections = new ArrayList<>(numNeurons * numOutgoingConnectionsPerNeuron);
	}

	public abstract void buildLayer(List<Neuron> downStreamNeurons);

	public String getLayerType() {
		return layerType;
	}

	public List<Connection> getConnectionVector() {
		return connections;
	}

	public List<Neuron> getNeuronVector() {
		return neurons;
	}

	public List<Double> getWeightVector() {
		List<Double> weightVector = new ArrayList<>(connections.size());
		for (int i = 0; i < connections.size(); i++) {
			weightVector.add(connections.get(i).getWeight());
		}
		return weightVector;
	}

}
