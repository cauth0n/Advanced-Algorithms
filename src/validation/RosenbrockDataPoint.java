package validation;

import java.util.List;

/**
 * Class which defines what a rosenbrock data point looks like. At this point in
 * time, it is no different from any other data point.
 * 
 * @author cauth0n
 * 
 */
public class RosenbrockDataPoint extends DataPoint {

	/**
	 * constructor.
	 * 
	 * @param targetOutput
	 *            target output of the data point
	 * @param inputValues
	 *            input vector of the data point.
	 */
	public RosenbrockDataPoint(double targetOutput, List<Double> inputValues) {
		super(targetOutput, inputValues);
	}

}
