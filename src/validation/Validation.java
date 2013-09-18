package validation;

import java.util.Collection;

public abstract class Validation {

	protected Collection<DataPoint> dataPoints;
	protected int numDataPoints;
	protected DataPointGenerator dataPointGenerator;
	protected Collection<DataPoint> trainingDataSet;
	protected Collection<DataPoint> testDataSet;

	public Validation(int numDataPoints, DataPointGenerator dataPointGenerator) {
		this.numDataPoints = numDataPoints;
		this.dataPointGenerator = dataPointGenerator;
	}

	public abstract void assignPoolOfDataPoints();

	public abstract void constructCrossValidationMethod();

}
