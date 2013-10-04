package neural_net;

import java.util.ArrayList;
import java.util.List;

import solver.NeuronFunction;
import solver.SigmoidNeuronFunction;

public class MLPStructureFactory extends AbstractNeuralNetworkStructureFactory {

	public MLPStructureFactory(NeuralNetworkStructuralInfo structuralInfo) {
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
				hiddenNeuronFunctionality.add(new SigmoidNeuronFunction());
			}
			layers.add(i, new MLPHiddenLayer(hiddenNeuronFunctionality, structuralInfo.getNumHiddenLayerNeurons()));
		}
	}
}
