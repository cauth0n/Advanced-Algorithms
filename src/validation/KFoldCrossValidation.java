package validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class KFoldCrossValidation extends Validation {

	private int k;
	private int testSetPointer;
	private Map<Integer, Collection<DataPoint>> dataPointDivision;

	public KFoldCrossValidation(int numDataPoints, DataPointGenerator dataPointGenerator, int k) {
		super(numDataPoints, dataPointGenerator);
		this.k = k;
		dataPoints = new ArrayList<>();
		testDataSet = new ArrayList<>();
		trainingDataSet = new ArrayList<>();
		dataPointDivision = new HashMap<Integer, Collection<DataPoint>>();
		testSetPointer = getTestSetPointer();
	}

	public int getTestSetPointer() {
		Random rand = new Random();
		return rand.nextInt(k);
	}

	@Override
	public void assignPoolOfDataPoints() {
		for (int i = 0; i < numDataPoints; i++) {
			dataPoints.add(dataPointGenerator.getNewDataPoint());
		}
	}

	@Override
	public void constructCrossValidationMethod() {

	}

}
