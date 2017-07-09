package edu.zj.compplexityBook.CA;

import edu.zj.compplexityBook.utils.SparseMatrix;

public class CADataUnbounded<T extends Enum<T>> extends SparseMatrix<T> implements CAData<T> {

	@Override
	public int getRowSize() {
		return 0;
	}

	@Override
	public int getColumnSize() {
		return 0;
	}
}
