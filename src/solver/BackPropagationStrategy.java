package solver;

import java.util.Scanner;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.NeuralNetworkStructure;
import neural_net.Neuron;

/**
 * @author cauthon
 */
public class BackPropagationStrategy extends FeedForwardNeuralNetworkStrategy {

	public BackPropagationStrategy(NeuralNetworkStructure neuralNetStructure) {
		super(neuralNetStructure);
	}

	public void mainLoop(int numIterations, double targetOutput) {
		for (int i = 0; i < numIterations; i++) {
			feedForward();
			solve(targetOutput);
			System.out.println(neuralNetStructure.toString());
			Scanner in = new Scanner(System.in);

			System.out.println("Press enter to continue");
			String go = in.next();
		}
	}

	@Override
	public void solve(double targetOutput) {
		for (Layer l : neuralNetStructure.getLayers()) {
			switch (l.getLayerType()) {
			case "OUTPUT":
				for (Neuron n : l.getNeuronVector()) {
					n.findAndSetError(targetOutput, n.getNeuronValue());
				}
				break;
			case "HIDDEN":
				for (Neuron n : l.getNeuronVector()) {
					double value = 0.0;
					for (int i = 0; i < n.getOutgoingNeurons().size(); i++) {
						value += n.getOutgoingNeurons().get(i).getErrorAssociatedWithNeuronValue() * n.getOutgoingConnectionsFromThisNeuron().get(i).getWeight();
					}
					n.setErrorAssociatedWithNeuronValue(value * (n.getNeuronValue() * (1 - n.getNeuronValue())));
				}

				break;
			case "INPUT":
			default:
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
					double newNeuronValue = 0;
					for (Connection prevConnection : n.getIncomingConnectionsToThisNeuron()) {
						newNeuronValue += prevConnection.getFromNeuron().getNeuronValue() * prevConnection.getWeight();
						// w*j part
					}
					n.activate(newNeuronValue);
				}
				break;
			}
		}
	}
}
