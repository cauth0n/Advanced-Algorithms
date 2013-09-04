package neural_net;

public class Neuron {

	private ActivationFunction activationFunction;
	private String nodeType;

	public Neuron(String nodeType, ActivationFunction activationFunction) {
		this.nodeType = nodeType;
		this.activationFunction = activationFunction;
	}

	public String getType() {
		return nodeType;
	}

}
