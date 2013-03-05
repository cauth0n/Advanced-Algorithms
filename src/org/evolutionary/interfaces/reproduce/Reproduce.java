package org.evolutionary.interfaces.reproduce;

import org.evolutionary.interfaces.individual.Individual;

/**
 * Interface to dictate reproduction techniques for all Evolutionary
 * algorithms
 * 
 * @author cauth0n
 */
public interface Reproduce {

	/***
	 * Returns a child individual of the same type from two parent
	 * individuals
	 * 
	 * @param x
	 *            one parent
	 * @param y
	 *            the second parent
	 * @return the child of the two parents.
	 */
	public Individual reproduce(Individual x, Individual y);
}
