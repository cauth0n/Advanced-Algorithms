package solver;

import java.util.List;
import java.util.Scanner;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.NeuralNetworkStructure;
import neural_net.Neuron;

/**
 * @author cauthon
 */
public abstract class FeedForwardNeuralNetworkStrategy extends NeuralNetworkAlgorithmStrategy {

	protected NeuralNetworkStructure neuralNetStructure;
	protected double alpha;
	protected double eta;
	protected double targetOutput;

	public FeedForwardNeuralNetworkStrategy(NeuralNetworkStructure neuralNetStructure, double alpha, double eta) {
		this.neuralNetStructure = neuralNetStructure;
		this.alpha = alpha;
		this.eta = eta;
	}

	public void print() {
		System.out.println(neuralNetStructure.toString());
	}

	public void pause() {
		System.out.println(neuralNetStructure.toString());
		Scanner in = new Scanner(System.in);
		System.out.println("Press enter to continue");
		String go = in.next();
	}

	public double getNNOutput() {
		return neuralNetStructure.getLayers().get(neuralNetStructure.getLayers().size() - 1).getNeuronVector().get(0).getNeuronValue();
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

			for (Connection c : neuralNetStructure.getLayers().get(i).getConnectionVector()) {
				double value = (-1 * eta * c.getToNeuron().getNeuronError() * c.getFromNeuron().getNeuronValue());
				c.setDeltaWeight(value);
			}
		}
	}

	public void backPropagateError() {
		for (int i = neuralNetStructure.getLayers().size() - 1; i >= 0; i--) {
			// skipping input layer
			Layer l = neuralNetStructure.getLayers().get(i);
			switch (l.getLayerType()) {
			case "OUTPUT":
				calculateOutputErrorSignals(l);
				break;
			case "HIDDEN":
				calculateHiddenErrorSignals(l);
				break;
			default:
				break;
			}
		}
	}

	public void calculateOutputErrorSignals(Layer l) {
		for (Neuron n : l.getNeuronVector()) {
			double delta = -1 * ((targetOutput - n.getNeuronValue()) * n.getActivationDerivative());
			n.setNeuronError(delta);
		}
	}

	public void calculateHiddenErrorSignals(Layer l) {
		double runningSum;

		for (Neuron n : l.getNeuronVector()) {
			runningSum = 0.0;
			for (Connection nextC : n.getOutgoingConnectionsFromThisNeuron()) {
				runningSum += nextC.getToNeuron().getNeuronError() * nextC.getWeight();
			}
			runningSum *= n.getActivationDerivative();
			n.setNeuronError(runningSum);
		}
	}

	public void feedForward(List<Double> inputValues) {
		for (Layer l : neuralNetStructure.getLayers()) {
			switch (l.getLayerType()) {

			case "HIDDEN":
				for (Neuron n : l.getNeuronVector()) {
					double newNeuronValue = 0.0;
					for (Connection c : n.getIncomingConnectionsToThisNeuron()) {
						newNeuronValue += c.getFromNeuron().getNeuronValue() * c.getWeight();
					}
					n.activate(newNeuronValue);
				}
				break;
			case "OUTPUT":
				for (Neuron n : l.getNeuronVector()) {
					double newNeuronValue = 0.0;
					for (Connection c : n.getIncomingConnectionsToThisNeuron()) {
						newNeuronValue += c.getFromNeuron().getNeuronValue() * c.getWeight();
					}
					n.setNeuronValue(newNeuronValue);
					// linear for output
				}
				break;
			case "INPUT":
				for (int i = 0; i < l.getNeuronVector().size(); i++) {
					l.getNeuronVector().get(i).setNeuronValue(inputValues.get(i));
				}
				break;
			default:
				break;
			}
		}
	}

}
