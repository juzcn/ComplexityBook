package edu.zj.complexityBook.CA.GameOfLife;

import java.math.BigInteger;

import edu.zj.utils.Grid.Model.Grid.NeighbType;


public class CARuleTable {

	public static BigInteger TWO = new BigInteger("2");
	private final CARule[] rules;
	private final NeighbType type;

	public CARuleTable(NeighbType type) {
		this.type = type;
		if (type == NeighbType.кдаз╬с)
			rules = new CARule[10];
		else
			rules = new CARule[18];
		for (int i = 0; i < rules.length/2; i++) {
			rules[rules.length - 2 * i - 1] = new CARule(0, i, 0);
			rules[rules.length - 2 * i - 2] = new CARule(1, i, 1);
		}
	}

	public void update(int current, int neighbCount, int next) {
		for (CARule r : rules) {
			if (r.getCurrent() == current && r.getNeighbCount() == neighbCount) {
				r.setNext(next);
			}
		}
	}

	public CARuleTable(int ruleNumber) {
		type = NeighbType.кдаз╬с;
		rules = new CARule[10];
		for (int i = 0; i < 5; i++) {
			rules[10 - 2 * i - 1] = new CARule(0, i, ruleNumber % 2);
			ruleNumber /= 2;
			rules[10 - 2 * i - 2] = new CARule(1, i, ruleNumber % 2);
			ruleNumber /= 2;
		}
	}

	public CARuleTable(BigInteger ruleNumber) {
		type = NeighbType.╟каз╬с;
		rules = new CARule[18];
		for (int i = 0; i < 9; i++) {
			rules[18 - 2 * i - 1] = new CARule(0, i, ruleNumber.remainder(TWO).intValue());
			ruleNumber = ruleNumber.divide(TWO);
			rules[18 - 2 * i - 2] = new CARule(1, i, ruleNumber.remainder(TWO).intValue());
			ruleNumber = ruleNumber.divide(TWO);
		}
	}

	public int byRule(int current, int neighbCount) {
		for (CARule rule : rules) {
			if (rule.getCurrent() == current && rule.getNeighbCount() == neighbCount)
				return rule.getNext();
		}
		throw new NoSuchRuleException("Current=" + current + " NeighbCount " + neighbCount);
	}

	public NeighbType getType() {
		return type;
	}

	public static CARuleTable gameOfLife() {
		CARuleTable ruleTable = new CARuleTable(NeighbType.╟каз╬с);
		ruleTable.update(1, 0, 0);
		ruleTable.update(1, 1, 0);
		ruleTable.update(1, 4, 0);
		ruleTable.update(1, 5, 0);
		ruleTable.update(1, 6, 0);
		ruleTable.update(1, 7, 0);
		ruleTable.update(1, 8, 0);
		ruleTable.update(0, 3, 1);
		return ruleTable;
	}

}
