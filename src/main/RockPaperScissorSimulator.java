package main;

import fitness.Fitness;
import individual.Individual;
import individual.Paper;
import individual.Rock;
import individual.RockPaperScissorChoice;
import individual.Scissor;
import population.Population;
import population.RockPaperScissorPopulation;

public class RockPaperScissorSimulator extends Simulator {

	public RockPaperScissorSimulator() {

	}

	@Override
	public Individual ga(Population initialPopulation, Fitness fitness) {

		int repeatCounter = 0; 
		do {
			Population newPopulation = buildEmptyPopulation();
			
			
			repeatCounter++;
		} while (repeatCounter < 10000);
		return null;
	}

	@Override
	public RockPaperScissorChoice reproduce(Individual x, Individual y) {

		return null;
	}

	@Override
	public RockPaperScissorPopulation buildEmptyPopulation() {
		// TODO Auto-generated method stub
		return null;
	}

}
