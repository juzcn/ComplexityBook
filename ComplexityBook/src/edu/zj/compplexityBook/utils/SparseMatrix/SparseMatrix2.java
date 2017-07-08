package edu.zj.compplexityBook.utils.SparseMatrix;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SparseMatrix<T> {
	public static class Position {
		private final BigInteger row;
		private final BigInteger column;

		public BigInteger getRow() {
			return row;
		}

		public BigInteger getColumn() {
			return column;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((column == null) ? 0 : column.hashCode());
			result = prime * result + ((row == null) ? 0 : row.hashCode());
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
			Position other = (Position) obj;
			if (column == null) {
				if (other.column != null)
					return false;
			} else if (!column.equals(other.column))
				return false;
			if (row == null) {
				if (other.row != null)
					return false;
			} else if (!row.equals(other.row))
				return false;
			return true;
		}

		public Position(BigInteger row, BigInteger column) {
			this.row = row;
			this.column = column;
		}

	}

	private final Map<Position, T> matrixData;

	public SparseMatrix() {
		matrixData = new HashMap<>();
	};

	public Set<Entry<Position, T>> getAll() {
		return matrixData.entrySet();
	}

	public void setData(T data, BigInteger row, BigInteger column) {
		setData(data, new Position(row, column));
	}

	public T getData(Position pos) {
		return matrixData.get(pos);
	}

	public void setData(T data, Position pos) {
		matrixData.put(pos, data);
	}

	public T getData(BigInteger row, BigInteger column) {
		return getData(new Position(row, column));
	}

	public final void clear() {
		matrixData.clear();
	}

	public final void clear(Position pos) {
		matrixData.remove(pos);
	}

	public final void clear(BigInteger row, BigInteger column) {
		clear(new Position(row, column));
	}

}
