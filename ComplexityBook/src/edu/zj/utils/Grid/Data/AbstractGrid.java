package edu.zj.utils.Grid.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Set;

public abstract class AbstractGrid<T, N extends Number> {
	protected final N rowSize;
	protected final N columnSize;
	protected final boolean wrapped;

	protected final Class<N> sizeClass;

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


	private Class<N> getSizeClass() {
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
						return (Class<N>) BigInteger.class;
					}
				}
			} else
				c = (Class) type;
		}
		return (Class<N>) Integer.class;

	}
	@SuppressWarnings("unchecked")
	public final N add(N op1, int op2) {
		if (op1==null) {
			if (this.sizeClass==BigInteger.class) {
				return (N) new BigInteger(Integer.toString(op2));
			}
			return (N) new Integer(op2);
		}
		if (op1 instanceof Integer)
			return (N) new Integer(((Integer) op1) + op2);
		if (op1 instanceof BigInteger)
			return (N) ((BigInteger) op1).add(new BigInteger(Integer.toString(op2)));
		throw new UnsupportedOperationException("add operation not yet implemented.");
	}

	public final Integer incrementRow(Integer row) {
		row++;
		if (row.equals((Integer) getRowSize())) {
			if (wrapped)
				return 0;
			return null;
		}
		return row;
	}

	public final BigInteger incrementRow(BigInteger row) {
		row = row.add(BigInteger.ONE);
		if (row.equals((BigInteger) getRowSize())) {
			if (wrapped)
				return BigInteger.ZERO;
			return null;
		}
		return row;
	}

	public final Integer decrementRow(Integer row) {
		row--;
		if (row == -1) {
			if (wrapped)
				return (Integer) getRowSize() - 1;
			return null;
		}
		return row;
	}

	public final static BigInteger NEGATIVE_ONE = new BigInteger("-1");

	public final BigInteger decrementRow(BigInteger row) {
		row = row.subtract(BigInteger.ONE);
		if (row.equals(NEGATIVE_ONE)) {
			if (wrapped)
				return ((BigInteger) getRowSize()).subtract(BigInteger.ONE);
			return null;
		}
		return row;
	}

	public final Integer incrementColumn(Integer column) {
		column++;
		if (column.equals((Integer) getColumnSize())) {
			if (wrapped)
				return 0;
			return null;
		}
		return column;
	}

	public final BigInteger incrementColumn(BigInteger column) {
		column = column.add(BigInteger.ONE);
		if (column.equals((BigInteger) getColumnSize())) {
			if (wrapped)
				return BigInteger.ZERO;
			return null;
		}
		return column;
	}

	public final Integer decrementColumn(Integer column) {
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
	public GridPos<N>[] neighboursPos8(N row, N column) {
		GridPos<N>[] neighbours = new GridPos[8];
		for (int i=0;i<8;i++) {
			neighbours[i]=neighb(row,column,Direction.values()[i]);
		}
		return neighbours;
	}

	@SuppressWarnings("unchecked")
	public GridPos<N>[] neighboursPos4(N row, N column) {
		GridPos<N>[] neighbours = new GridPos[4];
		for (int i=0;i<4;i++) {
			neighbours[i]=neighb(row,column,Direction.values()[i]);
		}
		return neighbours;

	}

	public enum Direction {
		TOP, RIGHT, DOWN, LEFT, TOP_LEFT, TOP_RIGHT, DOWN_RIGHT, DOWN_LEFT
	};

	@SuppressWarnings("unchecked")
	public final GridPos<N> neighb(N row, N column, Direction direction) {
		switch (direction) {
		case TOP:
			if (row instanceof BigInteger) {
				return new GridPos<N>((N) decrementRow((BigInteger) row), column);
			} else {
				return new GridPos<N>((N) decrementRow((Integer) row), column);
			}
		case RIGHT:
			if (row instanceof BigInteger) {
				return new GridPos<N>(row, (N) incrementColumn((BigInteger) column));
			} else {
				return new GridPos<N>(row, (N) incrementColumn((Integer) column));
			}
		case DOWN:
			if (row instanceof BigInteger) {
				return new GridPos<N>((N) incrementRow((BigInteger) row), column);
			} else {
				return new GridPos<N>((N) incrementRow((Integer) row), column);
			}
		case LEFT:
			if (row instanceof BigInteger) {
				return new GridPos<N>(row, (N) decrementColumn((BigInteger) column));
			} else {
				return new GridPos<N>(row, (N) decrementColumn((Integer) column));
			}
		case TOP_LEFT:
			if (row instanceof BigInteger) {
				return new GridPos<N>((N) decrementRow((BigInteger) row), (N) decrementColumn((BigInteger) column));
			} else {
				return new GridPos<N>((N) decrementRow((Integer) row), (N) decrementColumn((Integer) column));
			}
		case TOP_RIGHT:
			if (row instanceof BigInteger) {
				return new GridPos<N>((N) decrementRow((BigInteger) row), (N) incrementColumn((BigInteger) column));
			} else {
				return new GridPos<N>((N) decrementRow((Integer) row), (N) incrementColumn((Integer) column));
			}
		case DOWN_RIGHT:
			if (row instanceof BigInteger) {
				return new GridPos<N>((N) incrementRow((BigInteger) row), (N) incrementColumn((BigInteger) column));
			} else {
				return new GridPos<N>((N) incrementRow((Integer) row), (N) incrementColumn((Integer) column));
			}
		case DOWN_LEFT:
			if (row instanceof BigInteger) {
				return new GridPos<N>((N) incrementRow((BigInteger) row), (N) decrementColumn((BigInteger) column));
			} else {
				return new GridPos<N>((N) incrementRow((Integer) row), (N) decrementColumn((Integer) column));
			}
		default:
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public final N neighb(N rc, Direction direction) {
		switch (direction) {
		case TOP:
			if (rc instanceof BigInteger) {
				return (N) decrementRow((BigInteger) rc);
			} else {
				return (N) decrementRow((Integer) rc);
			}
		case RIGHT:
			if (rc instanceof BigInteger) {
				return (N) incrementColumn((BigInteger) rc);
			} else {
				return (N) incrementColumn((Integer) rc);
			}
		case DOWN:
			if (rc instanceof BigInteger) {
				return (N) incrementRow((BigInteger) rc);
			} else {
				return (N) incrementRow((Integer) rc);
			}
		case LEFT:
			if (rc instanceof BigInteger) {
				return (N) decrementColumn((BigInteger) rc);
			} else {
				return (N) decrementColumn((Integer) rc);
			}
		default:
			return null;
		}
	}

}
