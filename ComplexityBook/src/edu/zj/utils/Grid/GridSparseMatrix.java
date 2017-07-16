package edu.zj.utils.Grid;

import edu.zj.utils.SparseMatrix.SparseMatrix;

public abstract class GridSparseMatrix<T, N extends Number> extends SparseMatrix<T, N>  {
	public GridSparseMatrix(T asNull) {
		super(asNull);
	}

	public GridSparseMatrix(N rowSize, N columnSize, T asNull) {
		super(rowSize, columnSize,asNull);
	}

}
