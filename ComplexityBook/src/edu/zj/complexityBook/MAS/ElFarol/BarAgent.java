package edu.zj.complexityBook.MAS.ElFarol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.zj.complexityBook.MAS.Agent;

public class BarAgent extends Agent<BarWorld, Bar, BarAgent> {
	public static enum State {
		Unhappy, Unknown, Happy
	};
	private List<Rule> ruleSet = new ArrayList<Rule>();
	private Queue<State> history;
	private int historyCount;
	private int ruleExpired;

	public class Rule {
		private final State[] pattern;
		private boolean goBar;
		private int created;

		public Rule(int created, boolean goBar) {
			this.created = created;
			this.goBar = goBar;
			pattern = new State[historyCount];
			history.toArray(pattern);
		}

		public int getCreated() {
			return created;
		}
		public String toString() {
			StringBuffer sb=new StringBuffer();
			sb.append("Created "+created+ " Gobar "+goBar+" Pattern ");
			for (State s:pattern) {
				sb.append(s+",");
			}
			return sb.toString();
		}
	}


	public BarAgent(BarWorld world, int historyCount,int ruleExpired) {
		super(world);
		history = new LinkedList<>();
		for (int i = 0; i < historyCount; i++) {
			history.offer(State.Unknown);
		}
		this.historyCount = historyCount;
		this.ruleExpired=ruleExpired;
	}

	Rule selectedRule;

	public void stepRun1() {
		if (world.getRandom().nextBoolean())
			world.getEnvironment().gotoBar(this);
		else
			setState(State.Unknown);
	}

	public void stepRun() {
		selectedRule = null;
		for (Rule r : ruleSet) {
			State[] historyPattern = new State[historyCount];
			history.toArray(historyPattern);
			int i;
			for (i = 0; i < historyCount; i++) {
				if (historyPattern[i].ordinal() != r.pattern[i].ordinal())
					break;
//				if (r.goBar && historyPattern[i].ordinal() < r.pattern[i].ordinal())
//					break;
//				if (!r.goBar && historyPattern[i].ordinal() > r.pattern[i].ordinal())
//					break;
			}
			if (i == historyCount) {
				selectedRule = r;
				break;
			}
		}
		if (selectedRule != null) {
			if (selectedRule.goBar)
				world.getEnvironment().gotoBar(this);
		} else {
			if (world.getRandom().nextBoolean())
				world.getEnvironment().gotoBar(this);
		}
	}

	public void setState(State state) {
		if (state == State.Happy) {
			if (selectedRule != null) {
				selectedRule.created = world.getStep();
			} else
				ruleSet.add(new Rule(world.getStep(), true));
		} else if (state == State.Unhappy) {
			if (selectedRule != null) {
				selectedRule.goBar = false;
				selectedRule.created = world.getStep();
			} else
				ruleSet.add(new Rule(world.getStep(), false));
		}
		System.out.println(this + " state= " + state + " history size "+history.size()+" ruleset size " + ruleSet.size());
		history.poll();
		history.offer(state);
	}

	public void clearOutdatedRule() {
		Iterator<Rule> i = ruleSet.iterator();
		while (i.hasNext()) {
			if (world.getStep() - i.next().getCreated() >= ruleExpired) {
				i.remove();
			}
		}

	}
}
