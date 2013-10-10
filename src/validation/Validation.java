package validation;

import java.util.List;

/**
 * Validation class. Used to apply a method of validation.
 * 
 * @author cauth0n
 * 
 */
public abstract class Validation {

	protected List<DataPoint> dataPoints;
	protected int numDataPoints;
	protected List<DataPoint> trainingDataSet;
	protected List<DataPoint> testDataSet;
	protected double largestDataOut;

	/**
	 * Constructor.
	 * 
	 * @param numDataPoints
	 *            total number of data points across all sets (train and
	 *            validation)
	 * @param dataPointGenerator
	 *            how to generate data points
	 */
	public Validation(int numDataPoints) {
		this.numDataPoints = numDataPoints;

	}

	/**
	 * Skeleton method to define how to construct a cross validation method
	 */
	public abstract void divideDataPoints(List<DataPoint> allDataPoints);

	/**
	 * Getter method for the training set
	 */
	public abstract List<DataPoint> getTrainingSet();

	/**
	 * Getter method for the validation set
	 */
	public abstract List<DataPoint> getValidationSet();

}
