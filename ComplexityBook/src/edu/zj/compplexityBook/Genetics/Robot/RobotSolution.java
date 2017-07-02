package edu.zj.compplexityBook.Genetics.Robot;

import edu.zj.compplexityBook.Genetics.Chromosome;
import edu.zj.compplexityBook.Genetics.Robot.RobotProblem.CellState;
import edu.zj.compplexityBook.Genetics.Robot.RobotProblem.RobotAction;

public class RobotSolution extends RobotSimpleSolution {
	private final byte[] genes;

	public RobotSolution(byte[] genes) {
		this.genes = genes;
	}

	@Override
	public RobotAction getRobotAction() {
		RobotProblem problem = RobotProblem.getProblem();
		int indexes[] = new int[RobotProblem.RobotPos.values().length];
			CellState[] states = problem.getRobotStates();
			for (int i = 0; i < states.length; i++) {
				indexes[i] = states[i].ordinal();
			}
			return RobotProblem.RobotAction.values()[genes[RobotProblem.AIM.indexMapping(indexes)]];
	}

	@Override
	public <C extends Chromosome> C encode() {
		// TODO Auto-generated method stub
		return null;
	}

}
