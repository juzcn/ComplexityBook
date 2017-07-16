package edu.zj.utils.Grid;

import java.util.HashSet;
import java.util.Set;

public class Grid<T> extends AbstractGrid<T, Integer> {
	public Grid(Integer rowSize, Integer columnSize, boolean wrapped) {
		super(rowSize, columnSize, wrapped);
		data = new Object[rowSize][columnSize];
		System.out.println("Wrapped "+wrapped);
		clear();
	}

	private final Object data[][];

	public Grid(Integer rowSize, Integer columnSize) {
		this(rowSize, columnSize,false);
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
	public T getData(GridPos<Integer> pos) {
		return getData(pos.getRow(), pos.getColumn());
	}

	@Override
	public void setData(GridPos<Integer> pos, T data) {
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
