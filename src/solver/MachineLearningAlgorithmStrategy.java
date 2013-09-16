package solver;

/**
 * @author cauthon
 */
public interface MachineLearningAlgorithmStrategy {

	public abstract void solve(double targetOutput);

	public abstract void solvingChain(double targetOutput);

}
