package org.evolutionary_test.ga;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.evolutionary.ga.concrete_classes.GAIndividual;
import org.evolutionary.ga.concrete_classes.GAReproduce;
import org.evolutionary.interfaces.individual.Individual;
import org.junit.Test;

public class GAReproduceTest {

	@Test
	public void test() {

		// need to figure out how to test hash map equality..
		// Also, there is a random number generation in one method..
		// need to figure out how to duplicate it.

		// fail("Not yet implemented");

		Individual x = buildIndividual(true);
		Individual y = buildIndividual(false);
		GAReproduce gaObject = new GAReproduce();
		Individual z = gaObject.reproduce(x, y);
		// assertEquals()
	}

	public Individual buildIndividual(boolean key) {
		return new GAIndividual(buildMap(key));
	}

	public Map<String, Boolean> buildMap(boolean key) {
		Map<String, Boolean> map = new HashMap<String, Boolean>(10);
		instantiateMap(map, key);
		return map;
	}

	public void instantiateMap(Map<String, Boolean> map, boolean key) {
		for (int i = 0; i < map.size(); i++) {
			if (i % 2 == 0) {
				map.put(Integer.toString(i), key);
			} else {
				map.put(Integer.toString(i), !key);
			}
		}
	}
}
