package validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RosenbrockDataPointGenerator extends DataPointGenerator {
	private int vectorSize;
	private int inputRange;

	public RosenbrockDataPointGenerator(int vectorSize, int inputRange) {
		this.vectorSize = vectorSize;
		this.inputRange = inputRange;
	}

	@Override
	protected double getRandomValueInRange(double range) {
		Random rand = new Random();
		double returnValue = 0.0;
		if (rand.nextBoolean()) {
			returnValue = range * rand.nextDouble();
		} else {
			returnValue = -1 * range * rand.nextDouble();
		}
		return returnValue;
	}

	@Override
	public RosenbrockDataPoint getNewDataPoint() {
		RosenbrockDataPoint dataPoint;
		List<Double> inputValues = new ArrayList<>(vectorSize);
		double functionOutput;

		for (int i = 0; i < vectorSize; i++) {
			inputValues.add(new Double(getRandomValueInRange(inputRange)));
		}

		functionOutput = getFunctionOutput(inputValues);
		dataPoint = new RosenbrockDataPoint(functionOutput, inputValues);
		return dataPoint;
	}

	@Override
	public double getFunctionOutput(List<Double> inputValues) {
		double functionOutput = 0.0;
		for (int i = 0; i < vectorSize - 1; i++) {
			functionOutput += (Math.pow(1 - inputValues.get(i), 2) + 100 * Math.pow(inputValues.get(i + 1) - Math.pow(inputValues.get(i), 2), 2));
		}
		return functionOutput;
	}
}
