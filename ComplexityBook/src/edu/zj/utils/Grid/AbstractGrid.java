package edu.zj.utils.Grid;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Set;

public abstract class AbstractGrid<T, N extends Number> {
	protected final N rowSize;
	protected final N columnSize;
	protected final boolean wrapped;
	protected final Class sizeClass;

	public class Element extends GridPos<N> {
		private final T data;

		public Element(N row, N column, T data) {
			super(row, column);
			this.data = data;
		}

		public T getData() {
			return data;
		}

	}

	public AbstractGrid(N rowSize, N columnSize, boolean wrapped) {
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.sizeClass = this.getSizeClass();
		this.wrapped = wrapped;
	}

	public AbstractGrid(N rowSize, N columnSize) {
		this(rowSize, columnSize, false);
	}

	public AbstractGrid() {
		this(null, null, false);
	}

	public N getRowSize() {
		return rowSize;
	}

	public N getColumnSize() {
		return columnSize;
	}

	public abstract void clear();

	public abstract T getData(N row, N column);

	public abstract void setData(N row, N column, T data);

	public abstract T getData(GridPos<N> pos);

	public abstract void setData(GridPos<N> pos, T data);

	public abstract Set<Element> get(T value);

	public Element getElement(N row, N column) {
		return new Element(row, column, getData(row, column));
	}

	@SuppressWarnings("unchecked")
	public final N add(N op1, N op2) {
		if (op1 == null)
			return op2;
		if (op2 == null)
			return op1;
		if (op1 instanceof Integer) {
			return (N) new Integer(((Integer) op1) + ((Integer) op2));
		}
		if (op1 instanceof BigInteger) {
			return (N) ((BigInteger) op1).add((BigInteger) op2);
		}
		throw new UnsupportedOperationException("add operation not yet implemented.");
	}

	@SuppressWarnings("unchecked")
	public final N add(N op1, int op2) {

		if (op1 == null) {
			if (sizeClass == BigInteger.class)
				return (N) new BigInteger(Integer.toString(op2));
			else
				return (N) new Integer(op2);
		}
		if (op1 instanceof Integer)
			return (N) new Integer(((Integer) op1) + op2);
		if (op1 instanceof BigInteger)
			return (N) ((BigInteger) op1).add(new BigInteger(Integer.toString(op2)));
		throw new UnsupportedOperationException("add operation not yet implemented.");
	}

	@SuppressWarnings("unchecked")
	public final N substract(N op1, int op2) {
		if (op1 == null) {
			if (sizeClass == BigInteger.class)
				return (N) new BigInteger(Integer.toString(-op2));
			else
				return (N) new Integer(-op2);
		}
		if (op1 instanceof Integer)
			return (N) new Integer(((Integer) op1) - op2);
		if (op1 instanceof BigInteger)
			return (N) ((BigInteger) op1).subtract(new BigInteger(Integer.toString(op2)));
		throw new UnsupportedOperationException("add operation not yet implemented.");
	}

