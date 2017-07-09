package edu.zj.compplexityBook.utils.SparseMatrix;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigInteger;
import java.util.Set;

public abstract class AbstractMatrix<T, N extends Number> {
	protected final N rowSize;
	protected final N columnSize;
	protected final Class sizeClass;

	public class Element extends Position<N> {
		private final T data;

		public Element(N row, N column, T data) {
			super(row, column);
			this.data = data;
		}

		public T getData() {
			return data;
		}

	}

	public AbstractMatrix() {
		this(null, null);
	}

	public N getRowSize() {
		return rowSize;
	}

	public N getColumnSize() {
		return columnSize;
	}

	public AbstractMatrix(N rowSize, N columnSize) {
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.sizeClass = this.getSizeClass();
	}

	public abstract void clear();

	public abstract T getData(N row, N column);

	public abstract void setData(N row, N column, T data);

	public abstract T getData(Position<N> pos);

	public abstract void setData(Position<N> pos, T data);

	public abstract Set<Element> getNotNulls();

	public Element getElement(N row,N column) {
		return new Element(row,column,getData(row,column)); 
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
		while (c != AbstractMatrix.class) {
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

	private Class getSizeClass1() {
		try {
			Method m=this.getClass().getMethod("add", Number.class,Number.class);
			TypeVariable type=(TypeVariable) m.getGenericReturnType();
			
			System.out.println(type);
			
//		((ParameterizedType) type).getRawType();
//			System.out.println(types[0]);			
//			return (Class) type;
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
