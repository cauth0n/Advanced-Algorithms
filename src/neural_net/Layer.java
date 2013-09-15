package neural_net;

import java.util.ArrayList;
import java.util.List;

public abstract class Layer {

	// all connections weights initialized to 1. Arbitrary, and it will change
	// as the algorithm performs.
	protected final double initialWeight = 1;
	protected final double initialNodeValue = 0;

	protected List<Neuron> neurons;
	protected List<Connection> connections;

	protected int numOutgoingConnectionsPerNeuron;
	protected int numNeurons;

	protected String layerType;

	public Layer(int numNeurons, int numOutgoingConnectionsPerNeuron) {
		this.numNeurons = numNeurons;
		this.numOutgoingConnectionsPerNeuron = numOutgoingConnectionsPerNeuron;
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

	public List<Neuron> getDownstreamNeuronVector() {
		List<Neuron> downstreamNeuronVector = new ArrayList<>();
		for (Connection c : connections) {
			if (c.getToNeuron() == null) {
				System.out.println("No downstream connection exists. Error.");
				break;
			} else {
				downstreamNeuronVector.add(c.getToNeuron());
			}
		}
		return downstreamNeuronVector;
	}

	public List<Double> getWeightVector() {
		List<Double> weightVector = new ArrayList<>(connections.size());
		for (int i = 0; i < connections.size(); i++) {
			weightVector.add(connections.get(i).getWeight());
		}
		return weightVector;
	}



	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(layerType + ": " + " total neurons: " + neurons.size() + "; connections per neuron: " + (connections.size() / neurons.size()));

		stringBuilder.append("\n<");
		for (Neuron n : neurons) {
			stringBuilder.append(n.toString() + ">, <");
		}
		stringBuilder.append(">\n{");
		for (Connection c : connections) {
			stringBuilder.append(c.toString() + "}, {");
		}
		stringBuilder.append("}\n");
		return stringBuilder.toString();
	}

}
