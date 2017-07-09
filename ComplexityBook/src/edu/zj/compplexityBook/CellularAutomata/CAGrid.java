package edu.zj.compplexityBook.CellularAutomata;

import java.math.BigInteger;

import edu.zj.compplexityBook.utils.SparseMatrix.Position;

public interface CAGrid<T, N extends Number> {
	public void setData(N row, N column, T data);

	public T getData(N row, N column);

	public N add(N op1, int op2);

	public N substract(N op1, int op2);

	public N add(N op1, N op2);

	public default Position<N>[] neighboursPos(Position<N> current) {
		Position<N>[] neighbours = new Position[8];
		neighbours[0] = new Position<>(current.getRow(), add(current.getColumn(),1));
		neighbours[0] = new Position<>(current.getRow(), substract(current.getColumn(),1));

		neighbours[0] = new Position<>(add(current.getRow(),1), add(current.getColumn(),1));
		neighbours[0] = new Position<>(add(current.getRow(),1), substract(current.getColumn(),1));

		neighbours[0] = new Position<>(substract(current.getRow(),1), add(current.getColumn(),1));
		neighbours[0] = new Position<>(substract(current.getRow(),1), substract(current.getColumn(),1));

		neighbours[6] = new Position<>(add(current.getRow(),1), current.getColumn());
		neighbours[7] = new Position<>(substract(current.getRow(),1), current.getColumn());
		return neighbours;

	}
}
