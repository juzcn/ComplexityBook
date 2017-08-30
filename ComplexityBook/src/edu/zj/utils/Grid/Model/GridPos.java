package edu.zj.utils.Grid.Model;

public class GridPos  {
	private final int row;
	private final int column;

	public GridPos(int row, int column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridPos other = (GridPos) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public GridPos getPos() {
		return new GridPos(row,column);
	}

	public String toString() {
		return "("+row + "," + column+")";
	}
	
}