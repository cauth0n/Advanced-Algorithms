package population;

import fitness.Fitness;
import individual.Individual;

import java.util.Collection;

public class RockPaperScissorPopulation implements Population {
	protected Collection<Individual> population;
	protected Fitness populationFitness;

	public RockPaperScissorPopulation() {

	}

	public RockPaperScissorPopulation(Collection<Individual> population) {
		this.population = population;
	}

	public void addIndividualToPopulation(Individual individual) {
		population.add(individual);
	}

	@Override
	public void instantiateEmptyArrayList(Individual individualType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void instantiateEmptySet(Individual individualType) {
		// TODO Auto-generated method stub

	}

}
