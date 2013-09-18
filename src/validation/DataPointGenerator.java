package validation;

import java.util.List;

public abstract class DataPointGenerator {

	protected abstract double getRandomValueInRange(double range);

	public abstract DataPoint getNewDataPoint();

	public abstract double getFunctionOutput(List<Double> inputValues);

}
