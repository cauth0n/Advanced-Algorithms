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

		return 0;
	}

	public double getCenter() {
		return center;
	}

	public void setCenter(double center) {
		this.center = center;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getPeak() {
		return peak;
	}

	public void setPeak(double peak) {
		this.peak = peak;
	}

}
