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
	public void mainTrainingLoop(DataPoint d, StoppingCondition stoppingCondition) {
		stoppingCondition.reset();
		targetOutput = d.getTargetOutput();
		stoppingCondition.setTarget(targetOutput);

		int counter = 0;
		while (!stoppingCondition.isDone()) {
			feedForward(d.getInputValues());

			backPropagateWeightErrors();
			updateWeights();
			for (Layer l : neuralNetStructure.getLayers()) {

	/*			computeCenterSize(l);
				adjustCenters(l);
				computeWidthSize(l);
				adjustWidths(l);*/
			}
			stoppingCondition.postRoundOperation(getNNOutput());
			counter++;
			System.out.println("Target output: " + d.getTargetOutput() + " current output: " + getNNOutput());
		}

		System.out.println(neuralNetStructure.toString());
		System.out.println(counter + " Iterations");

	}

	public void computeCenterSize() {

	}

	@Override
	public double mainTestLoop(DataPoint d) {
		// TODO Auto-generated method stub
		return 0;
	}

}
