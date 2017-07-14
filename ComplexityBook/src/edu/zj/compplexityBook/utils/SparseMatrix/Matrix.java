package edu.zj.compplexityBook.utils.SparseMatrix;

import java.util.HashSet;
import java.util.Set;

public class Matrix<T> extends AbstractMatrix<T, Integer> {
	private final Object data[][];

	public Matrix(Integer rowSize, Integer columnSize) {
		super(rowSize, columnSize);
		data = new Object[rowSize][columnSize];
		clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getData(Integer row, Integer column) {
		return (T) data[row][column];
	}

	@Override
	public void setData(Integer row, Integer column, T data) {
		this.data[row][column] = data;
	}

	@Override
	public T getData(Position<Integer> pos) {
		return getData(pos.getRow(), pos.getColumn());
	}

	@Override
	public void setData(Position<Integer> pos, T data) {
		setData(pos.getRow(), pos.getColumn(), data);
	}

	@Override
	public void clear() {
		for (int i = 0; i < getRowSize(); i++) {
			for (int j = 0; j < getColumnSize(); j++) {
				data[i][j] = null;
			}

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Element> get(T value) {
		// TODO Auto-generated method stub
		Set<Element> set = new HashSet<>();
		for (int i = 0; i < getRowSize(); i++) {
			for (int j = 0; j < getColumnSize(); j++) {
				if (data[i][j].equals(value))
					set.add(new Element(i, j, (T) data[i][j]));
			}

		}
		return set;
	}

}
