package validation;

import java.util.List;
import java.util.Random;

/**
 * Class to define how a data point generator should be set up.
 * 
 * @author cauth0n
 * 
 */
public abstract class DataPointGenerator {

	/**
	 * Method to get a +- random value in the inputted range.
	 * 
	 * @param range
	 *            +- range of value to get [-range, range]
	 * @return random value in range.
	 */
	protected double getRandomValueInRange(double range) {
		Random rand = new Random();
		double returnValue = 0.0;
		if (rand.nextBoolean()) {
			returnValue = range * rand.nextDouble();
		} else {
			returnValue = -1 * range * rand.nextDouble();
		}
		return returnValue;
	}

	/**
	 * Skeleton method to grab a new DataPoint object. Consider refactoring to a
	 * factory.
	 * 
	 * @return
	 */
	public abstract DataPoint getNewDataPoint();

	/**
	 * Finds the output of a function from the inputted vector
	 * 
	 * @param inputValues
	 *            vector of values of which we want an output for.
	 * @return function's output
	 */
	public abstract double getFunctionOutput(List<Double> inputValues);

}
