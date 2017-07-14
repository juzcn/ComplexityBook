package edu.zj.compplexityBook.CellularAutomata.GameOfLife;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import edu.zj.compplexityBook.CellularAutomata.CAGridSparseMatrix;
import edu.zj.compplexityBook.CellularAutomata.CAGridSparseMatrix.Cell;
import edu.zj.compplexityBook.utils.SparseMatrix.AbstractMatrix;
import edu.zj.compplexityBook.utils.SparseMatrix.Position;

public class GolData extends CAGridSparseMatrix<GolData.State, BigInteger> {
	public GolData(State asNull) {
		super(asNull);
	}

	public static enum State {
		dead, alive
	}

	public static enum Action {
		die, birth
	}

	public class GolCell extends Cell {

		public GolCell(Element element) {
			super(element);
		}

		@Override
		public void evaluate() {
			int numAlives = 0;
			nextState=null;
			Position<BigInteger>[] neighbs = neighboursPos(new Position<>(element.getRow(), element.getColumn()));
			for (int i = 0; i < neighbs.length; i++) {
				if (getData(neighbs[i].getRow(), neighbs[i].getColumn()) == State.alive) {
					numAlives++;
				}
			}
			if (element.getData() == State.alive) {
				if (numAlives < 2 || numAlives > 3) {
					nextState = State.dead;
				}
			} else {
				if (numAlives == 3) {
					nextState = State.alive;
				}
			}
			// System.out.println("Row = "+ getRow()+ " Column = "+
			// getColumn()+" Alives "+numAlives);
		}

	}

	public static BigInteger ONE = new BigInteger("1");

	public Position<BigInteger>[] neighboursPos(Position<BigInteger> current) {
		Position<BigInteger>[] neighbours = new Position[8];
		neighbours[0] = new Position<>(current.getRow(), current.getColumn().add(ONE));
		neighbours[1] = new Position<>(current.getRow(), current.getColumn().subtract(ONE));
		neighbours[2] = new Position<>(current.getRow().add(ONE), current.getColumn().add(ONE));
		neighbours[3] = new Position<>(current.getRow().add(ONE), current.getColumn().subtract(ONE));
		neighbours[4] = new Position<>(current.getRow().subtract(ONE), current.getColumn().add(ONE));
		neighbours[5] = new Position<>(current.getRow().subtract(ONE), current.getColumn().subtract(ONE));
		neighbours[6] = new Position<>(current.getRow().add(ONE), current.getColumn());
		neighbours[7] = new Position<>(current.getRow().subtract(ONE), current.getColumn());
		return neighbours;

	}

	Set<GolCell> cells = new HashSet<>();

	public void evaluate() {
		Set<Element> all = get(State.alive);
		// System.out.println("All alive Cell Size "+all.size());
		for (Element entry : all) {
			cells.add(new GolCell(entry));
			Position<BigInteger>[] neighbs = neighboursPos(new Position(entry.getRow(), entry.getColumn()));
			for (int i = 0; i < neighbs.length; i++) {
				cells.add(new GolCell(new Element(neighbs[i].getRow(), neighbs[i].getColumn(),
						getData(neighbs[i].getRow(), neighbs[i].getColumn()))));
			}
		}
		// System.out.println("Cell Size "+cells.size());
		for (GolCell cell : cells) {
			cell.evaluate();
		}

	}

	public void update() {
		for (GolCell cell : cells) {
			cell.update();
		}

	}


}
