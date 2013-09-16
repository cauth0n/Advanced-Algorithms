package solver;

public class LinearActivationFunction extends ActivationFunction {

	@Override
	public double findError(double targetValue, double neuronValue) {
		return targetValue - neuronValue;
	}

	@Override
	public double activate(double valueToActivate) {
		return valueToActivate;
	}

}
