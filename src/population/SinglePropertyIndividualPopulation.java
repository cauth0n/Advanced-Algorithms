package population;

import individual.SinglePropertyIndividuals;
import java.util.Collection;

public abstract class SinglePropertyIndividualPopulation implements Population {

	protected Collection<SinglePropertyIndividuals> population;

	public SinglePropertyIndividualPopulation() {

	}

	public SinglePropertyIndividualPopulation(SinglePropertyIndividualPopulation another) {
		this();
	}

}
