package solver;

import java.util.ArrayList;
import java.util.List;

/**
 * Linear Regression class. simply finds linear regression in the form of: y =
 * ax+b
 * 
 * used by stopping condition
 * 
 * @author cauthon
 */
public class LinearRegression {

	private List<Double> errors;

	/**
	 * Constructor. Only sets up an array of error values.
	 * 
	 */
	public LinearRegression() {
		errors = new ArrayList<>();
	}

	/**
	 * Start over with errors, clearing them.
	 * 
	 */
	public void clearErrorList() {
		errors.clear();
	}

	/**
	 * adds an error to the list
	 * 
	 * @param error
	 */
	public void addError(double error) {
		errors.add(error);
	}

	/**
	 * Treats all errors as points, and their placement in a List<> as the time
	 * in which that error happens. Then, returns the slope of errors over time.
	 * 
	 * @return slope of errors over their position in list of errors
	 */
	public double getLinearRegressionSlope() {
		double b = 0.0;
		double sumX = 0.0;
		double sumXSquared = 0.0;
		double sumY = 0.0;
		int n = errors.size();
		double sumXY = 0.0;
		for (int i = 0; i < errors.size(); i++) {
			int x = i + 1;
			sumX += x;
			sumXSquared += Math.pow(x, 2);
			sumY += errors.get(i);
			sumXY += (x * errors.get(i));
		}
		b = ((n * sumXY) - (sumX * sumY)) / (n * sumXSquared - Math.pow(sumX, 2));
		return b;
	}
}
