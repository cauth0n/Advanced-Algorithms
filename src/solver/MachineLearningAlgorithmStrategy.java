package solver;

import validation.DataPoint;

/**
 * @author cauthon
 */
public interface MachineLearningAlgorithmStrategy {

	public double mainTrainingLoop(DataPoint d);

	public double mainTestLoop(DataPoint d);

}
