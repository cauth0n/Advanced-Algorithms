package validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * K fold cross validation class. Contains all methods necessary to use k-fold
 * cross validation on a data set.
 * 
 * @author cauth0n
 * 
 */
public class KFoldCrossValidation extends Validation {

	private int k;
	private int validationSetPointer;
	private Map<Integer, List<DataPoint>> dataPointDivision;

	/**
	 * Constructor
	 * 
	 * @param numDataPoints
	 *            total number of data points. This class will generate all data
	 *            points.
	 * @param dataPointGenerator
	 *            how to generate these data points. Could refactor this into a
	 *            factory.
	 * @param k
	 *            number of 'folds' to have
	 */
	public KFoldCrossValidation(int numDataPoints, int k) {
		super(numDataPoints);
		this.k = k;
		dataPointDivision = new HashMap<Integer, List<DataPoint>>();
		validationSetPointer = getValidationSetPointer();
	}

	/**
	 * Initializes the pointer to the validation set randomly. private.
	 * 
	 * @return random number, which points to a spot in teh hash.
	 */
	private int getValidationSetPointer() {
		Random rand = new Random();
		return rand.nextInt(k);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * sets up data pool so that k-fold cross validation can work on pool
	 * 
	 * @see validation.Validation#contructCrossValidationMethod()
	 */
	@Override
	public void divideDataPoints(List<DataPoint> allDataPoints) {
		for (int i = 0; i < k; i++) {
			dataPointDivision.put(i, new ArrayList<DataPoint>());
		}

		Random rand = new Random();
		int i = 0;
		while (i < numDataPoints) {
			for (DataPoint d : allDataPoints) {
				int spot = rand.nextInt(k);
				List<DataPoint> currentDataList = dataPointDivision.get(spot);
				currentDataList.add(d);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * getter method to return training set.
	 * 
	 * @see validation.Validation#getTrainingSet()
	 */
	public List<DataPoint> getTrainingSet() {
		List<DataPoint> toRet = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			if (i != validationSetPointer) {
				for (DataPoint d : dataPointDivision.get(i)) {
					toRet.add(d);
				}
			}
		}
		return toRet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Getter method to return the validation set. The hash's set at elemetn
	 * pointer is returned. then pointer is incremented.
	 * 
	 * @see validation.Validation#getValidationSet()
	 */
	public List<DataPoint> getValidationSet() {
		List<DataPoint> toRet = dataPointDivision.get(validationSetPointer);
		validationSetPointer++;
		validationSetPointer = validationSetPointer % k;
		return toRet;
	}

}
