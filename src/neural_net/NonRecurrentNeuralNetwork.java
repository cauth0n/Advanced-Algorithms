package neural_net;

import java.util.List;

import solver.ActivationFunction;

/**
 * @author cauthon
 */
public class NonRecurrentNeuralNetwork extends NeuralNetworkModel {

	public NonRecurrentNeuralNetwork(ActivationFunction activationFunction, int numInputNeurons, List<Double> inputVector, int numOutputNeurons,
			int numHiddenLayers, int numNeuronsPerHiddenLayer) {
		super(activationFunction, numInputNeurons, inputVector, numOutputNeurons, numHiddenLayers, numNeuronsPerHiddenLayer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildModelStructure() {
		neuralNetworkStructure = new MultiLayeredPerceptron(numInputNeurons, inputVector, numOutputNeurons, numHiddenLayers, numNeuronsPerHiddenLayer);
		constructInputOutputLayers();
		addHiddenLayers();
		stitchLayersTogether();

	}

	@Override
	public void constructInputOutputLayers() {
		neuralNetworkStructure.addInputLayer(); // layer 0
		neuralNetworkStructure.addOutputLayer(); // layer 1

	}

	public void addHiddenLayers() {
		for (int i = 1; i < numHiddenLayers + 1; i++) {
			neuralNetworkStructure.addHiddenLayer(i, new HiddenLayer(numNeuronsPerHiddenLayer));
		}
	}

	public void stitchLayersTogether() {
		for (int i = neuralNetworkStructure.getLayers().size() - 1; i >= 0; i--) {
			if (i == neuralNetworkStructure.getLayers().size() - 1) {// output,
																		// downStreamNeurons
				// are null.
				neuralNetworkStructure.getLayers().get(i).buildLayer(null);
			} else { // evertyhing else.
				neuralNetworkStructure.getLayers().get(i).buildLayer(neuralNetworkStructure.getLayers().get(i + 1).getNeuronVector());
			}
		}
	}

	public String toString() {
		return neuralNetworkStructure.toString();
	}

}
