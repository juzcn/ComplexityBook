package edu.zj.complexityBook.CA;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import edu.zj.complexityBook.UI.Model;
import edu.zj.utils.Grid.Model.GridCell;
import edu.zj.utils.Grid.Model.GridPos;

public abstract class CAModel<M extends CAModel<M>> extends Model {
	private CAGrid grid;
	private List<GridCell<Integer>> nextStateList;
	private Predicate<Integer> filter;
	private boolean synchro = true;

	public CAModel(int rowCount, int columnCount) {
		nextStateList = new ArrayList<>();
	}

	public CAGrid getGrid() {
		return grid;
	}

	public int getRowCount() {
		return grid.getRowSize();
	}

	public int getColumnCount() {
		return grid.getColumnSize();
	}

	public void setNextState(GridPos pos, Integer value) {
		if (synchro)
			nextStateList.add(new GridCell<Integer>(pos, value));
		else {
			this.getGrid().set(pos, value);
		}
	}

	public void setNextState(int row, int column, Integer value) {
		setNextState(new GridPos(row, column), value);
	}

	protected abstract Integer evaluate(int row, int column);

	protected void evaluate(Predicate<Integer> filter) {
		Integer nextState;
		selected = 0;

		for (int i = 0; i < grid.getRowSize(); i++) {
			for (int j = 0; j < grid.getColumnSize(); j++) {
				if (filter.test(grid.get(i, j))) {
					selected++;
					nextState = evaluate(i, j);
					if (nextState != null) {
						setNextState(i, j, nextState);
					}
				}
			}

		}
	}

	protected void evaluate() {
		Integer nextState;
		for (int i = 0; i < grid.getRowSize(); i++) {
			for (int j = 0; j < grid.getColumnSize(); j++) {
				nextState = evaluate(i, j);
				if (nextState != null) {
					setNextState(i, j, nextState);
				}
			}

		}
	}

	private int selected;
	private int stateChanged;

	protected final void update() {
		stateChanged = 0;
		for (GridCell<Integer> cell : nextStateList) {
			stateChanged++;
			grid.set(cell.getPos(), cell.getValue());
		}
		nextStateList.clear();

	}

	public void stepRun() {
		step++;
		if (filter == null)
			evaluate();
		else
			evaluate(filter);
		update();
	}

	public void setFilter(Predicate<Integer> filter) {
		this.filter = filter;
	}

	public void setFilter(Integer value) {
		this.filter = Predicate.isEqual(value);
	}

	public void setGrid(CAGrid grid) {
		this.grid = grid;
	}

	public int getSelected() {
		return selected;
	}

	public int getStateChanged() {
		return stateChanged;
	}

	public void setSynchro(boolean synchro) {
		this.synchro = synchro;
	}

}
