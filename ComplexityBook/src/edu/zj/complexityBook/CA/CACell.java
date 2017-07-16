package edu.zj.complexityBook.CA;

import edu.zj.utils.Grid.GridCell;
import edu.zj.utils.SparseMatrix.AbstractMatrix;

public abstract class CACell<S extends Enum<S>, N extends Number, C extends AbstractMatrix<S, N>> extends GridCell<S, N>
		implements I_CACell {
	protected C caGrid;
	protected S nextState;

	public CACell(C caGrid, N row, N column, S state) {
		super(row, column, state);
		this.caGrid = caGrid;
	}

	public final void update() {
		if (nextState != null) {
			setData(nextState);
			caGrid.setData(getRow(), getColumn(), getData());
		}
	}

}
