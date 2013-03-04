package org.evolutionary.ga.abstract_classes;

import org.evolutionary.interfaces.generate.Generate;
import org.evolutionary.interfaces.simulator.Simulator;

public abstract class AbstractGASimulator implements Simulator {
	protected Generate generateProcedure;
}
