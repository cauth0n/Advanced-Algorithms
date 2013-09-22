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

	public KFoldCrossValidation(int numDataPoints, DataPointGenerator dataPointGenerator, int k) {
		super(numDataPoints, dataPointGenerator);
		this.k = k;
		dataPointDivision = new HashMap<Integer, List<DataPoint>>();
		testSetPointer = getTestSetPointer();
	}

	private int getTestSetPointer() {
		Random rand = new Random();
		return rand.nextInt(k);
	}

	@Override
	public void contructCrossValidationMethod() {
		for (int i = 0; i < k; i++) {
			dataPointDivision.put(i, new ArrayList<DataPoint>());
		}
		assignPoolOfDataPoints();
	}

	@Override
	public void assignPoolOfDataPoints() {
		int sizeOfMapValues = numDataPoints / k;

		Random rand = new Random();
		int i = 0;
		while (i < numDataPoints) {
			DataPoint d = dataPointGenerator.getNewDataPoint();
			int spot = rand.nextInt(k);

			List<DataPoint> currentDataList = dataPointDivision.get(spot);

			if (currentDataList.size() < sizeOfMapValues) {
				currentDataList.add(d);
				i++;
			}
		}
	}

	public List<DataPoint> getTrainingSet() {
		List<DataPoint> toRet = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			if (i != testSetPointer) {
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

}
