package edu.zj.complexityBook.CellularAutomata.Wolfram;

import java.util.Random;

import edu.zj.complexityBook.CA.I_CACell;
import edu.zj.complexityBook.CA.I_CAData;
import edu.zj.complexityBook.CellularAutomata.Wolfram.WolframCell.State;
import edu.zj.utils.Grid.Data.Grid;

public class WolframData extends Grid<WolframCell.State> implements I_CAData {
	// Random random=new Random(1000l);
	Random random = new Random();
	private I_CACell[] cells;

	public I_CACell[] getCells() {
		return cells;
	}

	public void setCells(I_CACell[] cells) {
		this.cells = cells;
	}

	public WolframData(int columnSize, int ruleNumber) {
		super(1, columnSize, true);
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

	public final void setData(int i, WolframCell.State state) {
		setData(0, i, state);
	}

	public final WolframCell.State getData(int i) {
		return getData(0, i);
	}

	private WolframCell.State[] rules;

	public WolframCell.State ruleBy(WolframCell.State[] states) {
		String s = Integer.toString(states[0].ordinal()) + Integer.toString(states[1].ordinal())
				+ Integer.toString(states[2].ordinal());
		return rules[Integer.valueOf(s, 2)];
	}

	public WolframCell.State[] rules(int ruleNumber) {
		State[] rules = new WolframCell.State[8];
		String binary = Integer.toBinaryString(ruleNumber);
		int lz = 8 - binary.length();
		for (int i = 0; i < lz; i++) {
			binary = '0' + binary;
		}
		System.out.println("Binary" + binary);
		for (int j = 0; j < 8; j++) {
			rules[j] = State.values()[binary.charAt(7 - j) - '0'];
			System.out.println("Rule No " + j + " New State == " + rules[j]);
		}
		return rules;
	}

	public I_CACell[] cells() {
		I_CACell[] cells = new WolframCell[size()];
		for (int i = 0; i < size(); i++) {
			cells[i] = new WolframCell(this, element(i).getRow(), element(i).getColumn(), element(i).getData());
		}
		return cells;
	}

}
