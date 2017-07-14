package edu.zj.complexityBook.CellularAutomata;

public abstract class CACell<S extends Enum<S>, N extends Number> {
	private CAGrid<CACell<S, N>, N> caGrid;
	private final N row;
	private final N column;
	private S state;
	private S newState;

	public CACell(CAGrid<CACell<S, N>, N> caGrid, N row, N column, S state) {
		this.caGrid = caGrid;
		this.row = row;
		this.column = column;
		this.state = state;
	}

	public abstract void evaluate();

	public void update() {
		if (newState != null) {
			state = newState;
			caGrid.setData(row, column, this);
		}
	}
}
