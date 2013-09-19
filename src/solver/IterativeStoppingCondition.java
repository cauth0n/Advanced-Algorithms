package solver;

public class IterativeStoppingCondition extends StoppingCondition {

	private int totalIterations;
	private int numIterationsSoFar;

	public IterativeStoppingCondition(int totalIterations) {
		this.totalIterations = totalIterations;
		numIterationsSoFar = 0;
	}

	@Override
	public boolean isDone() {
		boolean isDone = false;
		if (numIterationsSoFar == totalIterations) {
			isDone = true;
		}
		return isDone;
	}

	@Override
	public void postRoundOperation() {
		numIterationsSoFar++;
	}

}
