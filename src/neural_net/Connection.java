package neural_net;

public class Connection {

	private Neuron toNeuron;
	private Neuron fromNeuron;
	private double weight;

	public Connection(Neuron fromNeuron, Neuron toNeuron, double weight) {
		this.toNeuron = toNeuron;
		this.fromNeuron = fromNeuron;
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Neuron getToNode() {
		return toNeuron;
	}

	public Neuron getFromNode() {
		return fromNeuron;
	}

}
