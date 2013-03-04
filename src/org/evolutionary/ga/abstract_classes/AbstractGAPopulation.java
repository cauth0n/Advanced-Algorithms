package org.evolutionary.ga.abstract_classes;

import java.util.Collection;

import org.evolutionary.interfaces.fitness.Fitness;
import org.evolutionary.interfaces.individual.Individual;
import org.evolutionary.interfaces.population.Population;

public abstract class AbstractGAPopulation implements Population {
	protected Collection<Individual> individuals;
	protected Fitness fitnessProcedure;

}
