package solver;

import java.util.List;

import neural_net.AbstractNeuralNetworkStructureFactory;
import neural_net.Connection;
import neural_net.Layer;
import neural_net.Neuron;
import validation.FunctionApproxDataPoint;

/**
 * Class to define how to solve a radial basis neural net.
 * 
 * @author cauthon
 */
public class RadialBasisTraining extends TrainingMethod {

	/**
	 * Constructor.
	 * 
	 * @param neuralNetStructure
	 *            neural net to solve
	 * @param alpha
	 *            momentum value alpha
	 * @param eta
	 *            learning value eta.
	 */
	public RadialBasisTraining(AbstractNeuralNetworkStructureFactory neuralNetStructure) {
		super(neuralNetStructure);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Main training loop for radial basis NNs. Feed forwards, then back props 1
	 * layer.
	 * 
	 * @see
	 * solver.MachineLearningAlgorithmStrategy#mainTrainingLoop(validation.DataPoint
	 * )
	 */
	@Override
	public double mainTrainingLoop(FunctionApproxDataPoint d) {
		double errorFromThisRound = 0.0;
		targetOutput = d.getNormalizedOutput();
		feedForward(d.getInputValues());
		backPropagateWeightErrors();
		errorFromThisRound = Math.abs(getNNOutput() - targetOutput);
		return errorFromThisRound;
	}

	/**
	 * Method to propagate error backwards from the output to the hidden layer.
	 * That is the only error propagation that is needed.
	 * 
	 */
	public void backPropagateWeightErrors() {
		for (Layer l : neuralNetworkStructure.getLayers()) {
			if (l.getLayerType().equals("RBFHIDDEN")) {
				for (Neuron n : l.getNeuronVector()) {
					for (Connection c : n.getOutgoingConnectionsFromThisNeuron()) {
						c.appendWeight(eta * n.getNeuronValue() * (targetOutput - getNNOutput()));
						// I believe this is right. The output layer only
						// has 1 neuron, the new weight is only the only value.
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * From the input values, feeds values through the network.
	 * 
	 * @see solver.FeedForwardNeuralNetworkStrategy#feedForward(java.util.List)
	 */
	public void feedForward(List<Double> inputValues) {
		for (Layer l : neuralNetworkStructure.getLayers()) {
			switch (l.getLayerType()) {

			case "RBFHIDDEN":
				for (Neuron n : l.getNeuronVector()) {
					((GaussianFunction) n.getActivationFunction()).setXVector(inputValues);
					n.activate(0.0);
				}
				break;
			case "OUTPUT":
				for (Neuron n : l.getNeuronVector()) {
					double newNeuronValue = 0.0;
					for (Connection c : n.getIncomingConnectionsToThisNeuron()) {
						newNeuronValue += c.getFromNeuron().getNeuronValue() * c.getWeight();
					}
					// n.setNeuronValue(newNeuronValue);
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
