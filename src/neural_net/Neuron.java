package neural_net;

import java.util.ArrayList;
import java.util.List;

public class Neuron {

	protected ActivationFunction activationFunction;
	protected List<Connection> incomingConnectionsToThisNeuron;
	protected List<Connection> outgoingConnectionsFromThisneuron;
	protected double neuronValue;

	public Neuron(ActivationFunction activationFunction, double neuronValue) {
		this.activationFunction = activationFunction;
		this.neuronValue = neuronValue;
		incomingConnectionsToThisNeuron = new ArrayList<>();
		outgoingConnectionsFromThisneuron = new ArrayList<>();
	}

	public double activate(double ValueToOperateOn) {
		return activationFunction.operate(ValueToOperateOn);
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

	public List<Neuron> getIncomingNeurons() {
		List<Neuron> retVal = new ArrayList<>();
		for (Connection c : incomingConnectionsToThisNeuron) {
			retVal.add(c.getFromNeuron());
		}
		return retVal;
	}

	public double getNeuronValue() {
		return neuronValue;
	}

	public void setNodeValue(double nodeValue) {
		this.neuronValue = nodeValue;
	}

	public String toString() {
		return neuronValue + "";
	}

}
