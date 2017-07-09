package edu.zj.compplexityBook.CellularAutomata;

import edu.zj.compplexityBook.utils.SparseMatrix.Matrix;

public class CAGridMatrix<T> extends Matrix<T> implements CAGrid<T,Integer>{
	public abstract class Cell<S extends Enum<S>, A extends Enum<A>> {
		protected final Element element;
		protected A action;

		public Cell(Element element) {
			this.element = element;
		}

		public abstract void evaluate();

		public abstract void doAction();
	}


	public CAGridMatrix(Integer rowSize, Integer columnSize) {
		super(rowSize, columnSize);
	}

}
