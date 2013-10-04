package neural_net;

/**
 * MLP class, holding everything a perceptron does, but with a numhidden layers
 * variable.
 * 
 * 
 * I may need to add more to this class as I get further.
 * 
 * @author cauth0n
 * 
 * 
 * 
 */
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

}
