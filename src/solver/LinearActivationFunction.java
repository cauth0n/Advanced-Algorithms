package solver;

public class LinearActivationFunction extends ActivationFunction {

	@Override
	public double activate(double valueToActivate) {
		return valueToActivate;
	}

	@Override
	public double activationDerivative(double valueToActivate) {
		// this bit doesn't really matter, because I only use linear
		// activation for output neurons..
		return 0;
	}

}
