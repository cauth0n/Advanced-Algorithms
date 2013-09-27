package neural_net;

/**
 * Connection class. Defines a connection between neurons
 * 
 * @author cauth0n
 * 
 */
public class Connection {

	private Neuron toNeuron;
	private Neuron fromNeuron;
	private double weight;
	private double deltaWeight;
	private double momentumDeltaWeight;

	/**
	 * Constructor
	 * 
	 * @param fromNeuron
	 * @param toNeuron
	 * @param weight
	 */
	public Connection(Neuron fromNeuron, Neuron toNeuron, double weight) {
		this.toNeuron = toNeuron;
		this.fromNeuron = fromNeuron;
		this.weight = weight;
		deltaWeight = 0;
		momentumDeltaWeight = 0;
	}

	/**
	 * Appends a weight to the current weight
	 * 
	 * @param weightToAppend
	 */
	public void appendWeight(double weightToAppend) {
		this.weight += weightToAppend;
	}

	public double getMomentumDeltaWeight() {
		return momentumDeltaWeight;
	}

	public void updateTimeStep() {
		momentumDeltaWeight = deltaWeight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Neuron getToNeuron() {
		return toNeuron;
	}

	public Neuron getFromNeuron() {
		return fromNeuron;
	}

	public double getDeltaWeight() {
		return deltaWeight;
	}

	public void setDeltaWeight(double deltaWeight) {
		this.deltaWeight = deltaWeight;
	}

	public String toString() {
		return (weight + "");
	}

}
