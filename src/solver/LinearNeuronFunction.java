package solver;

public class LinearNeuronFunction extends NeuronFunction {

	@Override
	public double activate(double valueToActivate) {
		return valueToActivate;
	}

	@Override
	public double activationDerivative(double valueToActivate) {
		/*
		 * should never be called.
		 */

		System.out.println("Should not be here. (in linear function's activationderivative method)");
		return valueToActivate;
	}

}
