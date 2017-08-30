package edu.zj.utils.Grid.Model;

public class GridCell<V> extends GridPos {
	private final V value;

	public GridCell(int row, int column, V value) {
		super(row, column);
		this.value = value;
	}

	public GridCell(GridPos pos, V value) {
		this(pos.getRow(),pos.getColumn(),value);
	}

	public V getValue() {
		return value;
	}
}
