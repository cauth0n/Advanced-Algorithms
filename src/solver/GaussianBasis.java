package solver;

import java.util.List;

import driver.Simulator;

/**
 * Class to represent a GaussianBasis fucntion. The Gaussian has no derivative,
 * but is still pullingn from ActivationFunction. May require a refactoring in
 * the future.
 * 
 * @author cauthon
 */
public class GaussianBasis extends NeuronFunction {

	private double sigma = 5;
	private List<Double> centerVector;
	private List<Double> xVector;

	/**
	 * Constructor.
	 * 
	 * @param centerVector
	 *            the vector of the center of this Gaussian.
	 */
	public GaussianBasis(List<Double> centerVector) {
		this.centerVector = centerVector;
	}

	/**
	 * Setter for xVector, used in the Euclidean norm calculation
	 * 
	 * @param xVector
	 *            vector to set
	 */
	public void setXVector(List<Double> xVector) {
		this.xVector = xVector;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Runs the Gaussian with the input value. Not actually used here.
	 * 
	 * @see solver.ActivationFunction#activate(double)
	 */
	@Override
	public double activate(double valueToActivate) {
		double value = Math.exp(-1 * Math.pow(getScalarEuclideanNorm(), 2) / (2 * Math.pow(sigma, 2)));
		return value;
	}

	/**
	 * 
	 * Gets the euclidean norm between xVector and center, for each point in the
	 * vector.
	 * 
	 * @return
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * not used here.
	 * 
	 * @see solver.ActivationFunction#activationDerivative(double)
	 */
	@Override
	public double activationDerivative(double valueToActivate) {
		System.out.println("got here. bad.");
		return 0;
		// don't need to differentiate a gaussian
	}

	/**
	 * 
	 * Setter for centerVector
	 * 
	 * @param centerVector
	 *            vector to set centerVector to
	 */
	public void setCenterVector(List<Double> centerVector) {
		this.centerVector = centerVector;
	}

}
