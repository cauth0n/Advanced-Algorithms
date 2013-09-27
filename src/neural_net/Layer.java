package neural_net;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract layer definition. Holds information shared across all layers
 * 
 * @author cauth0n
 * 
 */
public abstract class Layer {

	protected final double initialNodeValue = 0;
	protected List<Neuron> neurons;
	protected List<Connection> connections;
	protected int numNeurons;
	protected String layerType;

	/**
	 * Constructor.
	 * 
	 * @param numNeurons
	 */
	public Layer(int numNeurons) {
		this.numNeurons = numNeurons;
		neurons = new ArrayList<>(numNeurons);
		connections = new ArrayList<>();
	}

	/**
	 * 
	 * Skeleton method to define how a layer is built. I effectively build the
	 * entire network in reverse fashion, starting with output and ending with
	 * input. then the layers are stitched together.
	 * 
	 * @param downStreamNeurons
	 */
	public abstract void buildLayer(List<Neuron> downStreamNeurons);

	public String getLayerType() {
		return layerType;
	}

	public List<Connection> getConnectionVector() {
		return connections;
	}

	public List<Neuron> getNeuronVector() {
		return neurons;
	}

	/**
	 * Gets a random weight in the range of [-1,1]
	 * 
	 * @return that random weight
	 */
	public double getRandomWeight() {
		Random rand = new Random();
		double toRet = rand.nextDouble();
		if (rand.nextBoolean()) {
			toRet *= -1;
		}
		return toRet;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(layerType + ": " + " total neurons: " + neurons.size() + "; connections per neuron: " + (connections.size() / neurons.size()));

		stringBuilder.append("\n<");
		for (Neuron n : neurons) {
			stringBuilder.append(n.toString() + ">, <");
		}
		stringBuilder.append(">\n{");
		for (Connection c : connections) {
			stringBuilder.append(c.toString() + "}, {");
		}
		stringBuilder.append("}\n");
		return stringBuilder.toString();
	}

}
