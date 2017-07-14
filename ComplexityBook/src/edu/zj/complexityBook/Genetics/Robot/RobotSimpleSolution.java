package edu.zj.complexityBook.Genetics.Robot;

import java.util.ArrayList;
import java.util.List;

import edu.zj.complexityBook.Genetics.Chromosome;
import edu.zj.complexityBook.Genetics.Robot.RobotProblem.CellState;
import edu.zj.complexityBook.Genetics.Robot.RobotProblem.RobotAction;

public class RobotSimpleSolution extends RobotSolutionBase {
	@Override
	public RobotAction getRobotAction() {
		RobotProblem problem = RobotProblem.getProblem();
		return getRobotAction(problem.getRobotStates());
	}

	public RobotAction getRobotAction(CellState[] states) {
		if (states[0] == CellState.TRASH)
			return RobotAction.CLEAN;
		List<Integer> empties = new ArrayList<>();
		List<Integer> trashes = new ArrayList<>();
		for (int i = 1; i < 9; i++) {
			if (states[i] == CellState.EMPTY) {
				empties.add(i);
			} else if (states[i] == CellState.TRASH) {
				trashes.add(i);
			}
		}
		// System.out.println("Empties size "+empties.size());
		// System.out.println("Trashes size "+empties.size());
		if (trashes.size() > 0)
			return RobotAction.values()[trashes.get(RobotProblem.random.nextInt(trashes.size()))];
		if (empties.size() > 0)
			return RobotAction.values()[empties.get(RobotProblem.random.nextInt(empties.size()))];
		return null;
	}

	@Override
	public <C extends Chromosome> C encode() {
		// TODO Auto-generated method stub
		return null;
	}

}
