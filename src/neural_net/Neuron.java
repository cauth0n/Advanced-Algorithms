package neural_net;

import java.util.ArrayList;
import java.util.List;

import solver.ActivationFunction;

public class Neuron {

	protected ActivationFunction activationFunction;
	protected List<Connection> incomingConnectionsToThisNeuron;
	protected List<Connection> outgoingConnectionsFromThisneuron;
	protected double neuronValue;
	protected double neuronError;

	public Neuron(ActivationFunction activationFunction, double neuronValue) {
		this.activationFunction = activationFunction;
		this.neuronValue = neuronValue;
		incomingConnectionsToThisNeuron = new ArrayList<>();
		outgoingConnectionsFromThisneuron = new ArrayList<>();
		this.neuronError = 0;
	}

	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}

	public double getNeuronError() {
		return neuronError;
	}

	public void setNeuronError(double neuronError) {
		this.neuronError = neuronError;
	}

	public double getActivationDerivative() {
		return activationFunction.activationDerivative(neuronValue);
	}

	public void activate(double valueToActivate) {
		neuronValue = activationFunction.activate(valueToActivate);
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

	public List<Connection> getOutgoingConnectionsFromThisNeuron() {
		return outgoingConnectionsFromThisneuron;
	}

	public List<Neuron> getOutgoingNeurons() {
		List<Neuron> retVal = new ArrayList<>();
		for (Connection c : outgoingConnectionsFromThisneuron) {
			retVal.add(c.getToNeuron());
		}
		return retVal;
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

	public void setNeuronValue(double neuronValue) {
		this.neuronValue = neuronValue;
	}

	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

	public String toString() {
		return neuronValue + "";
	}

}
