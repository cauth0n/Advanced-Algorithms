package solver;

/**
 * 
 * Class that triggers a stop when a number of iterations has been met.
 * 
 * @author cauth0n
 * 
 */
public class IterativeStoppingCondition extends StoppingCondition {

	private int totalIterations;
	private int numIterationsSoFar;

	/**
	 * Constructor.
	 * 
	 * @param totalIterations
	 *            total number of iterations for a solver to run through
	 */
	public IterativeStoppingCondition(int totalIterations) {
		this.totalIterations = totalIterations;
		numIterationsSoFar = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Triggers when iterations so far == total iterations
	 * 
	 * @see solver.StoppingCondition#isDone()
	 */
	@Override
	public boolean isDone() {
		boolean isDone = false;
		if (numIterationsSoFar == totalIterations) {
			isDone = true;
		}
		return isDone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * after each round, iterate the counter
	 * 
	 * @see solver.StoppingCondition#postRoundOperation(double)
	 */
	@Override
	public void postRoundOperation(double d) {
		numIterationsSoFar++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * reset counter
	 * 
	 * @see solver.StoppingCondition#reset()
	 */
	@Override
	public void reset() {
		numIterationsSoFar = 0;

	}

}
