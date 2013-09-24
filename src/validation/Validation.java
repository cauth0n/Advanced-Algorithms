package validation;

import java.util.List;

public abstract class Validation {

	protected List<DataPoint> dataPoints;
	protected int numDataPoints;
	protected DataPointGenerator dataPointGenerator;
	protected List<DataPoint> trainingDataSet;
	protected List<DataPoint> testDataSet;

	public Validation(int numDataPoints, DataPointGenerator dataPointGenerator) {
		this.numDataPoints = numDataPoints;
		this.dataPointGenerator = dataPointGenerator;
	}

	public abstract void assignPoolOfDataPoints();

	public abstract void contructCrossValidationMethod();

	public abstract List<DataPoint> getTrainingSet();

	public abstract List<DataPoint> getTestSet();

	public abstract void normalizeOutputs();

}
