package edu.zj.complexityBook.CellularAutomata;

import edu.zj.utils.SparseMatrix.Position;
import edu.zj.utils.SparseMatrix.SparseMatrix;
import edu.zj.utils.SparseMatrix.AbstractMatrix.Element;

public abstract class CAGridSparseMatrix<T, N extends Number> extends SparseMatrix<T, N> implements CAGrid<T, N> {
	public CAGridSparseMatrix(T asNull) {
		super(asNull);
	}

	public CAGridSparseMatrix(N rowSize, N columnSize, T asNull) {
		super(rowSize, columnSize,asNull);
	}

	public abstract class Cell {
		protected final Element element;
		protected T nextState;

		public Cell(Element element) {
			this.element = element;
		}

		public abstract void evaluate();

		public void update() {
			if (nextState != null) {
					setData(element.getRow(), element.getColumn(), nextState);
			}

		}
	}


}
