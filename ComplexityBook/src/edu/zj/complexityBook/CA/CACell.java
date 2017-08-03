package edu.zj.complexityBook.CA;

import edu.zj.utils.Grid.Grid;

public abstract class CACell<S, G extends Grid<S>> {
	protected G caGrid;
	protected int row;
	protected int column;
	protected S state;
	protected S nextState;

	public CACell(G caGrid, int row, int column, S state) {
		super();
		this.caGrid = caGrid;
		this.row = row;
		this.column = column;
		this.state = state;
	}

	public S getState() {
		return state;
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
			caGrid.set(row, column, nextState);
			state = nextState;
		}
	}

}
