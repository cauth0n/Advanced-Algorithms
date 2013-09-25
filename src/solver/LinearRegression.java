package solver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cauthon
 */
public class LinearRegression {

	private double slope;
	private double size;
	private List<Double> errors;

	public LinearRegression(double size) {
		this.size = size;
		errors = new ArrayList<>();
	}

	public void clearErrorList() {
		errors.clear();
	}

	public void addError(double error) {
		errors.add(error);
	}

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
