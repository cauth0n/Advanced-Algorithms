package solver;

/**
 * 
 * Class to determine when a stopping condition is reached by looking at linear
 * regression of the errors.
 * 
 * @author cauth0n
 * 
 */
public class ConvergenceStoppingConditionUsingLinearRegression extends StoppingCondition {
	private double epsilon;
	private int regressionCounter;
	private LinearRegression errorRange;

	/**
	 * Constructor
	 * 
	 * @param epsilon
	 *            slope of errors at which to trigger stopping condition
	 */
	public ConvergenceStoppingConditionUsingLinearRegression(double epsilon) {
		this.epsilon = epsilon;
		regressionCounter = 0;
		errorRange = new LinearRegression();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Triggers when, after 100 iterations, the linear slope of errors is >
	 * epsilon
	 * 
	 * @see solver.StoppingCondition#isDone()
	 */
	@Override
	public boolean isDone() {
		boolean isDone = false;

		if (regressionCounter == 100) {
			double regressionSlope = errorRange.getLinearRegressionSlope();
			System.out.println("Regression slope is: " + regressionSlope);

			if (Math.abs(regressionSlope) < epsilon || regressionSlope > 0) {
				isDone = true;
			}
			regressionCounter = 0;
			errorRange.clearErrorList();
		}
		return isDone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * AFter each round, iterate time step and add error to error list
	 * 
	 * @see solver.StoppingCondition#postRoundOperation(double)
	 */
	@Override
	public void postRoundOperation(double newError) {
		regressionCounter++;
		errorRange.addError(newError);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Reset counter
	 * 
	 * @see solver.StoppingCondition#reset()
	 */
	@Override
	public void reset() {
		regressionCounter = 0;
	}

}
