package neural_net;

import java.util.ArrayList;
import java.util.List;

import driver.Simulator;

public class OutputLayer extends Layer {
	private List<Connection> connections;
	private int numConnectionsPerNeuron;

	public OutputLayer(String layerType, int numNeurons, int numConnectionsPerNeuron) {
		super(layerType, numNeurons);
		this.numConnectionsPerNeuron = numConnectionsPerNeuron;
		connections = new ArrayList<>(numConnectionsPerNeuron * numNeurons);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void buildLayer(List<Neuron> upStreamNeurons) {
		if (upStreamNeurons.size() != numConnectionsPerNeuron) {
			System.out.println("Incoming connections with incoming neurons were not equal. Critical error.");
		}
		for (int i = 0; i < neurons.size(); i++) {
			neurons.add(new DataNeuron(Simulator.activationFunction));
		}

		for (int i = 0; i < neurons.size(); i++) {
			for (int j = 0; j < numConnectionsPerNeuron; j++) {
				connections.add(new Connection(upStreamNeurons.get(j), null, initialWeight));
			}
		}

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
