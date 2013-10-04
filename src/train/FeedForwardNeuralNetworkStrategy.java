package train;

import java.util.List;

import neural_net.AbstractNeuralNetworkStructureFactory;
import validation.DataPoint;

/**
 * Class to define methods used in solving feed forward neural nets
 * 
 * @author cauthon
 */
public abstract class FeedForwardNeuralNetworkStrategy extends NeuralNetworkAlgorithmStrategy {

	protected AbstractNeuralNetworkStructureFactory neuralNetStructure;
	protected double alpha = 0;
	protected double eta = .2;
	protected double targetOutput;

	/**
	 * Constructor.
	 * 
	 * @param neuralNetStructure
	 *            structure of neural net
	 * @param alpha
	 *            momentum rate
	 * @param eta
	 *            learning rate
	 */
	public FeedForwardNeuralNetworkStrategy(AbstractNeuralNetworkStructureFactory neuralNetStructure) {
		this.neuralNetStructure = neuralNetStructure;
	}

	/**
	 * print structure to console
	 */
	public void print() {
		System.out.println(neuralNetStructure.toString());
	}

	/**
	 * Defining property of a feed forward network -- from input values, run
	 * them through the net.
	 * 
	 * @param inputValues
	 *            values to feed through net
	 */
	public abstract void feedForward(List<Double> inputValues);

	/**
	 * gets the output from the feed forward net. Assumes only ONE output
	 * neuron.
	 * 
	 * @return
	 */
	public double getNNOutput() {
		return neuralNetStructure.getLayers().get(neuralNetStructure.getLayers().size() - 1).getNeuronVector().get(0).getNeuronValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Tests the net by feeding data forward, then retuns output
	 * 
	 * @see
	 * solver.MachineLearningAlgorithmStrategy#mainTestLoop(validation.DataPoint
	 * )
	 */
	public double mainTestLoop(DataPoint d) {
		feedForward(d.getInputValues());
		return getNNOutput();
	}

}
