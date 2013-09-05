package neural_net;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer extends Neuron {
	

	private List<Connection> connections;


	public HiddenLayer(ActivationFunction activationFunction) {
		super(activationFunction);
		// TODO Auto-generated constructor stub
	}

	public List<Double> getWeightVector() {
		List<Double> weightVector = new ArrayList<>(connections.size());
		for (int i = 0; i < connections.size(); i++) {
			weightVector.add(connections.get(i).getWeight());
		}
		return weightVector;
	}

	public List<Connection> getConnections() {
		return connections;
	}

}
