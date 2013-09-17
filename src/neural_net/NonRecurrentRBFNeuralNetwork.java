package neural_net;

import java.util.List;

import solver.ActivationFunction;

/**
 * @author cauthon
 */
public class NonRecurrentRBFNeuralNetwork extends NeuralNetworkModel {
	public static ActivationFunction hiddenBasisFunction;
	private int numNeuronsPerHiddenLayer;

	public NonRecurrentRBFNeuralNetwork(ActivationFunction outputActivationFunction, int numInputNeurons, List<Double> inputVector,
			int numOutputNeurons, int numNeuronsPerHiddenLayer, ActivationFunction hiddenBasisFunction) {
		super(outputActivationFunction, numInputNeurons, inputVector, numOutputNeurons);
		NonRecurrentRBFNeuralNetwork.hiddenBasisFunction = hiddenBasisFunction;
		this.numNeuronsPerHiddenLayer = numNeuronsPerHiddenLayer;
	}

	@Override
	public void buildModelStructure() {
		neuralNetworkStructure = new MultiLayeredPerceptron(numInputNeurons, inputVector, numOutputNeurons, 1, numNeuronsPerHiddenLayer);
		constructInputOutputLayers();
		addHiddenLayer();
		stitchLayersTogether();
	}

	public void addHiddenLayer() {
		neuralNetworkStructure.addHiddenLayer(1, new RBFHiddenLayer(numNeuronsPerHiddenLayer));
	}

}
