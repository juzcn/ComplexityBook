package edu.zj.compplexityBook.CellularAutomata.Wolfram;

import java.util.Random;

import edu.zj.compplexityBook.CellularAutomata.CAGridMatrix;

public class WolframData extends CAGridMatrix<WolframData.State> {
	// Random random=new Random(1000l);
	Random random = new Random();

	public static enum State {
		OFF, ON
	}

	public class WolframCell extends Cell {

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
			nextState = ruleBy(states);
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

	private State[] rules;

	public State ruleBy(State[] states) {
		String s = Integer.toString(states[0].ordinal()) + Integer.toString(states[1].ordinal())
				+ Integer.toString(states[2].ordinal());
		return rules[Integer.valueOf(s, 2)];
	}

	public State[] rules(int ruleNumber) {
		State[] rules = new State[8];
		String binary = Integer.toBinaryString(ruleNumber);
		int lz=8 - binary.length();
		for (int i = 0; i < lz; i++) {
			binary = '0' + binary;
		}
		System.out.println("Binary" + binary);
		for (int j = 0; j < 8; j++) {
			rules[j] = State.values()[binary.charAt(7-j) - '0'];
			System.out.println("Rule No "+j+" New State == "+rules[j]);
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

	public void update() {
		for (int i = 0; i < size(); i++) {
			cells[i].update();
		}
	}

}
