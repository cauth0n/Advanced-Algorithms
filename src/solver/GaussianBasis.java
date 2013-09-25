package solver;

import java.util.List;

/**
 * @author cauthon
 */
public class GaussianBasis extends ActivationFunction {
	private final double sigma = .5;
	private List<Double> centerVector;
	private List<Double> xVector;

	public GaussianBasis(List<Double> centerVector) {
		this.centerVector = centerVector;
	}

	public void setXVector(List<Double> xVector) {
		this.xVector = xVector;
	}

	@Override
	public double activate(double valueToActivate) {
		return Math.exp(-1 * Math.pow(getScalarEuclideanNorm(xVector), 2) / (2 * Math.pow(sigma, 2)));
	}

	public double getScalarEuclideanNorm(List<Double> xVector) {
		double toRet = 0.0;
		if (centerVector.size() != xVector.size()) {
			System.out.println("Different sized center and x vector");
		}
		for (int i = 0; i < centerVector.size(); i++) {
			toRet += (xVector.get(i) - centerVector.get(i));
		}
		return toRet;
	}

	@Override
	public double activationDerivative(double valueToActivate) {
		System.out.println("got here. bad.");
		return 0;
		// don't need to differentiate a gaussian
	}

	public void setCenterVector(List<Double> centerVector) {
		this.centerVector = centerVector;
	}

}
