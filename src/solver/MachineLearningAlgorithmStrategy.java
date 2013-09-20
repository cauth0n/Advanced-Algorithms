package solver;

import validation.DataPoint;

/**
 * @author cauthon
 */
public interface MachineLearningAlgorithmStrategy {

	public void mainTrainingLoop(DataPoint d, StoppingCondition stoppingCondition);

	public double mainTestLoop(DataPoint d);

}
