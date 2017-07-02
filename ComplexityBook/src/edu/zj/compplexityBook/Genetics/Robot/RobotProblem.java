package edu.zj.compplexityBook.Genetics.Robot;

import java.util.Random;

import edu.zj.compplexityBook.Genetics.ChromosomeFactory;
import edu.zj.compplexityBook.Genetics.GeneticAlgorithm;
import edu.zj.compplexityBook.Genetics.Problem;
import edu.zj.compplexityBook.utils.ArrayIndexMapping;

public class RobotProblem extends Problem {
	public static long RANDOM_SEED = 1000L;
	protected static final Random random = new Random(RANDOM_SEED);
	public static int[] DIMENSIONS = { 2, 3, 3, 3, 3, 3, 3, 3, 3 };
	public static ArrayIndexMapping AIM = new ArrayIndexMapping(DIMENSIONS);

	public static enum CellState {
		EMPTY, TRASH, WALL
	}

	public static enum RobotAction {
		CLEAN, MOVE_EAST, MOVE_EAST_SOUTH, MOVE_SOUTH, MOVE_WEST_SOUTH, MOVE_WEST, MOVE_WEST_NORTH, MOVE_NORTH, MOVE_EAST_NORTH, MOVE_RANDOM
	}

	public static enum RobotPos {
		CURRENT, EAST, EAST_SOUTH, SOUTH, WEST_SOUTH, WEST, WEST_NORTH, NORTH, EAST_NORTH
	}

	public class Robot {
		int iPos;

		public int getiPos() {
			return iPos;
		}

		public void setiPos(int iPos) {
			this.iPos = iPos;
		}

		public int getjPos() {
			return jPos;
		}

		public void setjPos(int jPos) {
			this.jPos = jPos;
		}

		int jPos;

		public int takeAction(RobotAction action) {
			if (action == null) return 0;
			switch (action) {
			case CLEAN:
				if (cells[iPos][jPos] == CellState.TRASH) {
					cells[iPos][jPos] = CellState.EMPTY;
					return 1;
				} else
					return 0;
			case MOVE_EAST:
				if (iPos > 0) {
					iPos--;
					return 0;
				} else {
					return -1;
				}
			case MOVE_EAST_SOUTH:
				if (iPos > 0 && jPos > 0) {
					iPos--;
					jPos--;
					return 0;
				} else
					return -1;
			case MOVE_SOUTH:
				if (jPos > 0) {
					jPos--;
					return 0;
				} else
					return -1;
			case MOVE_WEST_SOUTH:
				if (jPos > 0 && iPos < width - 1) {
					iPos++;
					jPos--;
					return 0;
				} else
					return -1;
			case MOVE_WEST:
				if (iPos < width - 1) {
					iPos++;
					return 0;
				} else
					return -1;
			case MOVE_WEST_NORTH:
				if (iPos < width - 1 && jPos < height - 1) {
					iPos++;
					jPos++;
					return 0;
				} else
					return -1;
			case MOVE_NORTH:
				if (jPos < height - 1) {
					jPos++;
					return 0;
				} else
					return -1;
			case MOVE_EAST_NORTH:
				if (iPos > 0 && jPos < height - 1) {
					jPos++;
					iPos--;
					return 0;
				} else
					return -1;
			case MOVE_RANDOM:
				return takeAction(simpleSolution.getRobotAction());
			default:
				return 0;
			}
		}

		Robot(int i, int j) {
			iPos = i;
			jPos = j;
		}

	}

	private final CellState[][] cells;
	private final int width, height, maxSteps;

