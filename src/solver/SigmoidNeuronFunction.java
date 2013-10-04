package solver;

/**
 * Class that represents a sigmoidal activation. The logistic function is chosen
 * here, for simplicity when it comes to the derivative.
 * 
 * @author cauth0n
 * 
 */
public class SigmoidNeuronFunction extends NeuronFunction {

	private double alpha = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * Calling the logistic function with input valueToActivate
	 * 
	 * @see solver.ActivationFunction#activate(double)
	 */
	@Override
	public double activate(double valueToActivate) {

		return (1.0 / (1.0 + Math.exp(-1 * alpha * valueToActivate)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Calls the derivative of the logistic function with input valueToActivate
	 * 
	 * @see solver.ActivationFunction#activationDerivative(double)
	 */
	@Override
	public double activationDerivative(double valueToActivate) {
		double activation = activate(valueToActivate);
		return alpha * (activation * (1 - activation));
	}
}
