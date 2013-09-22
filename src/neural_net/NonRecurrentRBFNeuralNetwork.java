package neural_net;

import java.util.List;

import solver.ActivationFunction;
import solver.GaussianBasis;

/**
 * @author cauthon
 */
public class NonRecurrentRBFNeuralNetwork extends NeuralNetworkModel {
	private ActivationFunction hiddenBasisFunction;
	private int numNeuronsPerHiddenLayer;

	public NonRecurrentRBFNeuralNetwork(ActivationFunction outputActivationFunction, int numInputNeurons, List<Double> inputVector,
			int numOutputNeurons, int numNeuronsPerHiddenLayer, GaussianBasis hiddenBasisFunction) {
		super(outputActivationFunction, numInputNeurons, inputVector, numOutputNeurons);
		this.hiddenBasisFunction = hiddenBasisFunction;
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
		neuralNetworkStructure.addHiddenLayer(1, new RBFHiddenLayer(numNeuronsPerHiddenLayer, hiddenBasisFunction));
	}

}
