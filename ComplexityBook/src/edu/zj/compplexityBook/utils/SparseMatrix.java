package edu.zj.compplexityBook.utils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SparseMatrix<T> {

	public class Element {
		private final BigInteger row;
		private final BigInteger column;
		private final T data;

		public Element(BigInteger row, BigInteger column, T data) {
			this.row = row;
			this.column = column;
			this.data = data;
		}

		public BigInteger getRow() {
			return row;
		}

		public BigInteger getColumn() {
			return column;
		}

		public T getData() {
			return data;
		}
	}

	public static class Pos {
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
			Pos other = (Pos) obj;
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

		public Pos(BigInteger row, BigInteger column) {
			this.row = row;
			this.column = column;
		}

	}

	private final Map<Pos, T> matrixData;

	public SparseMatrix() {
		matrixData = new HashMap<>();

	};

	public SparseMatrix(SparseMatrix<T> sm) {
		this();
		Set<Entry<Pos, T>> set = sm.matrixData.entrySet();
		for (Entry<Pos, T> entry : set) {
			this.setData(entry.getValue(), entry.getKey().getRow(), entry.getKey().getColumn());
		}
	}

	public final void setData(T data, BigInteger row, BigInteger column) {
		matrixData.put(new Pos(row, column), data);
	}

	public void setData(T data, String row, String column) {
		setData(data, new BigInteger(row), new BigInteger(column));
	}

	public final void setData(T data, int row, int column) {
		setData(data, Integer.toString(row), Integer.toString(column));
	}

	public final T getData(BigInteger row, BigInteger column) {
		return matrixData.get(new Pos(row, column));
	}

	public T getData(String row, String column) {
		return getData(new BigInteger(row), new BigInteger(column));
	}

	public final T getData(int row, int column) {
		return getData(Integer.toString(row), Integer.toString(column));
	}

	public final void clear() {
		matrixData.clear();
	}

	public final void clear(Pos pos) {
		matrixData.remove(pos);
	}

	public final void clear(String row, String column) {
		clear(new BigInteger(row), new BigInteger(column));
	}

	public final void clear(BigInteger row, BigInteger column) {
		clear(new Pos(row, column));
	}

	public final void clear(int row, int column) {
		clear(Integer.toString(row), Integer.toString(column));
	}

}
