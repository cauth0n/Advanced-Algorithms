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
	protected DataPointGenerator dataPointGenerator;
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
	public Validation(int numDataPoints, DataPointGenerator dataPointGenerator) {
		this.numDataPoints = numDataPoints;
		this.dataPointGenerator = dataPointGenerator;
	}

	/**
	 * Skeleton method to define how to assign a pool of data points
	 */
	public abstract void assignPoolOfDataPoints();

	/**
	 * Skeleton method to define how to construct a cross validation method
	 */
	public abstract void contructCrossValidationMethod();

	/**
	 * Getter method for the training set
	 */
	public abstract List<DataPoint> getTrainingSet();

	/**
	 * Getter method for the validation set
	 */
	public abstract List<DataPoint> getValidationSet();

	/**
	 * method to normalize all output data points R --> [0,1]
	 */
	public abstract void normalizeOutputs();

	/**
	 * method to reverse the normalization method
	 */
	public abstract double deNormalize(double valToDeNormalize);

}
