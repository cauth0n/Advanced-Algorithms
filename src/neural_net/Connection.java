package neural_net;

public class Connection {

	private Neuron toNode;
	private Neuron fromNode;
	private double weight;

	public Connection(Neuron toNode, Neuron fromNode, double weight) {
		this.toNode = toNode;
		this.fromNode = fromNode;
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Neuron getToNode() {
		return toNode;
	}

	public Neuron getFromNode() {
		return fromNode;
	}

}
