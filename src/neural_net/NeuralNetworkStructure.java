package neural_net;

import java.util.ArrayList;
import java.util.List;

import driver.MachineLearningModelStructure;

public abstract class NeuralNetworkStructure implements MachineLearningModelStructure {

	protected List<Layer> layers;

	protected InputLayer inputLayer;
	protected OutputLayer outputLayer;

	protected int numInputNeurons;
	protected int numOutputNeurons;

	public NeuralNetworkStructure(int numInputNeurons, List<Double> inputVector, int numOutputNeurons) {
		layers = new ArrayList<>();
		inputLayer = new InputLayer(numInputNeurons, inputVector);
		outputLayer = new OutputLayer(numOutputNeurons);
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public NeuralNetworkStructure getNetworkStructure() {
		return this;
	}

	public void addInputLayer() {
		layers.add(inputLayer);
	}

	public void addOutputLayer() {
		layers.add(outputLayer);
	}

	public void addHiddenLayer(int position, HiddenLayer layerToAdd) {
		layers.add(position, layerToAdd);
	}

	@Override
	public String toString() {
		String retVal = "";
		for (Layer l : layers) {
			retVal += l.toString() + "\n";
		}
		return retVal;
	}

}
