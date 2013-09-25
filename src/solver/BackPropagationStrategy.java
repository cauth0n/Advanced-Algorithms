package solver;

import java.util.List;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.NeuralNetworkStructure;
import neural_net.Neuron;
import validation.DataPoint;

/**
 * @author cauthon
 */
public class BackPropagationStrategy extends FeedForwardNeuralNetworkStrategy {

	public BackPropagationStrategy(NeuralNetworkStructure neuralNetStructure, double alpha, double eta) {
		super(neuralNetStructure, alpha, eta);
	}

	public double mainTestLoop(DataPoint d) {
		feedForward(d.getInputValues());
		return getNNOutput();
	}

	public double mainTrainingLoop(DataPoint d, StoppingCondition stoppingCondition) {
		double errorFromThisRound = 0.0;
		targetOutput = d.getNormalizedOutput();
		feedForward(d.getInputValues());
		backPropagateError();
		backPropagateWeightErrors();
		updateWeights();
		errorFromThisRound = Math.abs(getNNOutput() - targetOutput);
		return errorFromThisRound;
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
					n.activate(newNeuronValue);
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
