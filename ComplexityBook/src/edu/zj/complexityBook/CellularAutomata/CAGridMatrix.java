package edu.zj.complexityBook.CellularAutomata;

import edu.zj.utils.SparseMatrix.Matrix;

public abstract  class CAGridMatrix<T> extends Matrix<T> implements CAGrid<T,Integer>{
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


	public CAGridMatrix(Integer rowSize, Integer columnSize) {
		super(rowSize, columnSize);
	}

}
