package main;

import fitness.Fitness;
import individual.Individual;
import population.Population;

public abstract class Simulator {

	public Simulator() {

	}

	public abstract Individual ga(Population initialPopulation, Fitness fitness);

	public abstract Individual reproduce(Individual x, Individual y);

	public abstract Population buildEmptyPopulation();

}
