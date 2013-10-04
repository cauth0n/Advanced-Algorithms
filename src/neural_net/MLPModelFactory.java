package neural_net;

public class MLPModelFactory extends AbstractNeuralNetworkModelFactory {

	public MLPModelFactory(NeuralNetworkStructuralInfo structuralInfo) {
		super(structuralInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildModelStructure() {
		neuralNetworkStructure = new MLPStructureFactory(structuralInfo);
	}

}
