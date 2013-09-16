package solver;

public abstract class ActivationFunction {

	public abstract double activate(double valueToActivate);

	public abstract double findError(double targetValue, double neuronValue);

}
