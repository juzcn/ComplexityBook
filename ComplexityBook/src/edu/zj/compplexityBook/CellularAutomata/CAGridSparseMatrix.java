package edu.zj.compplexityBook.CellularAutomata;

import edu.zj.compplexityBook.utils.SparseMatrix.Position;
import edu.zj.compplexityBook.utils.SparseMatrix.SparseMatrix;

public class CAGridSparseMatrix<T, N extends Number> extends SparseMatrix<T, N> implements CAGrid<T, N> {
	private final T asNull;

	public CAGridSparseMatrix(T asNull) {
		super();
		this.asNull = asNull;
	}

	public CAGridSparseMatrix(N rowSize, N columnSize, T asNull) {
		super(rowSize, columnSize);
		this.asNull = asNull;
	}

	public abstract class Cell<S extends Enum<S>, A extends Enum<A>> {
		protected final Element element;
		protected A action;

		public Cell(Element element) {
			this.element = element;
		}

		public abstract void evaluate();

		public abstract void doAction();
	}

	@Override
	public T getData(Position<N> pos) {
		T state = super.getData(pos);
		if (state == null)
			state = asNull;
		return state;
	}

	@Override
	public void setData(Position<N> pos, T data) {
		if (data == null || data.equals(asNull))
			super.setData(pos, null);
		else
			super.setData(pos, data);
	}


}
