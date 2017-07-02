package edu.zj.compplexityBook.Genetics.Robot;

import edu.zj.compplexityBook.Genetics.Chromosome;
import edu.zj.compplexityBook.Genetics.Robot.RobotProblem.CellState;

public class RobotChromosome extends Chromosome {
	public RobotChromosome(byte[] genes) {
		super(genes);
	}

	@Override
	public double calcFitness() {
		RobotProblem problem = RobotProblem.getProblem();
		problem.restore();
		int indexes[] = new int[RobotProblem.RobotPos.values().length];
		fitness = 0;
		int score;
		int position;
		for (int step = 1; step <= problem.getMaxSteps(); step++) {
			CellState[] states = problem.getRobotStates();
			for (int i = 0; i < states.length; i++) {
				indexes[i] = states[i].ordinal();
			}
			position=RobotProblem.AIM.indexMapping(indexes);
			RobotProblem.RobotAction action = RobotProblem.RobotAction
					.values()[getGene(position)];
			score=problem.getRobot().takeAction(action);
//			if (score==-1) {
//				action = problem.getSimpleSolution().getRobotAction(states);
//				score=problem.getRobot().takeAction(action);
//				System.out.println("New score "+score);
//				setGene(position,action.ordinal());
//			}
			fitness += score;
		}
		problem.restore();
		return fitness;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RobotSolution decode() {
		return new RobotSolution(getGenes());
	}

}
