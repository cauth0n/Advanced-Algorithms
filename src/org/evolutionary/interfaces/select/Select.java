package org.evolutionary.interfaces.select;

import org.evolutionary.interfaces.individual.Individual;

/**
 * Interface to dictate Selection techniques for all Evolutionary
 * algorithms. The selection process typically involves choosing two
 * very fit individuals from a population.
 * 
 * @author cauth0n
 */
public interface Select {

	public Individual select();

}
