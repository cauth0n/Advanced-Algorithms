package solver;

public class SigmoidActivationFunction extends ActivationFunction {

	// logistic
	@Override
	public double activate(double valueToActivate) {

		return (1.0 / (1.0 + Math.pow(Math.E, -1 * 1 * valueToActivate)));
	}

	@Override
	public double activationDerivative(double valueToActivate) {
		double activation = activate(valueToActivate);
		return (activation * (1 - activation));
	}
}
