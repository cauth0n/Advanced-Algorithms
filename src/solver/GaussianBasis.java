package solver;

import java.util.List;

import driver.Simulator;

/**
 * @author cauthon
 */
public class GaussianBasis extends ActivationFunction {

	private double sigma = .5;
	private List<Double> centerVector;
	private List<Double> xVector;
	private int totalGaussianFunctions;

	public GaussianBasis(List<Double> centerVector, int totalGaussianFunctions) {
		this.centerVector = centerVector;
		this.totalGaussianFunctions = totalGaussianFunctions;
	}

	public void setXVector(List<Double> xVector) {
		this.xVector = xVector;
	}

	public void setSigma() {
		sigma = Simulator.maxDist / totalGaussianFunctions;
	}

	@Override
	public double activate(double valueToActivate) {
		double value = Math.exp(-1 * Math.pow(getScalarEuclideanNorm(), 2) / (2 * Math.pow(sigma, 2)));
		return value;
	}

	public double getScalarEuclideanNorm() {
		double toRet = 0.0;
		for (int i = 0; i < centerVector.size(); i++) {
			toRet += Math.pow(xVector.get(i) - centerVector.get(i), 2);
		}
		if (Simulator.maxDist < toRet) {
			Simulator.maxDist = toRet;
		}
		toRet = Math.sqrt(toRet);
		return toRet;
	}

	@Override
	public double activationDerivative(double valueToActivate) {
		System.out.println("got here. bad.");
		return 0;
		// don't need to differentiate a gaussian
	}

}
