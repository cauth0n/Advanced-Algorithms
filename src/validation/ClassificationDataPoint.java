package validation;

import java.util.List;

public abstract class ClassificationDataPoint implements DataPoint{

	private String targetOutput;
	private List<String> inputValues;

	public ClassificationDataPoint() {

	}

	public String getTargetOutput() {
		return targetOutput;
	}

	public List<String> getInputValues() {
		return inputValues;
	}

	public abstract void format(String stringToFormat);
}
