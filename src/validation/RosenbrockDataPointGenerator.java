package validation;

public class RosenbrockDataPointGenerator extends DataPointGenerator {

	public RosenbrockDataPointGenerator() {

	}

	@Override
	public DataPoint getNextDataPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	public int runFunction(int n) {
		int result = 0;

		for (int i = n - 1; i >= 1; i--) {
			result += (Math.pow(1 - i, 2) + 100 * Math.pow((i + 1) - Math.pow(i, 2), 2));
		}

		return result;
	}
}
