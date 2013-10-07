package neural_net;

import train.TrainingMethod;
import driver.MachineLearningModel;
import driver.MachineLearningModelStructure;

/**
 * Model for NNs. This is not the structure. Rather, it has a structure.
 * 
 * @author cauth0n
 * 
 */
public abstract class AbstractNeuralNetworkModelFactory implements MachineLearningModel {

	protected AbstractNeuralNetworkStructureFactory neuralNetworkStructure;
	protected NeuralNetworkStructuralInfo structuralInfo;
	protected TrainingMethod trainingMethod;


	public AbstractNeuralNetworkModelFactory(NeuralNetworkStructuralInfo structuralInfo) {
		this.structuralInfo = structuralInfo;
	}

	public String toString() {
		return neuralNetworkStructure.toString();
	}

	public MachineLearningModelStructure getModelStructure() {
		return neuralNetworkStructure;
	}

	public void buildModelStructure() {
		neuralNetworkStructure.buildStructure();
	}

}
