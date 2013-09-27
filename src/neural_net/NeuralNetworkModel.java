package neural_net;

import java.util.List;

import solver.ActivationFunction;
import driver.MachineLearningModel;
import driver.MachineLearningModelStructure;

/**
 * Model for NNs. This is not the structure. Rather, it has a structure.
 * 
 * @author cauth0n
 * 
 */
public abstract class NeuralNetworkModel implements MachineLearningModel {

	public static ActivationFunction activationFunction;

	protected NeuralNetworkStructure neuralNetworkStructure;
	protected int numInputNeurons;
	protected List<Double> inputVector;
	protected int numOutputNeurons;

	/**
	 * Constructor.
	 * 
	 * @param activationFunction
	 * @param numInputNeurons
	 * @param inputVector
	 * @param numOutputNeurons
	 */
	public NeuralNetworkModel(ActivationFunction activationFunction, int numInputNeurons, List<Double> inputVector, int numOutputNeurons) {
		NeuralNetworkModel.activationFunction = activationFunction;

		this.numInputNeurons = numInputNeurons;
		this.inputVector = inputVector;
		this.numOutputNeurons = numOutputNeurons;

	}

	public String toString() {
		return neuralNetworkStructure.toString();
	}

	public MachineLearningModelStructure getModelStructure() {
		return neuralNetworkStructure;
	}

	/**
	 * Once the layers are made, they are 'stitched' together here. this makes
	 * it so layers can be iterated through
	 * 
	 */
	public void stitchLayersTogether() {
		for (int i = neuralNetworkStructure.getLayers().size() - 1; i >= 0; i--) {
			if (i == neuralNetworkStructure.getLayers().size() - 1) {// output,`
																		// downStreamNeurons
				// are null.
				neuralNetworkStructure.getLayers().get(i).buildLayer(null);
			} else { // evertyhing else.
				neuralNetworkStructure.getLayers().get(i).buildLayer(neuralNetworkStructure.getLayers().get(i + 1).getNeuronVector());
			}
		}
	}

	/**
	 * Must be called. Builds input and output layers.
	 */
	public void constructInputOutputLayers() {
		neuralNetworkStructure.addInputLayer(); // layer 0
		neuralNetworkStructure.addOutputLayer(); // layer 1

	}

}
