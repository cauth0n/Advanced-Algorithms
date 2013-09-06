package neural_net;

public abstract class Neuron {

	private NeuronFunction neuronFunction;

	public Neuron(NeuronFunction neuronFunction) {
		this.neuronFunction = neuronFunction;
	}

}
