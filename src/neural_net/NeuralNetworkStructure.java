package neural_net;

import java.util.ArrayList;
import java.util.List;

import driver.MachineLearningModelStructure;

/**
 * Abstract view of the structure of a NN
 * 
 * @author cauth0n
 * 
 */
public abstract class NeuralNetworkStructure implements MachineLearningModelStructure {

	protected List<Layer> layers;

	protected InputLayer inputLayer;
	protected OutputLayer outputLayer;

	protected int numInputNeurons;
	protected int numOutputNeurons;

	/**
	 * Constructor.
	 * 
	 * @param numInputNeurons
	 * @param inputVector
	 * @param numOutputNeurons
	 */
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

	/**
	 * Adds an input layer
	 */
	public void addInputLayer() {
		layers.add(inputLayer);
	}

	/**
	 * Adds an output layer.
	 */
	public void addOutputLayer() {
		layers.add(outputLayer);
	}

	/**
	 * Adds a hidden layer at the specified position
	 * 
	 * @param position
	 * @param layerToAdd
	 */
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
