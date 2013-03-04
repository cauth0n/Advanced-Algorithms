package org.evolutionary.interfaces.simulator;

import org.evolutionary.interfaces.individual.Individual;
import org.evolutionary.interfaces.population.Population;

public interface Simulator {

	public abstract Individual reproduce(Individual x, Individual y);

	public abstract Population buildEmptyPopulation();

}
