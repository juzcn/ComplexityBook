package edu.zj.compplexityBook.CA;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import edu.zj.compplexityBook.utils.SparseMatrix.SparseMatrix;
import edu.zj.compplexityBook.utils.SparseMatrix.SparseMatrix.Position;

public class GolData {
	public static enum State {
		dead, alive
	}

	public static enum Action {
		die, birth
	}

	public class GolCell extends Position {

		public void evaluate() {
			numAlives = 0;
			Position[] neighbs = neighboursPos(new Position(getRow(), getColumn()));
			for (int i = 0; i < neighbs.length; i++) {
				if (getData(neighbs[i].getRow(), neighbs[i].getColumn()) == State.alive) {
					numAlives++;
				}
			}
			if (state == State.alive) {
				if (numAlives < 2 ||numAlives > 3 ) {
					action = Action.die;
					return;
				}
			} else {
				if (numAlives==3) {
					action=Action.birth;
				}
			}
			System.out.println("Row = "+ getRow()+ " Column = "+ getColumn()+" Alives "+numAlives);
		}

		public void apply() {
			if (action!=null) {
				if (action==Action.die) {
					System.out.println("Row = "+ getRow()+ " Column = "+ getColumn()+" Die ");
					setData(State.dead,getRow(),getColumn());
				} else {
					System.out.println("Row = "+ getRow()+ " Column = "+ getColumn()+" Birth ");
					setData(State.alive,getRow(),getColumn());
					state=State.alive;
				}
			}
		}

		private State state;
		private Action action;
		private int numAlives;

		public GolCell(BigInteger row, BigInteger column, State state) {
			super(row, column);
			this.state = state;
		}
	}

	private SparseMatrix<State> data = new SparseMatrix<>();;

	Set<GolCell> cells = new HashSet<>();

	public void setData(State s, Position pos) {
		if (s == State.alive)
			data.setData(s, pos);
		else
			data.clear(pos);
	}

	public void setData(State s, BigInteger row, BigInteger column) {
		data.setData(s, new Position(row, column));
	}

	public State getData(Position pos) {
		State s = data.getData(pos);
		if (s == null)
			return State.dead;
		else
			return s;
	}

	public State getData(BigInteger row, BigInteger column) {
		State s = data.getData(new Position(row, column));
		if (s == null)
			return State.dead;
		else
			return s;
	}

	public static BigInteger ONE = new BigInteger("1");

	public static Position[] neighboursPos(Position current) {
		Position[] neighbours = new Position[8];
		neighbours[0] = new Position(current.getRow(), current.getColumn().add(ONE));
		neighbours[1] = new Position(current.getRow(), current.getColumn().subtract(ONE));
		neighbours[2] = new Position(current.getRow().add(ONE), current.getColumn().add(ONE));
		neighbours[3] = new Position(current.getRow().add(ONE), current.getColumn().subtract(ONE));
		neighbours[4] = new Position(current.getRow().subtract(ONE), current.getColumn().add(ONE));
		neighbours[5] = new Position(current.getRow().subtract(ONE), current.getColumn().subtract(ONE));
		neighbours[6] = new Position(current.getRow().add(ONE), current.getColumn());
		neighbours[7] = new Position(current.getRow().subtract(ONE), current.getColumn());
		return neighbours;

	}

	public void evaluate() {
		Set<Entry<Position, State>> all = data.getAll();
		cells.clear();
		System.out.println("All alive Cell Size "+all.size());
		for (Entry<Position, State> entry : all) {
			cells.add(new GolCell(entry.getKey().getRow(), entry.getKey().getColumn(), entry.getValue()));
			Position[] neighbs = neighboursPos(entry.getKey());
			for (int i = 0; i < neighbs.length; i++) {
				cells.add(new GolCell(neighbs[i].getRow(), neighbs[i].getColumn(),
						getData(neighbs[i].getRow(), neighbs[i].getColumn())));
			}
		}
		System.out.println("Cell Size "+cells.size());
		for (GolCell cell : cells) {
			cell.evaluate();
		}

	}

	public void apply() {
		for (GolCell cell : cells) {
			cell.apply();
		}

	}

}
