package reproduce;

import individual.Individual;

import java.util.Map;

public class RockPaperScissorsReproduce implements Reproduce {

	public RockPaperScissorsReproduce() {

	}

	@Override
	public Individual reproduce(Individual x, Individual y) {
		Map<?, ?> xType = x.getProperties();
		Map<?, ?> yType = y.getProperties();

		int xSize = xType.size();
		if (yType.size() != xSize) {
			System.out.println("Inproper sizes, from Reproduce");
		}

		for (int i = 0; i < xSize; i++) {

		}

		return null;
	}

}
