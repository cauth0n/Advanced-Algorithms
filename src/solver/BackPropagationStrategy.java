package solver;

import java.util.List;
import java.util.Scanner;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.NeuralNetworkStructure;
import neural_net.Neuron;
import validation.DataPoint;

/**
 * @author cauthon
 */
public class BackPropagationStrategy extends FeedForwardNeuralNetworkStrategy {
	private double targetOutput;

	public BackPropagationStrategy(NeuralNetworkStructure neuralNetStructure, double alpha, double eta) {
		super(neuralNetStructure, alpha, eta);
	}

	public double mainTestLoop(DataPoint d) {
		feedForward(d.getInputValues());
		return getNNOutput();
	}

	public void mainTrainingLoop(DataPoint d, StoppingCondition stoppingCondition) {
		stoppingCondition.reset();
		targetOutput = d.getTargetOutput();
		stoppingCondition.setTarget(targetOutput);
		int counter = 0;
		while (!stoppingCondition.isDone()) {
			// print();
			// pause();
			feedForward(d.getInputValues());
			backPropagateError();
			backPropagateWeightErrors();
			updateWeights();

			stoppingCondition.postRoundOperation(getNNOutput());
			counter++;
		}

		System.out.println(neuralNetStructure.toString());
		System.out.println(counter + " Iterations");
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
		return neuralNetStructure.getLayers().get(neuralNetStructure.getLayers().size() - 1).getConnectionVector().get(0).getWeight();
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
				double value = (-1 * eta * c.getError() * c.getFromNeuron().getNeuronValue());
				c.setDeltaWeight(value);
				// this c.getFromNeuron.getNeuronValue might need to change:
				// change to something like
				// c.getFromNeuron.getPreviousNeuron.getValue...
			}
		}
	}

	@Override
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
			for (Connection c : n.getOutgoingConnectionsFromThisNeuron()) {
				double delta = -1 * ((targetOutput - c.getWeight()) * n.getActivationDerivative());
				c.setError(delta);
			}
		}
	}

	public void calculateHiddenErrorSignals(Layer l) {
		double runningSum;
		for (Connection c : l.getConnectionVector()) {
			runningSum = 0.0;
			for (Connection cNext : c.getToNeuron().getOutgoingConnectionsFromThisNeuron()) {
				runningSum += cNext.getError() * cNext.getWeight();
			}
			runningSum *= c.getToNeuron().getActivationDerivative();
			c.setError(runningSum);
		}
	}

	@Override
	public void feedForward(List<Double> inputValues) {
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
