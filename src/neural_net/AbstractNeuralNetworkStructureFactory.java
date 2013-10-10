package neural_net;

import java.util.ArrayList;
import java.util.List;

import solver.LinearNeuronFunction;
import solver.NeuronFunction;
import solver.SigmoidNeuronFunction;
import driver.MachineLearningModelStructure;

/**
 * Abstract view of the structure of a NN
 * 
 * @author cauth0n
 * 
 */
public abstract class AbstractNeuralNetworkStructureFactory implements
		MachineLearningModelStructure {

	protected List<Layer> layers;
	protected InputLayer inputLayer;
	protected OutputLayer outputLayer;
	protected NeuralNetworkStructuralInfo structuralInfo;

	public AbstractNeuralNetworkStructureFactory(
			NeuralNetworkStructuralInfo structuralInfo) {
		this.structuralInfo = structuralInfo;
		layers = new ArrayList<>();
	}

	public List<Layer> getLayers() {
		return layers;
	}

	/**
	 * Adds an input layer
	 */
	public void addInputLayer(List<NeuronFunction> inputNeuronFunctionality) {
		inputLayer = new InputLayer(inputNeuronFunctionality,
				structuralInfo.getNumInputNeurons());
		layers.add(inputLayer);
	}

	/**
	 * Adds an output layer.
	 */
	public void addOutputLayer(List<NeuronFunction> outputNeuronFunctionality) {
		outputLayer = new OutputLayer(outputNeuronFunctionality,
				structuralInfo.getNumOutputNeurons());
		layers.add(outputLayer);
	}

	/**
	 * Once the layers are made, they are 'stitched' together here. this makes
	 * it so layers can be iterated through
	 * 
	 */
	public void stitchLayersTogether() {
		for (int i = layers.size() - 1; i >= 0; i--) {
			if (i == layers.size() - 1) {
				// output, downStreamNeurons are null.
				layers.get(i).buildLayer(null);
			} else {
				// evertyhing else.
				layers.get(i).buildLayer(layers.get(i + 1).getNeuronVector());
			}
		}
	}

	/**
	 * Must be called. Builds input and output layers.
	 */
	public void constructInputOutputLayers() {
		List<NeuronFunction> inputNeuronFunctionality = new ArrayList<>();
		List<NeuronFunction> outputNeuronFunctionality = new ArrayList<>();
		for (int i = 0; i < structuralInfo.getNumInputNeurons(); i++) {
			inputNeuronFunctionality.add(new LinearNeuronFunction());
		}

		for (int i = 0; i < structuralInfo.getNumOutputNeurons(); i++) {
			outputNeuronFunctionality.add(new SigmoidNeuronFunction());
		}

		addInputLayer(inputNeuronFunctionality); // layer 0
		addOutputLayer(outputNeuronFunctionality); // layer 1
	}

	public abstract void addHiddenLayers();

	@Override
	public String toString() {
		String retVal = "";
		for (Layer l : layers) {
			retVal += l.toString() + "\n";
		}
		return retVal;
	}

}
