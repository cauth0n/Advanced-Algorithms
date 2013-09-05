package neural_net;

public abstract class Neuron {

	private ActivationFunction activationFunction;

	public Neuron(double inputValue) {

	}

	public Neuron(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}

}
