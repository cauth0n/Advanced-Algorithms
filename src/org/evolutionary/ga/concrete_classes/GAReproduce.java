package org.evolutionary.ga.concrete_classes;

import java.util.HashMap;
import java.util.Map;

import org.evolutionary.ga.abstract_classes.AbstractGAReproduce;
import org.evolutionary.interfaces.individual.Individual;

public class GAReproduce extends AbstractGAReproduce {
	
	
	public Individual reproduce(Individual x, Individual y) {

		int genomeSize = ((Map<String, Boolean>) x.getGenome()).size();
		int cutOff = getRandomInt(genomeSize);

		Map<String, Boolean> zGenome = new HashMap<String, Boolean>(genomeSize);
		assignX((Map<String, Boolean>) x.getGenome(), zGenome, cutOff);
		assignY((Map<String, Boolean>) y.getGenome(), zGenome, cutOff);
		return new GAIndividual(zGenome);
	}

	public void assignX(Map<String, Boolean> xGenome, Map<String, Boolean> zGenome, int cutOff) {
		String[] keysOfxGenome = (String[]) xGenome.keySet().toArray();
		for (int i = 0; i < cutOff; i++) {
			zGenome.put(keysOfxGenome[i], xGenome.get(i));
		}
	}

	public void assignY(Map<String, Boolean> yGenome, Map<String, Boolean> zGenome, int cutOff) {
		String[] keysOfyGenome = (String[]) yGenome.keySet().toArray();
		for (int i = cutOff; i < yGenome.size(); i++) {
			zGenome.put(keysOfyGenome[i], yGenome.get(i));
		}
	}
}
