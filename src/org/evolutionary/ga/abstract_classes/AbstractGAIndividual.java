package org.evolutionary.ga.abstract_classes;

import java.util.Map;

import org.evolutionary.interfaces.individual.Individual;

public class AbstractGAIndividual implements Individual {
	protected Map<String, Boolean> genome;

	public AbstractGAIndividual(Map<String, Boolean> genome) {
		this.genome = genome;
	}

	@Override
	public Map<String, Boolean> getGenome() {
		return genome;
	}

}
