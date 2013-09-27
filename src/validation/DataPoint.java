package validation;

import java.util.List;

/**
 * Class to define how a DataPoint looks.
 * 
 * @author cauth0n
 * 
 */
public abstract class DataPoint {

	protected double normalizedOutput;
	protected double targetOutput;
	protected List<Double> inputValues;

	/**
	 * Constructor.
	 * 
	 * @param targetOutput
	 *            target output of this data point. Must be known before this
	 *            object is created.
	 * @param inputValues
	 *            vector of input values into this data point.
	 */
	public DataPoint(double targetOutput, List<Double> inputValues) {
		this.targetOutput = targetOutput;
		this.inputValues = inputValues;
	}

	/**
	 * getter for target output
	 * 
	 * @return targetoutput
	 */
	public double getTargetOutput() {
		return targetOutput;
	}

	/**
	 * getter for input vector
	 * 
	 * @return input vector
	 * 
	 */
	public List<Double> getInputValues() {
		return inputValues;
	}

	/**
	 * getter for normalized output of this data point. R --> [0,1]
	 * 
	 * @return normalized output
	 */
	public double getNormalizedOutput() {
		return normalizedOutput;
	}

	/**
	 * Setter for normalized output.
	 * 
	 * @param normalizedOutput
	 *            normalized output to set
	 */
	public void setNormalizedOutput(double normalizedOutput) {
		this.normalizedOutput = normalizedOutput;
	}

	/*
	 * (non-Javadoc) To string.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String toRet = "Input vector: {";
		for (Double d : inputValues) {
			toRet += d + ", ";
		}
		toRet += "} Target: " + targetOutput;
		return toRet;
	}

}
