package edu.zj.compplexityBook.CA;

import edu.zj.compplexityBook.utils.SparseMatrix.Matrix;

public class CAGridMatrix<T> extends Matrix<T> implements CAGrid<T,Integer>{

	public CAGridMatrix(Integer rowSize, Integer columnSize) {
		super(rowSize, columnSize);
	}

	public abstract class Cell<S extends Enum<S>, A extends Enum<A>> {
		protected final Element element;

		public Cell(Element element) {
			this.element = element;
		}

		public abstract A evaluate();

		public abstract void doAction(A action);
	}

}
