package neural_net;

import train.GradientDescentStrategy;

public class MLPModelFactory extends AbstractNeuralNetworkModelFactory {

	public MLPModelFactory(NeuralNetworkStructuralInfo structuralInfo) {
		super(structuralInfo);
		neuralNetworkStructure = new MLPStructureFactory(structuralInfo);
		trainingMethod = new GradientDescentStrategy(neuralNetworkStructure);
	}
}
