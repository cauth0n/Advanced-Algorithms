package validation;

import java.util.ArrayList;
import java.util.List;

public abstract class Validation {

	protected List<DataPoint> dataPoints;
	protected DataPointGenerator dataPointGenerator;
	protected List<DataPoint> trainingDataSet;
	protected List<DataPoint> testDataSet;

	public Validation(DataPointGenerator dataPointGenerator) {
		this.dataPointGenerator = dataPointGenerator;
		dataPoints = new ArrayList<>();
		trainingDataSet = new ArrayList<>();
		testDataSet = new ArrayList<>();
	}

	public abstract void generatorDataPoints();

}
