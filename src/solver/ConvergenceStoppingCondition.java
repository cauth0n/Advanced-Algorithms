package solver;

public class ConvergenceStoppingCondition extends StoppingCondition {
	private double target;
	private double epsilon;
	private double current;

	public ConvergenceStoppingCondition(double epsilon) {
		this.epsilon = epsilon;
	}

	public void setTarget(double newTarget) {
		this.target = newTarget;
	}

	@Override
	public boolean isDone() {
		boolean isDone = false;

		if (Math.abs(target - current) < epsilon) {
			isDone = true;
		}
		return isDone;
	}

	@Override
	public void postRoundOperation(double newCurrent) {
		this.current = newCurrent;
	}

	@Override
	public void reset() {
		current = 0;
	}

}
