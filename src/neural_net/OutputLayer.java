package neural_net;

import java.util.List;

/**
 * Output layer -- neurons just output.
 * 
 * @author cauth0n
 * 
 */
public class OutputLayer extends Layer {

	/**
	 * Constructor.
	 * 
	 * @param numNeurons
	 *            number neruons in this layer.
	 */
	public OutputLayer(int numNeurons) {
		super(numNeurons);
		layerType = "OUTPUT";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Hhow we build this layer. simple.
	 * 
	 * @see neural_net.Layer#buildLayer(java.util.List)
	 */
	@Override
	public void buildLayer(List<Neuron> downStreamNeurons) {
		for (int i = 0; i < numNeurons; i++) {
			Neuron n = new Neuron(NeuralNetworkModel.activationFunction, initialNodeValue);
			neurons.add(n);
		}
	}
}
