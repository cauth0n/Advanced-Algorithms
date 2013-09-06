package neural_net;

public class FeedForwardNeuralNetwork extends NeuralNetworkType {

	public FeedForwardNeuralNetwork() {
		
	}

	@Override
	public void buildNetworkStructure() {
		neuralNetStructure = new MultiLayeredStructure(1, 1, 1);
		neuralNetStructure.constructLayers();
		((MultiLayeredStructure)neuralNetStructure).addHiddenLayer(1, 2, 1);
		neuralNetStructure.stitchLayersTogether();
	}

}
