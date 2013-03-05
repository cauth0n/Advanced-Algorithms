package org.evolutionary.ga.abstract_classes;

import java.util.Map;
import java.util.Random;

import org.evolutionary.interfaces.reproduce.Reproduce;

public abstract class AbstractGAReproduce implements Reproduce {

	public abstract void assignX(Map<String, Boolean> xGenome, Map<String, Boolean> zGenome, int cutOff);

	public abstract void assignY(Map<String, Boolean> yGenome, Map<String, Boolean> zGenome, int cutOff);

	public int getRandomInt(int numbersToPullFrom) {
		Random rand = new Random();
		return rand.nextInt(numbersToPullFrom);
	}
}
