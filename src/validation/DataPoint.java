package validation;

import java.util.List;

public abstract class DataPoint {

	protected double targetOutput;
	protected List<Double> inputValues;

	public DataPoint(double targetOutput, List<Double> inputValues) {
		this.targetOutput = targetOutput;
		this.inputValues = inputValues;
	}

	public double getTargetOutput() {
		return targetOutput;
	}

	public List<Double> getInputValues() {
		return inputValues;
	}

	public String toString() {
		String toRet = "Input vector: {";
		for (Double d : inputValues) {
			toRet += d + ", ";
		}
		toRet += "} Target: " + targetOutput;
		return toRet;
	}

}
