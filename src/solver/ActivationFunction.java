package solver;

/**
 * Class to define what an activation function in each neuron should have.
 * 
 * Will probably need to be refactored for Gaussian functions
 * 
 * @author cauth0n
 * 
 */
public abstract class ActivationFunction {

	/**
	 * run a value through the function, and return the function's output
	 * 
	 * @param valueToActivate
	 *            value to run through the function
	 * @return output
	 */
	public abstract double activate(double valueToActivate);

	/**
	 * Run a value through the function's derivative, return the output
	 * 
	 * @param valueToActivate
	 *            value to input
	 * @return output
	 */
	public abstract double activationDerivative(double valueToActivate);

}
