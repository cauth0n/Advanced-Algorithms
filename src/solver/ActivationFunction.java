package solver;

public abstract class ActivationFunction {

	public abstract double activate(double valueToActivate);

	public abstract double activationDerivative(double valueToActivate);

}
