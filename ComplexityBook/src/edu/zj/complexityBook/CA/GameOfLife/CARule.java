package edu.zj.complexityBook.CA.GameOfLife;

public class CARule {
	private final int current;
	private final int neighbCount;
	private int next;

	public CARule(int current, int neighbCount, int next) {
		this.current = current;
		this.neighbCount = neighbCount;
		this.next = next;
	}

	public static CARule[] ruleTable(int ruleNumber) {
		CARule[] rules = new CARule[10];
		for (int i = 0; i < 5; i++) {
			rules[10 - 2 * i - 1] = new CARule(0, i, ruleNumber % 2);
			ruleNumber /= 2;
			rules[10 - 2 * i - 2] = new CARule(1, i, ruleNumber % 2);
			ruleNumber /= 2;
		}
		return rules;
	}

	public int getCurrent() {
		return current;
	}

	public int getNeighbCount() {
		return neighbCount;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}
}