	public int getMaxSteps() {
		return maxSteps;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Robot getRobot() {
		return robot;
	}

	public CellState[][] getCells() {
		return cells;
	}

	private final Robot robot;
	private final CellState[][] cells_save;
	private final Robot robot_save;
	private final RobotSimpleSolution simpleSolution;

	public RobotSimpleSolution getSimpleSolution() {
		return simpleSolution;
	}

	public RobotProblem(int width, int height, double initialTrashPercent, int maxSteps) {
		cells = new CellState[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (random.nextDouble() <= initialTrashPercent) {
					cells[i][j] = CellState.TRASH;

				} else {
					cells[i][j] = CellState.EMPTY;
				}
			}
		}
		this.width = width;
		this.height = height;
		this.maxSteps = maxSteps;
		robot = new Robot(0, 0);
		simpleSolution = resolve();
		robot_save = new Robot(0, 0);
		cells_save = new CellState[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cells_save[i][j] = cells[i][j];
			}
		}
		robot_save.iPos = robot.iPos;
		robot_save.jPos = robot.jPos;
	}

	public void restore() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				cells[i][j] = cells_save[i][j];
			}
		}
		robot.iPos = robot_save.iPos;
		robot.jPos = robot_save.jPos;

	}

	public CellState[] getRobotStates() {
		CellState[] states = new CellState[RobotPos.values().length];
		states[RobotPos.CURRENT.ordinal()] = cells[robot.getiPos()][robot.getjPos()];
		if (robot.getiPos() > 0)
			states[RobotPos.EAST.ordinal()] = cells[robot.getiPos() - 1][robot.getjPos()];
		else
			states[RobotPos.EAST.ordinal()] = CellState.WALL;

		if (robot.getiPos() > 0 && robot.getjPos() > 0)
			states[RobotPos.EAST_SOUTH.ordinal()] = cells[robot.getiPos() - 1][robot.getjPos() - 1];
		else
			states[RobotPos.EAST_SOUTH.ordinal()] = CellState.WALL;

		if (robot.getjPos() > 0)
			states[RobotPos.SOUTH.ordinal()] = cells[robot.getiPos()][robot.getjPos() - 1];
		else
			states[RobotPos.SOUTH.ordinal()] = CellState.WALL;

		if (robot.getiPos() < width - 1 && robot.getjPos() > 0)
			states[RobotPos.WEST_SOUTH.ordinal()] = cells[robot.getiPos() + 1][robot.getjPos() - 1];
		else
			states[RobotPos.WEST_SOUTH.ordinal()] = CellState.WALL;

		if (robot.getiPos() < width - 1)
			states[RobotPos.WEST.ordinal()] = cells[robot.getiPos() + 1][robot.getjPos()];
		else
			states[RobotPos.WEST.ordinal()] = CellState.WALL;

		if (robot.getiPos() < width - 1 && robot.getjPos() < height - 1)
			states[RobotPos.WEST_NORTH.ordinal()] = cells[robot.getiPos() + 1][robot.getjPos() + 1];
		else
			states[RobotPos.WEST_NORTH.ordinal()] = CellState.WALL;

		if (robot.getjPos() < height - 1)
			states[RobotPos.NORTH.ordinal()] = cells[robot.getiPos()][robot.getjPos() + 1];
		else
			states[RobotPos.NORTH.ordinal()] = CellState.WALL;

		if (robot.getiPos() > 0 && robot.getjPos() < height - 1)
			states[RobotPos.EAST_NORTH.ordinal()] = cells[robot.getiPos() - 1][robot.getjPos() + 1];
		else
			states[RobotPos.EAST_NORTH.ordinal()] = CellState.WALL;

		return states;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RobotSimpleSolution resolve() {
		// TODO Auto-generated method stub
		return new RobotSimpleSolution();
	}

	public RobotSolution resolve(int size, int elitismCount, double crossoverRate, double mutationRate) {
		restore();
		int length = 1;
		for (int i = 0; i < DIMENSIONS.length; i++) {
			length *= DIMENSIONS[i];
		}
		RobotChromosomeFactory factory = new RobotChromosomeFactory(RobotChromosome.class, length);
		GeneticAlgorithm<RobotChromosome, ChromosomeFactory> ga = new GeneticAlgorithm<>(factory, size, crossoverRate,
				mutationRate, elitismCount);
		restore();
		return ga.resolve().decode();
	}

}
