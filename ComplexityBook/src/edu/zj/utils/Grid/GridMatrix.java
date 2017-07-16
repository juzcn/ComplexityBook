package edu.zj.utils.Grid;

import edu.zj.utils.SparseMatrix.Matrix;

public abstract  class GridMatrix<T> extends Matrix<T> {
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


	public GridMatrix(Integer rowSize, Integer columnSize) {
		super(rowSize, columnSize);
	}

}
