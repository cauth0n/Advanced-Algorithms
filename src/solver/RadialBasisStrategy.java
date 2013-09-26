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
public class RadialBasisStrategy extends FeedForwardNeuralNetworkStrategy {

	public RadialBasisStrategy(NeuralNetworkStructure neuralNetStructure, double alpha, double eta, List<DataPoint> trainingSet) {
		super(neuralNetStructure, alpha, eta);
	}

	@Override
	public double mainTrainingLoop(DataPoint d) {
		double errorFromThisRound = 0.0;

		targetOutput = d.getNormalizedOutput();
		feedForward(d.getInputValues());
		backPropagateWeightErrors();
		// System.out.println(targetOutput);
		// pause();

		errorFromThisRound = Math.abs(getNNOutput() - targetOutput);
		return errorFromThisRound;
	}

	public void backPropagateWeightErrors() {
		for (Layer l : neuralNetStructure.getLayers()) {
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

	public void feedForward(List<Double> inputValues) {
		for (Layer l : neuralNetStructure.getLayers()) {
			switch (l.getLayerType()) {

			case "RBFHIDDEN":
				for (Neuron n : l.getNeuronVector()) {
					((GaussianBasis) n.getActivationFunction()).setXVector(inputValues);
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

	@Override
	public double mainTestLoop(DataPoint d) {
		// TODO Auto-generated method stub
		return 0;
	}

}
