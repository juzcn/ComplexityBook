package edu.zj.complexityBook.CA.Bulb;

public class Bulb {
	private boolean state;
	private int rule;
	private Bulb input1;
	private Bulb input2;

	public Bulb(boolean state, int rule) {
		this.state = state;
		this.rule = rule;
	}
	/*
	   * A bulb's next state is determined by one of four
	   * rules, assigned at random:
	   * 0: the bulb is on if both its inputs are on
	   * 1: the bulb is off if both its inputs are on
	   * 2: the bulb is on if at least one of the inputs is on
	   * 3: the bulb is off if at least one of the inputs is on
	   * 4: the bulb is on if both its inputs are the same
	   * 5: the bulb is off if both its inputs are the same
	   *
	   * This program uses only the first four rules. If you
	   * set MAX_RULE to 6, the cycling is not quick nor apparent.
	   */
	public boolean getNextState() {
		boolean result = false;
		switch (rule) {
		case 0:
			result = input1.state && input2.state;
			break;
		case 1:
			result = !(input1.state || input2.state);
			break;
		case 2:
			result = input1.state || input2.state;
			break;
		case 3:
			result = !(input1.state && input2.state);
			break;
		case 4:
			result = (input1.state == input2.state);
			break;
		case 5:
			result = (input1.state != input2.state);
			break;
		}
		return result;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int getRule() {
		return rule;
	}

	public void setRule(int rule) {
		this.rule = rule;
	}

	public Bulb getInput1() {
		return input1;
	}

	public void setInput1(Bulb input1) {
		this.input1 = input1;
	}

	public Bulb getInput2() {
		return input2;
	}

	public void setInput2(Bulb input2) {
		this.input2 = input2;
	}
}
