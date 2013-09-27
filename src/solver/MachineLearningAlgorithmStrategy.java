package solver;

import validation.DataPoint;

/**
 * Interface for describing how machine learning algorithms will be solved.
 * 
 * @author cauthon
 */
public interface MachineLearningAlgorithmStrategy {

	/**
	 * Main training loop. Takes in a data point, performs whatever learning
	 * operation, then returns error associated with that training round.
	 * 
	 * @param d
	 *            data point to train on
	 * @return error from data point's training round
	 */
	public double mainTrainingLoop(DataPoint d);

	/**
	 * Main testing loop. No learning goes on here. Takes in a data point d,
	 * runs it through the model, and returns output
	 * 
	 * @param d
	 *            data point to test on
	 * @return output from runnign d on the model
	 */
	public double mainTestLoop(DataPoint d);

}
