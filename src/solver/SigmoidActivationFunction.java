package solver;

public class SigmoidActivationFunction extends ActivationFunction {

	private double alpha = .01;

	// logistic
	@Override
	public double activate(double valueToActivate) {

		return (1.0 / (1.0 + Math.pow(Math.E, -1 * alpha * valueToActivate)));
	}

	@Override
	public double activationDerivative(double valueToActivate) {
		double activation = activate(valueToActivate);
		return alpha * (activation * (1 - activation));
	}
}
