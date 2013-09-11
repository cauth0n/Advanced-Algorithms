package neural_net;

public abstract class Neuron {

	protected NeuronFunction neuronFunction;

	public Neuron(NeuronFunction neuronFunction) {
		this.neuronFunction = neuronFunction;
	}

	public NeuronFunction getNeuronFunction() {
		return neuronFunction;
	}

}
