package solver;

import java.util.Scanner;

public class ConvergenceStoppingCondition extends StoppingCondition {
	private double epsilon;
	private double newError;
	private int regressionCounter;
	private ErrorRange errorRange;

	public ConvergenceStoppingCondition(double epsilon) {
		this.epsilon = epsilon;
		regressionCounter = 0;
		errorRange = new ErrorRange(100);
	}

	@Override
	public boolean isDone() {
		boolean isDone = false;

		if (regressionCounter == 100) {
			double regressionSlope = errorRange.getLinearRegressionSlope();
			System.out.println("Regression slope is: " + regressionSlope);

			if (regressionSlope > epsilon) {
				isDone = true;
			}
			regressionCounter = 0;
			errorRange.clearErrorList();
		}
		return isDone;
	}

	@Override
	public void postRoundOperation(double newError) {
		this.newError = newError;
		regressionCounter++;
		errorRange.addError(newError);
	}

	@Override
	public void reset() {
		newError = 0;
		regressionCounter = 0;
	}

}
