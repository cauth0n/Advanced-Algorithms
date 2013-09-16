package solver;

public class SigmoidActivationFunction extends ActivationFunction {

	@Override
	public double findError(double targetValue, double neuronValue) {
		double linear = targetValue - neuronValue;
		double sigmoid = (neuronValue * (1 - neuronValue));

		return linear * sigmoid;
	}

	//logistic
	@Override
	public double activate(double valueToActivate) {
		return (1.0 / (1.0 + Math.pow(Math.E, -1 * valueToActivate)));
	}
}
