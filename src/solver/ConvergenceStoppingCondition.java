package solver;

public class ConvergenceStoppingCondition extends StoppingCondition {
	private double target;
	private double epsilon;
	private double current;
	private int iterations;

	public ConvergenceStoppingCondition(double epsilon) {
		this.epsilon = epsilon;
		iterations = 0;
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
/*		if (iterations > 100000) {
			isDone = true;
			System.out.println("Reached a HUGE number of iterations before convergence. Probably hit a local optima.");
		}*/
		return isDone;
	}

	@Override
	public void postRoundOperation(double newCurrent) {
		this.current = newCurrent;
		iterations++;
	}

	@Override
	public void reset() {
		current = 0;
		iterations = 0;
	}

}
