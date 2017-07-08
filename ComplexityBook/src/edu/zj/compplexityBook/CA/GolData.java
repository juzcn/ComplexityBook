package edu.zj.compplexityBook.CA;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

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

	public class GolCell extends Cell<State, Action> {

		public GolCell(AbstractMatrix<State, BigInteger>.Element element) {
			super(element);
		}

		@Override
		public void evaluate() {
			int numAlives = 0;
			action = null;
			Position<BigInteger>[] neighbs = neighboursPos(new Position<>(element.getRow(), element.getColumn()));
			for (int i = 0; i < neighbs.length; i++) {
				if (getData(neighbs[i].getRow(), neighbs[i].getColumn()) == State.alive) {
					numAlives++;
				}
			}
			if (element.getData() == State.alive) {
				if (numAlives < 2 || numAlives > 3) {
					action = Action.die;
				}
			} else {
				if (numAlives == 3) {
					action = Action.birth;
				}
			}
			// System.out.println("Row = "+ getRow()+ " Column = "+
			// getColumn()+" Alives "+numAlives);
		}

		@Override
		public void doAction() {
			if (action != null) {
				if (action == Action.die) {
					// System.out.println("Row = "+ getRow()+ " Column = "+
					// getColumn()+" Die ");
					setData(element.getRow(), element.getColumn(), State.dead);
				} else {
					// System.out.println("Row = "+ getRow()+ " Column = "+
					// getColumn()+" Birth ");
					setData(element.getRow(), element.getColumn(), State.alive);
				}
			}
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
		Set<Element> all = getNotNulls();
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

	public void apply() {
		for (GolCell cell : cells) {
			cell.doAction();
		}

	}


}
