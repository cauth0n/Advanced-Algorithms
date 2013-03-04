package org.evolutionary.ga.concrete_classes;

import java.util.Map;

import org.evolutionary.ga.abstract_classes.AbstractGAIndividual;
import org.evolutionary.interfaces.fitness.Fitness;

public class GAIndividual extends AbstractGAIndividual {

	public GAIndividual(Map<String, Boolean> genome) {
		super(genome);
	}

	@Override
	public Map<String, Boolean> getGenome() {
		return genome;
	}

	@Override
	public void findFitness(Fitness fitnessProcedure) {
		individualFitness = fitnessProcedure.getFitness();
	}
}
