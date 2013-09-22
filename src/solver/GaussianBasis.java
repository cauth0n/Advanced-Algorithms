package solver;

/**
 * @author cauthon
 */
public class GaussianBasis extends ActivationFunction {

	private double center;
	private double width;
	private double peak;

	public GaussianBasis(double peak, double center, double width) {
		this.center = center;
		this.width = width;
		this.peak = peak;
	}

	@Override
	public double activate(double valueToActivate) {
		return peak * Math.exp((Math.pow(valueToActivate - center, 2)) / (2 * Math.pow(width, 2)));
	}

	@Override
	public double activationDerivative(double valueToActivate) {
		// TODO Auto-generated method stub
		return 0;
	}

}
