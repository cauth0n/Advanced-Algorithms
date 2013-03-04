package org.evolutionary.ga.abstract_classes;

import java.util.HashMap;
import java.util.Map;

import org.evolutionary.ga.concrete_classes.GAIndividual;
import org.evolutionary.interfaces.individual.Individual;
import org.evolutionary.interfaces.reproduce.Reproduce;

public abstract class AbstractGAReproduce implements Reproduce {

	public Individual reproduce(Individual x, Individual y) {
		
		
		Map<String, Boolean> zGenome = new HashMap<String, Boolean>();
		return new GAIndividual(zGenome);
	}
}
