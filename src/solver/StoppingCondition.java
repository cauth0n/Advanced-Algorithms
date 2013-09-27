package solver;

/**
 * Class to define how to decide when a training iteration should stop.
 * 
 * @author cauth0n
 * 
 */
public abstract class StoppingCondition {

	/**
	 * Skeleton method to determine when a training iteration is complete.
	 * 
	 * @return true if finishing criteria is met, false if not
	 */
	public abstract boolean isDone();

	/**
	 * Skeleton method to determine what happens after each training round.
	 * 
	 * @param postRoundOperation
	 *            double data point to tell the stopping condition what has
	 *            changed since the past training round.
	 */
	public abstract void postRoundOperation(double postRoundOperation);

	/**
	 * This is done after iterations of training rounds, so that each training
	 * round starts anew
	 * 
	 */
	public abstract void reset();

}
