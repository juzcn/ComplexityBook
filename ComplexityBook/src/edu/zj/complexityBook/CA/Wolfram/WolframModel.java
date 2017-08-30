package edu.zj.complexityBook.CA.Wolfram;

import edu.zj.complexityBook.CA.CAGrid;
import edu.zj.complexityBook.CA.CAModel;
import edu.zj.utils.Grid.Model.Grid.Direction;
import edu.zj.utils.Grid.Model.GridNeighb;

public class WolframModel extends CAModel<WolframModel> {
	public WolframModel(WolframConfig.ModelParams params) {
		super(1, params.getColumnCount());
		this.setTitle("Wolfram CA");
		this.setRandomSeed(50L);
		setRules(params.getRuleNumber());
		CAGrid grid = new CAGrid(1, params.getColumnCount(), new String[] { "white", "black" });
		if (params.isRand()) {
			for (int i = 0; i < params.getColumnCount(); i++) {
				grid.set(0, i, getRandom().nextInt(2));
			}
		} else {
			for (int i = 0; i < params.getColumnCount(); i++) {
				grid.set(0, i, 0);
			}
			grid.set(0, params.getColumnCount()/2,1);
		}
		this.setGrid(grid);
	}

	private static int[] rules;

	public static void setRules(int ruleNumber) {
		rules = new int[8];
		String binary = Integer.toBinaryString(ruleNumber);
		int lz = 8 - binary.length();
		for (int i = 0; i < lz; i++) {
			binary = '0' + binary;
		}
		System.out.println("Binary" + binary);
		for (int j = 0; j < 8; j++) {
			rules[j] = binary.charAt(7 - j) - '0';
			System.out.println("Rule No " + j + " New State == " + rules[j]);
		}
	}

	public static int ruleBy(int[] states) {
		String s = Integer.toString(states[0]) + Integer.toString(states[1]) + Integer.toString(states[2]);
		return rules[Integer.valueOf(s, 2)];
	}

	@Override
	protected Integer evaluate(int row, int column) {
		int[] states = new int[3];
		GridNeighb<Integer> left = getGrid().neighb(row, column, Direction.LEFT);
		GridNeighb<Integer> right = getGrid().neighb(row, column, Direction.RIGHT);
		states[0] = left.getValue();
		states[1] = getGrid().get(row, column);
		states[2] = right.getValue();
		return ruleBy(states);
	}

}
