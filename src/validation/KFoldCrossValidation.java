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
	public KFoldCrossValidation(int numDataPoints, DataPointGenerator dataPointGenerator, int k) {
		super(numDataPoints, dataPointGenerator);
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
	public void contructCrossValidationMethod() {
		for (int i = 0; i < k; i++) {
			dataPointDivision.put(i, new ArrayList<DataPoint>());
		}
		assignPoolOfDataPoints();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * generates data points using the data point generator. Partitions data
	 * points into sets, which fill the folds of the hash.
	 * 
	 * @see validation.Validation#assignPoolOfDataPoints()
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * Normalize outputs R --> [0,1]
	 * 
	 * Stores a maximum value
	 * 
	 * @see validation.Validation#normalizeOutputs()
	 */
	@Override
	public void normalizeOutputs() {
		double maxTarget = 0.0;
		for (List<DataPoint> listD : dataPointDivision.values()) {
			for (DataPoint d : listD) {
				maxTarget = Math.max(maxTarget, Math.abs(d.getTargetOutput()));
			}
		}
		largestDataOut = maxTarget;
		for (List<DataPoint> listD : dataPointDivision.values()) {
			for (DataPoint d : listD) {
				d.setNormalizedOutput(d.getTargetOutput() / maxTarget);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Reverses the normalization process [0,1] --> R
	 * 
	 * @see validation.Validation#deNormalize(double)
	 */
	@Override
	public double deNormalize(double valToDeNormalize) {
		return valToDeNormalize * largestDataOut;

	}

}
