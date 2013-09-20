package solver;

public abstract class StoppingCondition {

	public abstract boolean isDone();

	public abstract void postRoundOperation(double postRoundOperation);

	public abstract void setTarget(double newTarget);

	public abstract void reset();

}
