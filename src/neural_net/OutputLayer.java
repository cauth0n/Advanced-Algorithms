package neural_net;

import java.util.List;

public class OutputLayer extends Layer {

	public OutputLayer(int numNeurons) {
		super(numNeurons);
		layerType = "OUTPUT";
	}

	@Override
	public void buildLayer(List<Neuron> downStreamNeurons) {
		for (int i = 0; i < numNeurons; i++) {
			Neuron n = new Neuron(NeuralNetworkModel.activationFunction, initialNodeValue);
			neurons.add(n);
			
		}

	}

}