	private Class getSizeClass() {
		Type type;
		Type[] types;
		Class c = getClass();
		while (c != AbstractGrid.class) {
			type = c.getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				c = (Class) ((ParameterizedType) type).getRawType();
				types = ((ParameterizedType) type).getActualTypeArguments();
				for (int i = 0; i < types.length; i++) {
					if (types[i] == BigInteger.class) {
						return BigInteger.class;
					}
				}
			} else
				c = (Class) type;
		}
		return Integer.class;

	}

	private final Integer incrementRow(Integer row) {
		row++;
		if (row.equals((Integer) getRowSize())) {
			if (wrapped)
				return 0;
			return null;
		}
		return row;
	}

	private final BigInteger incrementRow(BigInteger row) {
		row = row.add(BigInteger.ONE);
		if (row.equals((BigInteger) getRowSize())) {
			if (wrapped)
				return BigInteger.ZERO;
			return null;
		}
		return row;
	}

	private final Integer decrementRow(Integer row) {
		row--;
		if (row == -1) {
			if (wrapped)
				return (Integer) getRowSize() - 1;
			return null;
		}
		return row;
	}

	public final static BigInteger NEGATIVE_ONE = new BigInteger("-1");

	private final BigInteger decrementRow(BigInteger row) {
		row = row.subtract(BigInteger.ONE);
		if (row.equals(NEGATIVE_ONE)) {
			if (wrapped)
				return ((BigInteger) getRowSize()).subtract(BigInteger.ONE);
			return null;
		}
		return row;
	}

	private final Integer incrementColumn(Integer column) {
		column++;
		if (column.equals((Integer) getColumnSize())) {
//			System.out.println("Wrapped " + wrapped + "column  column size " + column + " " + getColumnSize());
			if (wrapped)
				return 0;
			return null;
		}
		return column;
	}

	private final BigInteger incrementColumn(BigInteger column) {
		column = column.add(BigInteger.ONE);
		if (column.equals((BigInteger) getColumnSize())) {
			if (wrapped)
				return BigInteger.ZERO;
			return null;
		}
		return column;
	}

	private final Integer decrementColumn(Integer column) {
		column--;
		if (column == -1) {
			if (wrapped)
				return (Integer) getColumnSize() - 1;
			return null;
		}
		return column;
	}

	private final BigInteger decrementColumn(BigInteger column) {
		column = column.subtract(BigInteger.ONE);
		if (column.equals(NEGATIVE_ONE)) {
			if (wrapped)
				return ((BigInteger) getColumnSize()).subtract(BigInteger.ONE);
			return null;
		}
		return column;
	}

	@SuppressWarnings("unchecked")
	public GridPos<N> top(N row, N column) {
		if (sizeClass == BigInteger.class) {
			return new GridPos<N>((N) decrementRow((BigInteger) row), column);
		} else {
			return new GridPos<N>((N) decrementRow((Integer) row), column);
		}
	}

	@SuppressWarnings("unchecked")
	public GridPos<N> down(N row, N column) {
		if (sizeClass == BigInteger.class) {
			return new GridPos<N>((N) incrementRow((BigInteger) row), column);
		} else {
			return new GridPos<N>((N) incrementRow((Integer) row), column);
		}
	}

	@SuppressWarnings("unchecked")
	public GridPos<N> left(N row, N column) {
		if (sizeClass == BigInteger.class) {
			return new GridPos<N>(row, (N) decrementColumn((BigInteger) column));
		} else {
			return new GridPos<N>(row, (N) decrementColumn((Integer) column));
		}
	}

	@SuppressWarnings("unchecked")
	public GridPos<N> topleft(N row, N column) {
		if (sizeClass == BigInteger.class) {
			return new GridPos<N>((N) decrementRow((BigInteger) row), (N) decrementColumn((BigInteger) column));
		} else {
			return new GridPos<N>((N) decrementRow((Integer) row), (N) decrementColumn((Integer) column));
		}
	}

	@SuppressWarnings("unchecked")
	public GridPos<N> downleft(N row, N column) {
		if (sizeClass == BigInteger.class) {
			return new GridPos<N>((N) incrementRow((BigInteger) row), (N) decrementColumn((BigInteger) column));
		} else {
			return new GridPos<N>((N) incrementRow((Integer) row), (N) decrementColumn((Integer) column));
		}
	}

	@SuppressWarnings("unchecked")
	public GridPos<N> topright(N row, N column) {
		if (sizeClass == BigInteger.class) {
			return new GridPos<N>((N) decrementRow((BigInteger) row), (N) incrementColumn((BigInteger) column));
		} else {
			return new GridPos<N>((N) decrementRow((Integer) row), (N) incrementColumn((Integer) column));
		}
	}

	@SuppressWarnings("unchecked")
	public GridPos<N> downright(N row, N column) {
		if (sizeClass == BigInteger.class) {
			return new GridPos<N>((N) incrementRow((BigInteger) row), (N) incrementColumn((BigInteger) column));
		} else {
			return new GridPos<N>((N) incrementRow((Integer) row), (N) incrementColumn((Integer) column));
		}
	}

	@SuppressWarnings("unchecked")
	public GridPos<N> right(N row, N column) {
		if (sizeClass == BigInteger.class) {
			return new GridPos<N>(row, (N) incrementColumn((BigInteger) column));
		} else {
			return new GridPos<N>(row, (N) incrementColumn((Integer) column));
		}
	}

	public GridPos<N>[] neighboursPos8(N row, N column) {
		GridPos<N>[] neighbours = new GridPos[8];
		neighbours[0] = top(row, column);
		neighbours[1] = down(row, column);
		neighbours[2] = left(row, column);
		neighbours[3] = right(row, column);
		neighbours[4] = topleft(row, column);
		neighbours[5] = topright(row, column);
		neighbours[6] = downleft(row, column);
		neighbours[7] = downright(row, column);
		return neighbours;

	}
	public GridPos<N>[] neighboursPos4(N row, N column) {
		GridPos<N>[] neighbours = new GridPos[4];
		neighbours[0] = top(row, column);
		neighbours[1] = down(row, column);
		neighbours[2] = left(row, column);
		neighbours[3] = right(row, column);
		return neighbours;

	}

}
