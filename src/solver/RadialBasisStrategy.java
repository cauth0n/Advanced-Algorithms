package solver;

import neural_net.Layer;
import neural_net.NeuralNetworkStructure;
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

		int counter = 0;

		feedForward(d.getInputValues());

		backPropagateWeightErrors();
		updateWeights();

		stoppingCondition.postRoundOperation(getNNOutput());
		counter++;

		return errorFromThisRound;
	}

	@Override
	public double mainTestLoop(DataPoint d) {
		// TODO Auto-generated method stub
		return 0;
	}

}
