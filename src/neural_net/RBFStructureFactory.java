package neural_net;

import java.util.ArrayList;
import java.util.List;

import solver.GaussianFunction;
import solver.NeuronFunction;

public class RBFStructureFactory extends AbstractNeuralNetworkStructureFactory {

	public RBFStructureFactory(NeuralNetworkStructuralInfo structuralInfo) {
		super(structuralInfo);
	}

	@Override
	public void buildStructure() {
		constructInputOutputLayers();
		addHiddenLayers();

		stitchLayersTogether();
	}

	@Override
	public void addHiddenLayers() {
		for (int i = 1; i < structuralInfo.getNumHiddenLayers() + 1; i++) {
			List<NeuronFunction> hiddenNeuronFunctionality = new ArrayList<>();
			for (int j = 0; j < structuralInfo.getNumHiddenLayerNeurons(); j++) {
				hiddenNeuronFunctionality.add(new GaussianFunction());
			}
			layers.add(i, new RBFHiddenLayer(hiddenNeuronFunctionality, structuralInfo.getNumHiddenLayerNeurons()));
		}
	}
}
