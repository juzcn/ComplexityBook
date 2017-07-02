package edu.zj.compplexityBook.Genetics.Robot;

import java.util.ArrayList;
import java.util.List;

import edu.zj.compplexityBook.Genetics.ChromosomeFactory;

public class RobotChromosomeFactory extends ChromosomeFactory {

	public RobotChromosomeFactory(Class<RobotChromosome> chromosomeClass, int chromosomeLength) {
		super(chromosomeClass, chromosomeLength);
	}

	public RobotChromosome getValidRobotChromosome() {
		RobotProblem problem = RobotProblem.getProblem();
		problem.restore();
		RobotProblem.RobotAction action;
		RobotChromosome c = null;
		byte[] genes = new byte[chromosomeLength];
		for (int j = 0; j < chromosomeLength; j++) {
			int[] indexes = RobotProblem.AIM.indexMapping(j);
			RobotProblem.CellState[] states = new RobotProblem.CellState[indexes.length];
			for (int k = 0; k < states.length; k++) {
				states[k] = RobotProblem.CellState.values()[indexes[k]];
			}
			action = problem.getSimpleSolution().getRobotAction(states);
			problem.getRobot().takeAction(action);
			if (action == null)
				genes[j] = 0;
			else
				genes[j] = (byte) action.ordinal();
		}
		c = new RobotChromosome(genes);
		problem.restore();
		return c;
	}

	@SuppressWarnings("unchecked")
	public List<RobotChromosome> getRandomPopulation(int size) {

		List<RobotChromosome> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(getValidRobotChromosome());
		}
		return list;
	}

}
