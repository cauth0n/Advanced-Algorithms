package neural_net;

public interface NeuralNetwork {

	public void buildNetworkStructure();

	public void feedForward();

	public void solve(double targetOutput);

	public NeuralNetworkType getNetworkType();

}
