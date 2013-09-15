package neural_net;

import java.util.List;

public class PerceptronNeuralNetwork extends NeuralNetworkType {

	public PerceptronNeuralNetwork(String neuralNetworkType, int numInputNeurons, int numConnectionsPerInputNeuron, List<Double> inputVector, int numOutputNeurons) {
		super(neuralNetworkType, numInputNeurons, numConnectionsPerInputNeuron, inputVector, numOutputNeurons);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildNetworkStructure() {
		// TODO Auto-generated method stub

	}

	@Override
	public NeuralNetworkType getNetworkType() {
		return this;
	}

	@Override
	public void feedForward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void solve(double targetOutput) {
		// TODO Auto-generated method stub
		
	}

}
