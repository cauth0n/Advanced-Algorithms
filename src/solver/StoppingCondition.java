package solver;

public abstract class StoppingCondition {

	public abstract boolean isDone();

	public abstract void postRoundOperation();

}
