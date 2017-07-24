package edu.zj.utils.Grid.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SparseGrid<T, N extends Number> extends AbstractGrid<T, N> {
	private final Map<GridPos<N>, T> data;
	private final T asNull;

	public SparseGrid(T asNull) {
		this(null, null, false,asNull);
	};

	public SparseGrid(N rowSize, N columnSize, boolean wrapped, T asNull) {
		super(rowSize, columnSize, wrapped);
		this.data = new HashMap<>();
		this.asNull = asNull;
	}

	public SparseGrid(N rowSize, N columnSize, T asNull) {
		this(rowSize, columnSize, false, asNull);
	};

	@Override
	public T getData(N row, N column) {
		// TODO Auto-generated method stub
		return getData(new GridPos<N>(row, column));
	}

	@Override
	public void setData(N row, N column, T data) {
		setData(new GridPos<N>(row, column), data);
	}

	@Override
	public T getData(GridPos<N> pos) {
		T d = data.get(pos);
		return (d == null) ? asNull : d;
	}

	@Override
	public void setData(GridPos<N> pos, T data) {
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
	public Set<AbstractGrid<T, N>.Element> get(T value) {
		Set<Element> set = new HashSet<>();
		if (value.equals(asNull)) {
			// not implemented
		} else {
			Set<Entry<GridPos<N>, T>> entries = data.entrySet();
			T v;
			for (Entry<GridPos<N>, T> entry : entries) {
				v = entry.getValue();
				if (v.equals(value))
					set.add(new Element(entry.getKey().getRow(), entry.getKey().getColumn(), v));
			}
		}
		return set;
	}

}
