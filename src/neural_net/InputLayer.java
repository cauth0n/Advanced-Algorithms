package neural_net;

import java.util.ArrayList;
import java.util.List;

import driver.Simulator;

public class InputLayer extends Layer {

	private List<Connection> connections;
	private List<Neuron> neurons;

	/**
	 * This part is tricky. I am defining a Layer as a set of connections and a
	 * set of neurons, such that the connections lead INTO the neurons. Although
	 * it is arbitrary to make connections INTO or OUTGOING, I am defining a
	 * Layer as INTO.
	 * 
	 * @param numNeurons
	 *            number of Neurons at this layer
	 * 
	 */
	public InputLayer(String layerType, int numNeurons) {
		super(layerType);
		neurons = new ArrayList<>(numNeurons);
		connections = new ArrayList<>(numNeurons);
	}

	public List<Double> getWeightVector() {
		List<Double> weightVector = new ArrayList<>(connections.size());
		for (int i = 0; i < connections.size(); i++) {
			weightVector.add(connections.get(i).getWeight());
		}
		return weightVector;
	}

	/***
	 * Builds a single input layer in the neural network.
	 * 
	 * Uses null in input layer, because no neurons are upstream.
	 * 
	 * The use of null will probably have to be changed later, so that I can put
	 * in or glean information from these layers.
	 * 
	 * @param upStreamNeurons
	 *            this parameter will be null in the input case. I am leaving it
	 *            here because this method is being overwritten, and I want to
	 *            make sure it is overwritten.
	 * 
	 */
	public void buildLayer(List<Neuron> upStreamNeurons) {// upStreamNeurons is
															// null in this
															// case.
		for (int i = 0; i < neurons.size(); i++) {
			neurons.add(new Neuron(Simulator.activationFunction));
		}

		for (int i = 0; i < neurons.size(); i++) {
			connections.add(new Connection(null, neurons.get(i), initialWeight));
		}
	}

	public List<Connection> getConnections() {
		return connections;
	}

	public List<Neuron> getNeurons() {
		return neurons;
	}

}
