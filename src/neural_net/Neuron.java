package neural_net;

import java.util.ArrayList;
import java.util.List;

import solver.NeuronFunction;

/**
 * 
 * Class to define neuron. A neuron has a value, activation function, and
 * incomign and outgoing connections.
 * 
 * I am not commenting the getters and setters.
 * 
 * @author cauth0n
 * 
 */
public class Neuron {

	protected NeuronFunction activationFunction;
	protected List<Connection> incomingConnectionsToThisNeuron;
	protected List<Connection> outgoingConnectionsFromThisneuron;
	protected double neuronValue;
	protected double neuronError;

	/**
	 * Constructor;
	 * 
	 * @param activationFunction
	 * @param neuronValue
	 */
	public Neuron(NeuronFunction activationFunction, double neuronValue) {
		this.activationFunction = activationFunction;
		this.neuronValue = neuronValue;
		incomingConnectionsToThisNeuron = new ArrayList<>();
		outgoingConnectionsFromThisneuron = new ArrayList<>();
		this.neuronError = 0;
	}

	/**
	 * getter for activation function
	 * 
	 * @return activation function of this neuron
	 */
	public NeuronFunction getActivationFunction() {
		return activationFunction;
	}

	/**
	 * 
	 * getter for neuron error
	 * 
	 * @return neruon error
	 */
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

	public double getNeuronValue() {
		return neuronValue;
	}

	public void setNeuronValue(double neuronValue) {
		this.neuronValue = neuronValue;
	}

	public void setActivationFunction(NeuronFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

	public String toString() {
		return neuronValue + "";
	}

}
