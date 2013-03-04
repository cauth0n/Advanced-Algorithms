package org.evolutionary.ga.abstract_classes;

import java.util.Map;

import org.evolutionary.interfaces.fitness.Fitness;
import org.evolutionary.interfaces.individual.Individual;

public abstract class AbstractGAIndividual implements Individual {
	protected Map<String, Boolean> genome;
	protected double individualFitness;

	public AbstractGAIndividual(Map<String, Boolean> genome) {
		this.genome = genome;
	}

	public abstract Map<String, Boolean> getGenome();

	public double getFitnessValue(){
		return individualFitness;
	}
	public abstract void findFitness(Fitness fitnessProcedure);

}
