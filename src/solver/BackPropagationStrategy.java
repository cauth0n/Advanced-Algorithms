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

	private double targetOutput;

	public BackPropagationStrategy(NeuralNetworkStructure neuralNetStructure, double alpha, double eta) {
		super(neuralNetStructure, alpha, eta);
	}

	public void mainLoop(int numIterations, double targetOutput) {
		this.targetOutput = targetOutput;
		for (int i = 0; i < numIterations; i++) {
			// pause();

			feedForward();
			backPropagateError();
			backPropagateWeightErrors();
			updateWeights();
		}
		System.out.println(neuralNetStructure.toString());
	}

	public void pause() {
		System.out.println(neuralNetStructure.toString());
		Scanner in = new Scanner(System.in);
		System.out.println("Press enter to continue");
		String go = in.next();
	}

	public void updateWeights() {
		for (Layer l : neuralNetStructure.getLayers()) {
			for (Connection c : l.getConnectionVector()) {
				c.appendWeight(c.getDeltaWeight() + alpha * c.getMomentumDeltaWeight());
				c.updateTimeStep();
			}
		}
	}

	public void backPropagateWeightErrors() {
		for (int i = neuralNetStructure.getLayers().size() - 1; i > 0; i--) {
			Layer l = neuralNetStructure.getLayers().get(i);
			backPropagateWeights(l);
		}
	}

	public void backPropagateWeights(Layer l) {
		for (Neuron n : l.getNeuronVector()) {
			for (Connection c : n.getIncomingConnectionsToThisNeuron()) {
				c.setDeltaWeight(-1 * eta * c.getFromNeuron().getNeuronValue() * n.getNeuronError());
			}
		}
	}

	@Override
	public void backPropagateError() {
		for (int i = neuralNetStructure.getLayers().size() - 1; i > 0; i--) {
			// skipping input layer
			Layer l = neuralNetStructure.getLayers().get(i);
			switch (l.getLayerType()) {
			case "OUTPUT":
				for (Neuron n : l.getNeuronVector()) {
					n.setNeuronError(getCalculatedOutputErrorSignals(n));
				}
				break;
			case "HIDDEN":
				calculateHiddenErrorSignals(l);
				break;
			default:
				break;
			}
		}
	}

	public double getCalculatedOutputErrorSignals(Neuron n) {
		double delta = -1 * ((targetOutput - n.getNeuronValue()) * n.getActivationDerivative());
		return delta;
	}

	public void calculateHiddenErrorSignals(Layer l) {
		double runningSum;
		for (Neuron n : l.getNeuronVector()) {
			runningSum = 0.0;
			for (Connection outConn : n.getOutgoingConnectionsFromThisNeuron()) {
				runningSum += outConn.getWeight() * outConn.getToNeuron().getNeuronError();
			}
			runningSum *= n.getActivationDerivative();
			n.setNeuronError(runningSum);
		}
	}

	@Override
	public void feedForward() {
		for (Layer l : neuralNetStructure.getLayers()) {
			switch (l.getLayerType()) {

			case "HIDDEN":
			case "OUTPUT":
				for (Neuron n : l.getNeuronVector()) {
					double newNeuronValue = 0.0;
					for (Connection c : n.getIncomingConnectionsToThisNeuron()) {
						newNeuronValue += c.getFromNeuron().getNeuronValue() * c.getWeight();
					}
					n.activate(newNeuronValue);
				}
				break;
			default:
				break;
			}
		}
	}
}
