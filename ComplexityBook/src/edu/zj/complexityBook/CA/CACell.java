package edu.zj.complexityBook.CA;

import java.util.ArrayList;
import java.util.List;

import edu.zj.utils.Grid.Model.Grid;
import edu.zj.utils.Grid.Model.GridPos;
import edu.zj.utils.Grid.Model.IGridPos;

public abstract class CACell<S, M extends CAMain<S, ? extends Grid<S>, ? extends CACell<S, M>>> implements IGridPos {
	protected M caMain;
	protected int row;
	protected int column;
	protected S state;
	protected S nextState;

	public S getNextState() {
		return nextState;
	}

	public void setNextState(S nextState) {
		this.nextState = nextState;
	}

	public CACell(M caMain, int row, int column, S state) {
		this.caMain = caMain;
		this.row = row;
		this.column = column;
		this.state = state;
	}

	public CACell(M caMain, int row, int column) {
		this.caMain = caMain;
		this.row = row;
		this.column = column;
		this.state = caMain.getCaGrid().get(row, column);
	}

	public CACell(M caMain, IGridPos pos) {
		this.caMain = caMain;
		this.row = pos.getRow();
		this.column = pos.getColumn();
		this.state = caMain.getCaGrid().get(pos);
	}

	public S getState() {
		return state;
	}

	public List<CACell<S, M>> neighbs4() {
		List<GridPos> pos = caMain.getCaGrid().neighbs4(row, column);
		List<CACell<S, M>> list = new ArrayList<>();
		CACell<S, M> cell;
		for (IGridPos p : pos) {
			cell = caMain.getCellMap().get(p);
			if (cell != null)
				list.add(cell);
		}
		return list;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setState(S state) {
		this.state = state;
	}

	public abstract void evaluate();

	public void update() {
		if (nextState != null) {
			caMain.getCaGrid().set(row, column, nextState);
			state = nextState;
			nextState = null;
		}
	}

}
