package edu.zj.utils.SparseMatrix;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

public class SparseMatrix<T, N extends Number> extends AbstractMatrix<T, N> {
	private final Map<Position<N>, T> data;
	private final T asNull;

	public SparseMatrix(T asNull) {
		this(null, null, asNull);
	};

	public SparseMatrix(N rowSize, N columnSize, T asNull) {
		super(rowSize, columnSize);
		this.data = new HashMap<>();
		this.asNull = asNull;
	};

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
		T d = data.get(pos);
		return (d == null) ? asNull : d;
	}

	@Override
	public void setData(Position<N> pos, T data) {
		if (data.equals(asNull))
			this.data.remove(pos);
		else
			this.data.put(pos, data);
	}

	@Override
	public void clear() {
		data.clear();
	}

	@Override
	public Set<AbstractMatrix<T, N>.Element> get(T value) {
		Set<Element> set = new HashSet<>();
		if (value.equals(asNull)) {
			// not implemented
		} else {
			Set<Entry<Position<N>, T>> entries = data.entrySet();
			T v;
			for (Entry<Position<N>, T> entry : entries) {
				v = entry.getValue();
				if (v.equals(value))
					set.add(new Element(entry.getKey().getRow(), entry.getKey().getColumn(), v));
			}
		}
		return set;
	}

}
