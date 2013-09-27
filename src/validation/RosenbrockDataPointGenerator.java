package validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for generating data points from the rosenbrock function
 * 
 * @author cauth0n
 * 
 */
public class RosenbrockDataPointGenerator extends DataPointGenerator {
	private int vectorSize;
	private double inputRange;

	/**
	 * Constructor.
	 * 
	 * @param vectorSize
	 *            vector that inputs in the rosenbrock function
	 * @param inputRange
	 *            +- real number range in which data points are randomly chosen
	 *            from
	 */
	public RosenbrockDataPointGenerator(int vectorSize, double inputRange) {
		this.vectorSize = vectorSize;
		this.inputRange = inputRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see validation.DataPointGenerator#getNewDataPoint() gets a new data
	 * point from the input's range
	 */
	@Override
	public RosenbrockDataPoint getNewDataPoint() {
		RosenbrockDataPoint dataPoint;
		List<Double> inputValues = new ArrayList<>(vectorSize);
		double functionOutput;

		for (int i = 0; i < vectorSize; i++) {
			inputValues.add(new Double(getRandomValueInRange(inputRange)));
		}

		functionOutput = getFunctionOutput(inputValues);
		dataPoint = new RosenbrockDataPoint(functionOutput, inputValues);
		return dataPoint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see validation.DataPointGenerator#getFunctionOutput(java.util.List) run
	 * the rosenbrock function on me, return the output value.
	 */
	@Override
	public double getFunctionOutput(List<Double> inputValues) {
		double functionOutput = 0.0;
		for (int i = 0; i < vectorSize - 1; i++) {
			functionOutput += (Math.pow(1 - inputValues.get(i), 2) + 100 * Math.pow(inputValues.get(i + 1) - Math.pow(inputValues.get(i), 2), 2));
		}
		return functionOutput;
	}
}
