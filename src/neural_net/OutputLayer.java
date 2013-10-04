package neural_net;

import java.util.List;

import solver.NeuronFunction;

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
	public OutputLayer(NeuronFunction functionForNeuronsInThisLayer, int numNeurons) {
		super(functionForNeuronsInThisLayer, numNeurons);
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
			Neuron n = new Neuron(functionForNeuronsInThisLayer, initialNodeValue);
			neurons.add(n);
		}
	}
}
