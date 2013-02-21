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
		if (x instanceof RockPaperScissorChoice && y instanceof RockPaperScissorChoice) {
			String xType = x.getProperty();
			String yType = y.getProperty();
			double rand = Math.random();
			if (rand < .5) {
				if (xType.equals("Paper")) {
					return new Paper();
				} else if (xType.equals("Rock")) {
					return new Rock();
				} else if (xType.equals("Scissor")) {
					return new Scissor();
				} else {
					System.out.println("Invalid xType in reproduce method.");
				}
			} else {
				if (yType.equals("Paper")) {
					return new Paper();
				} else if (yType.equals("Rock")) {
					return new Rock();
				} else if (yType.equals("Scissor")) {
					return new Scissor();
				} else {
					System.out.println("Invalid yType in reproduce method.");
				}
			}
		} else {
			System.out.println("Inproper types entering reproduce method in RockPaperScissorSimulator");
		}
		return null;
	}

	@Override
	public RockPaperScissorPopulation buildEmptyPopulation() {
		// TODO Auto-generated method stub
		return null;
	}

}
