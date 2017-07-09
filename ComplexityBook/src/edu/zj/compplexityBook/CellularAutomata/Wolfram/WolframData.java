package edu.zj.compplexityBook.CellularAutomata.Wolfram;

import java.util.Random;

import edu.zj.compplexityBook.CellularAutomata.CAGridMatrix;

public class WolframData extends CAGridMatrix<WolframData.State> {
	// Random random=new Random(1000l);
	Random random = new Random();

	public static enum State {
		OFF, ON
	}

	public static enum Action {
		TURN_OFF, TURN_ON
	}

	public class WolframCell extends Cell<State, Action> {

		public WolframCell(Element element) {
			super(element);
		}

		@Override
		public void evaluate() {
			State[] states = new State[3];
			int current = element.getColumn();
			states[0] = (current == 0) ? getData(size() - 1) : getData(current - 1);
			states[1] = element.getData();
			states[2] = getData((current + 1) % size());
			action = ruleBy(states);
		}

		@Override
		public void doAction() {
			if (action == Action.TURN_OFF) {
				// System.out.println("Row = "+ getRow()+ " Column = "+
				// getColumn()+" Die ");
				setData(element.getColumn(), State.OFF);
			} else {
				// System.out.println("Row = "+ getRow()+ " Column = "+
				// getColumn()+" Birth ");
				setData(element.getColumn(), State.ON);
			}
		}

	}

	private class Rule {
		// private final State[] states;
		private final Action action;

		// public State[] getStates() {
		// return states;
		// }

		public Action getAction() {
			return action;
		}

		public Rule(Action action) {
			// this.states = states;
			this.action = action;
		}
	}

	public WolframData(int columnSize, int ruleNumber) {
		super(1, columnSize);
		rules = rules(ruleNumber);
		for (int i = 0; i < columnSize; i++) {
			if (random.nextBoolean()) {
				setData(0, i, State.ON);
			} else {
				setData(0, i, State.OFF);
			}
		}
	}

	public final int size() {
		return getColumnSize();
	}

	public final Element element(int i) {
		return getElement(0, i);
	}

	public final void setData(int i, State state) {
		setData(0, i, state);
	}

	public final State getData(int i) {
		return getData(0, i);
	}

	private Rule[] rules;

	public Action ruleBy(State[] states) {
		String s = Integer.toString(states[0].ordinal()) + Integer.toString(states[1].ordinal())
				+ Integer.toString(states[2].ordinal());
		return rules[Integer.valueOf(s, 2)].getAction();
	}

	public Rule[] rules(int ruleNumber) {
		Rule[] rules = new Rule[8];
		String binary = Integer.toBinaryString(ruleNumber);
		int lz=8 - binary.length();
		for (int i = 0; i < lz; i++) {
			binary = '0' + binary;
		}
		System.out.println("Binary" + binary);
		Action action;
		for (int j = 0; j < 8; j++) {
			action = Action.values()[binary.charAt(7-j) - '0'];
			rules[j] = new Rule(action);
			System.out.println("Rule No "+j+" Action == "+action);
		}
		return rules;
	}

	WolframCell[] cells;

	public void evaluate() {
		cells = new WolframCell[size()];
		for (int i = 0; i < size(); i++) {
			cells[i] = new WolframCell(element(i));
			cells[i].evaluate();
		}
	}

	public void apply() {
		for (int i = 0; i < size(); i++) {
			cells[i].doAction();
		}
	}

}
