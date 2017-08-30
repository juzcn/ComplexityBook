package edu.zj.complexityBook.CA.Sandpile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import edu.zj.complexityBook.CA.CAGrid;
import edu.zj.complexityBook.CA.CAModel;
import edu.zj.utils.Grid.Model.GridNeighb;

public class SandpileModel extends CAModel<SandpileModel> {

	public class Avalanche {
		int duration = 0;
		int criticalAgentCount = 0;

		void incrementDuration() {
			duration++;
		}

		void incrementAgents(int size) {
			criticalAgentCount += size;
		}

		public int getDuration() {
			return duration;
		}

		public int getCriticalAgentCount() {
			return criticalAgentCount;
		}

		public String toString() {
			return duration + "-" + criticalAgentCount;
		}
	}

	List<Avalanche> avalanches = new ArrayList<>();

	public SandpileModel(SandpileConfig.ModelParams params) {
		super(params.getRowCount(), params.getColumnCount());
		this.setSynchro(params.isSynchro());
		setTitle("SandpileÄ£ÐÍ");
		setRandomSeed(params.getRandomSeed());
		CAGrid grid = new CAGrid(params.getRowCount(), params.getColumnCount(),5);
		// caGrid = new MapGrid<>(0);
		for (int i = 0; i < params.getRowCount(); i++) {
			for (int j = 0; j < params.getColumnCount(); j++) {
				grid.set(i, j, getRandom().nextInt(4));
			}

		}
		grid.setBoundCondition(0);
		this.setGrid(grid);
		this.setFilter((s) -> s >= 4);
	}

	protected Avalanche current = null;

	@Override
	protected void evaluate(Predicate<Integer> filter) {
		super.evaluate(filter);
		int row, column;
		if (getSelected() == 0) {
			if (current != null) {
				System.out.println("Step " + getStep() + " Avalanche terminated " + current);
				avalanches.add(current);
				current = null;
			}
			row = getRandom().nextInt(getGrid().getRowSize());
			column = getRandom().nextInt(getGrid().getColumnSize());
			this.setNextState(row, column, getGrid().get(row, column) + 1);
		} else {
			if (current == null) {
				current = new Avalanche();
			}
			current.incrementDuration();
			current.incrementAgents(getSelected());
		}
	}

	public List<Avalanche> getAvalanches() {
		return avalanches;
	}

	@Override
	protected Integer evaluate(int row, int column) {
		// only filter by condition
		List<GridNeighb<Integer>> neighbs = getGrid().neighbs(row, column);
		for (GridNeighb<Integer> p : neighbs) {
			setNextState(p.getPos(),p.getValue()  + 1);
		}
		return getGrid().get(row, column) - neighbs.size();
	}
}
