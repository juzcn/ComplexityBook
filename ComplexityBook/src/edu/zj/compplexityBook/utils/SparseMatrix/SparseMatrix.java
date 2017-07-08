package edu.zj.compplexityBook.utils.SparseMatrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

public class SparseMatrix<T, N extends Number> extends AbstractMatrix<T, N> {
	private final Map<Position<N>, T> data;

	public SparseMatrix() {
		super();
		data = new HashMap<>();
	};

	public SparseMatrix(N rowSize, N columnSize) {
		super(rowSize, columnSize);
		data = new HashMap<>();
	};

	public Set<Entry<Position<N>, T>> getAll() {
		return data.entrySet();
	}

	@Override
	public T getData(N row, N column) {
		// TODO Auto-generated method stub
		return getData(new Position<N>(row, column));
	}

	@Override
	public void setData(N row, N column, T data) {
		setData(new Position<N>(row, column), data);
	}

	@Override
	public T getData(Position<N> pos) {
		return data.get(pos);
	}

	@Override
	public void setData(Position<N> pos, T data) {
		if (data == null)
			this.data.remove(pos);
		else
			this.data.put(pos, data);
	}

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	public Set<AbstractMatrix<T, N>.Element> getNotNulls() {
		Set<Element> set = new HashSet<>();
		Set<Entry<Position<N>, T>> entries = data.entrySet();
		for (Entry<Position<N>, T> entry:entries) {
			set.add(new Element(entry.getKey().getRow(),entry.getKey().getColumn(),entry.getValue()));
		}
		return set;
	}

}
