package neural_net;

import java.util.ArrayList;
import java.util.List;

public abstract class Neuron {

	protected NeuronFunction neuronFunction;
	protected List<Connection> incomingConnectionsToThisNeuron;
	protected List<Connection> outgoingConnectionsFromThisneuron;

	public Neuron(NeuronFunction neuronFunction) {
		this.neuronFunction = neuronFunction;
		incomingConnectionsToThisNeuron = new ArrayList<>();
		outgoingConnectionsFromThisneuron = new ArrayList<>();
	}

	public NeuronFunction getNeuronFunction() {
		return neuronFunction;
	}

	public void addIncomingConnectionToThisNeuron(Connection c) {
		incomingConnectionsToThisNeuron.add(c);
	}

	public void addOutgoingConnectionFromThisNeuron(Connection c) {
		outgoingConnectionsFromThisneuron.add(c);
	}

	public List<Connection> getIncomingConnectionsToThisNeuron() {
		return incomingConnectionsToThisNeuron;
	}

	public List<Connection> getOutgoingConnectionsFromThisneuron() {
		return outgoingConnectionsFromThisneuron;
	}

}
