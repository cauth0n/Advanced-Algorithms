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

	public RadialBasisStrategy(NeuralNetworkStructure neuralNetStructure, double alpha, double eta) {
		super(neuralNetStructure, alpha, eta);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double mainTrainingLoop(DataPoint d, StoppingCondition stoppingCondition) {
		double errorFromThisRound = 0.0;

		targetOutput = d.getTargetOutput();

		feedForward(d.getInputValues());
		backPropagateWeightErrors();
		updateWeights();

		errorFromThisRound = Math.abs(getNNOutput() - targetOutput);
		return errorFromThisRound;
	}

	public void feedForward(List<Double> inputValues) {
		for (Layer l : neuralNetStructure.getLayers()) {
			switch (l.getLayerType()) {

			case "RBFHIDDEN":
				for (Neuron n : l.getNeuronVector()) {
					((GaussianBasis) n.getActivationFunction()).setXVector(inputValues);
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
					// n.setNeuronValue(newNeuronValue);// linear
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
