package solver;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.NeuralNetworkStructure;
import neural_net.NeuralNetworkModel;
import neural_net.Neuron;
import driver.MachineLearningModel;

/**
 * @author cauthon
 */
public class BackPropagationStrategy extends FeedForwardNeuralNetworkStrategy {

	public BackPropagationStrategy(NeuralNetworkStructure neuralNetStructure) {
		super(neuralNetStructure);
	}

	@Override
	public void solve(double targetOutput) {
		for (Layer l : neuralNetStructure.getLayers()) {
			switch (l.getLayerType()) {
			case "OUTPUT":
				for (Neuron n : l.getNeuronVector()) {
					double diff = targetOutput - n.getNeuronValue();
				}
				break;
			}
		}
	}

	@Override
	public void feedForward() {
		for (Layer l : neuralNetStructure.getLayers()) {
			switch (l.getLayerType()) {
			case "INPUT":// do nothing
				break;
			case "HIDDEN":
			case "OUTPUT":
				for (Neuron n : l.getNeuronVector()) {
					int newNeuronValue = 0;
					for (Connection prevConnection : n.getIncomingConnectionsToThisNeuron()) {
						newNeuronValue += prevConnection.getFromNeuron().getNeuronValue() * prevConnection.getWeight();
						// w*j part
					}
					n.setNodeValue(n.activate(newNeuronValue));
				}
				break;
			}
		}

	}

}
