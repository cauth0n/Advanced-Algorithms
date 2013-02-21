package population;

import java.util.ArrayList;
import java.util.HashSet;

import individual.Individual;
import individual.RockPaperScissorChoice;
import individual.SinglePropertyIndividuals;

public class RockPaperScissorPopulation extends SinglePropertyIndividualPopulation {

	public RockPaperScissorPopulation() {
		super();
	}

	@Override
	public void instantiateEmptyArrayList(Individual individualType) {
		if (individualType instanceof RockPaperScissorChoice) {
			population = new ArrayList<RockPaperScissorChoice>();
		}else{
			System.out.println("Type error in instantiateEmptyArrayList of RockPaperScissorPopulation");
		}

	}

	@Override
	public void instantiateEmptySet(Individual individualType) {
		if (individualType instanceof RockPaperScissorChoice) {
			population = new HashSet<RockPaperScissorChoice>();
		}else{
			System.out.println("Type error in instantiateEmptySet of RockPaperScissorPopulation");
		}

	}

}
