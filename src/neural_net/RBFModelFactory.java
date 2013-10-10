package neural_net;

import solver.RadialBasisTraining;
import solver.Solver;
import solver.TestingMethod;
import solver.TrainingMethod;

public class RBFModelFactory extends AbstractNeuralNetworkModelFactory {

	public RBFModelFactory(NeuralNetworkStructuralInfo structuralInfo) {
		super(structuralInfo);
		neuralNetworkStructure = new RBFStructureFactory(structuralInfo);
	}

	@Override
	public void initializeSolver() {
		TrainingMethod trainingMethod = new RadialBasisTraining(
				neuralNetworkStructure);
		TestingMethod testingMethod = new TestingMethod();
		solver = new Solver();
		solver.setTestingMethod(testingMethod);
		solver.setTrainingMethod(trainingMethod);
	}
}
