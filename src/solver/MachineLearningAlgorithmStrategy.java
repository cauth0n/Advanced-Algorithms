package solver;

import validation.DataPoint;

/**
 * @author cauthon
 */
public interface MachineLearningAlgorithmStrategy {

	public abstract void mainLoop(DataPoint d, StoppingCondition stoppingCondition);

}
