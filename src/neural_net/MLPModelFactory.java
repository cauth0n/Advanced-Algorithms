package neural_net;

import solver.GradientDescentTraining;
import solver.Solver;
import solver.TestingMethod;
import solver.TrainingMethod;

public class MLPModelFactory extends AbstractNeuralNetworkModelFactory {

	public MLPModelFactory(NeuralNetworkStructuralInfo structuralInfo) {
		super(structuralInfo);
		neuralNetworkStructure = new MLPStructureFactory(structuralInfo);
	}

	@Override
	public void initializeSolver() {
		TrainingMethod trainingMethod = new GradientDescentTraining(
				neuralNetworkStructure);
		TestingMethod testingMethod = new TestingMethod();
		solver = new Solver();
		solver.setTestingMethod(testingMethod);
		solver.setTrainingMethod(trainingMethod);
	}
}
