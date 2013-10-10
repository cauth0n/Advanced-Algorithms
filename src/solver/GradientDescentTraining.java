package solver;

import java.util.List;

import neural_net.AbstractNeuralNetworkStructureFactory;
import neural_net.Connection;
import neural_net.Layer;
import neural_net.Neuron;
import validation.FunctionApproxDataPoint;

/**
 * Class to apply backprop learning to a ff nn
 * 
 * @author cauthon
 */
public class GradientDescentTraining extends TrainingMethod {

	/**
	 * Constructor.
	 * 
	 * @param neuralNetStructure
	 *            neural net structure
	 * @param alpha
	 *            momentum value
	 * @param eta
	 *            learning rate
	 */
	public GradientDescentTraining(
			AbstractNeuralNetworkStructureFactory neuralNetworkStructure) {
		super(neuralNetworkStructure);
	}

	public double mainTrainingLoop(FunctionApproxDataPoint d) {
		double errorFromThisRound = 0.0;
		double targetOutput = d.getNormalizedOutput();

		feedForward(d.getInputValues());
		backPropagateError();
		backPropagateWeightErrors();
		updateWeights();
		errorFromThisRound = Math.abs(getNNOutput() - targetOutput);
		return errorFromThisRound;
	}

	/**
	 * Method for updating weights according to how much change in weight is
	 * accumulated
	 */
	public void updateWeights() {
		for (Layer l : neuralNetworkStructure.getLayers()) {
			for (Connection c : l.getConnectionVector()) {
				c.appendWeight(c.getDeltaWeight() + alpha
						* c.getMomentumDeltaWeight());
				c.updateTimeStep();
			}
		}
	}

	/**
	 * As the name suggests, propagates weight errors backwards, setting a delta
	 * weight for each Connection c
	 */
	public void backPropagateWeightErrors() {
		for (int i = neuralNetworkStructure.getLayers().size() - 1; i > 0; i--) {

			for (Connection c : neuralNetworkStructure.getLayers().get(i)
					.getConnectionVector()) {
				double value = (-1 * eta * c.getToNeuron().getNeuronError() * c
						.getFromNeuron().getNeuronValue());
				c.setDeltaWeight(value);
			}
		}
	}

	/**
	 * Switch method to determine how to propagate error backwards.
	 * 
	 */
	public void backPropagateError() {
		for (int i = neuralNetworkStructure.getLayers().size() - 1; i >= 0; i--) {
			// skipping input layer
			Layer l = neuralNetworkStructure.getLayers().get(i);
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

	/**
	 * Figures out how much 'error' is associated with each neuron by deriving
	 * the neuron and multiplying a 'error' by it. This is for output neurons
	 * only
	 * 
	 * @param l
	 *            layer we are in
	 */
	public void calculateOutputErrorSignals(Layer l) {
		for (Neuron n : l.getNeuronVector()) {
			double delta = -1
					* ((targetOutput - n.getNeuronValue()) * n
							.getActivationDerivative());
			n.setNeuronError(delta);
		}
	}

	/**
	 * Figures out how much 'error' is associated with each neuron by deriving
	 * the neuron and multiplying a 'error' by it. This is for hidden neurons
	 * only
	 * 
	 * @param l
	 *            layer we are in
	 */
	public void calculateHiddenErrorSignals(Layer l) {
		double runningSum;

		for (Neuron n : l.getNeuronVector()) {
			runningSum = 0.0;
			for (Connection nextC : n.getOutgoingConnectionsFromThisNeuron()) {
				runningSum += nextC.getToNeuron().getNeuronError()
						* nextC.getWeight();
			}
			runningSum *= n.getActivationDerivative();
			n.setNeuronError(runningSum);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Feed forward part.
	 * 
	 * @see solver.FeedForwardNeuralNetworkStrategy#feedForward(java.util.List)
	 */
	public void feedForward(List<Double> inputValues) {
		for (Layer l : neuralNetworkStructure.getLayers()) {
			switch (l.getLayerType()) {

			case "HIDDEN":
				for (Neuron n : l.getNeuronVector()) {
					double newNeuronValue = 0.0;
					for (Connection c : n.getIncomingConnectionsToThisNeuron()) {
						newNeuronValue += c.getFromNeuron().getNeuronValue()
								* c.getWeight();
					}
					n.activate(newNeuronValue);
				}
				break;
			case "OUTPUT":
				for (Neuron n : l.getNeuronVector()) {
					double newNeuronValue = 0.0;
					for (Connection c : n.getIncomingConnectionsToThisNeuron()) {
						newNeuronValue += c.getFromNeuron().getNeuronValue()
								* c.getWeight();
					}
					n.activate(newNeuronValue);
				}
				break;
			case "INPUT":
				for (int i = 0; i < l.getNeuronVector().size(); i++) {
					l.getNeuronVector().get(i)
							.setNeuronValue(inputValues.get(i));
				}
				break;
			default:
				break;
			}
		}
	}

}
