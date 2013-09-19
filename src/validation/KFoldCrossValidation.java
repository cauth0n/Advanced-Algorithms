package validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class KFoldCrossValidation extends Validation {

	private int k;
	private int testSetPointer;
	private Map<Integer, List<DataPoint>> dataPointDivision;
	private List<DataPoint> duplicateDataPoints;

	public KFoldCrossValidation(int numDataPoints, DataPointGenerator dataPointGenerator, int k) {
		super(numDataPoints, dataPointGenerator);
		this.k = k;
		dataPoints = new ArrayList<>();
		duplicateDataPoints = new ArrayList<>();
		testDataSet = new ArrayList<>();
		trainingDataSet = new ArrayList<>();
		dataPointDivision = new HashMap<Integer, List<DataPoint>>();
		testSetPointer = getTestSetPointer();
	}

	private int getTestSetPointer() {
		Random rand = new Random();
		return rand.nextInt(k);
	}

	@Override
	public void assignPoolOfDataPoints() {
		for (int i = 0; i < numDataPoints; i++) {
			DataPoint d = dataPointGenerator.getNewDataPoint();
			dataPoints.add(d);
			duplicateDataPoints.add(d);
		}
	}

	public List<DataPoint> getTrainingSet() {
		List<DataPoint> toRet = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			if (i == testSetPointer) {
				continue;
			} else {
				for (DataPoint d : dataPointDivision.get(i)) {
					toRet.add(d);
				}
			}
		}
		return toRet;
	}

	public List<DataPoint> getTestSet() {
		List<DataPoint> toRet = dataPointDivision.get(testSetPointer);
		testSetPointer++;
		testSetPointer = testSetPointer % k;
		return toRet;
	}

	@Override
	public void constructCrossValidationMethod() {
		for (int i = 0; i < k; i++) {
			dataPointDivision.put(i, getRandomizedList());
		}
	}

	private List<DataPoint> getRandomizedList() {
		List<DataPoint> toRet = new ArrayList<>();
		Random rand = new Random();
		int duplicateSize = duplicateDataPoints.size() - 1;

		while (duplicateSize >= 0) {
			DataPoint p = duplicateDataPoints.get(rand.nextInt(duplicateDataPoints.size()));
			toRet.add(p);
			duplicateDataPoints.remove(p);
			duplicateSize--;
		}

		return toRet;
	}
}
