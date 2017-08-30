package edu.zj.complexityBook.CA;

import edu.zj.utils.Grid.Model.BoundCondition;
import edu.zj.utils.Grid.Model.Grid;

public final class CAGrid extends Grid<Integer> {
	private final String[] states;
	private final int nbStates;

	public CAGrid(int rowSize, int columnSize, BoundCondition<Integer>[] boundCondition, String[] states) {
		super(rowSize, columnSize, boundCondition);
		this.states = states;
		this.nbStates = states.length;
	}

	public CAGrid(int rowSize, int columnSize, BoundCondition<Integer>[] boundCondition, int nbStates) {
		super(rowSize, columnSize, boundCondition);
		states=null;
		this.nbStates = nbStates;
	}

	public CAGrid(int rowSize, int columnSize, String[] states) {
		super(rowSize, columnSize);
		this.states = states;
		this.nbStates = states.length;
	}

	public CAGrid(int rowSize, int columnSize, int nbStates) {
		super(rowSize, columnSize);
		states=null;
		this.nbStates = nbStates;
	}

	public int getNbStates() {
		return nbStates;
	}
	
	public String toString(int state) {
		if (states==null) return Integer.toString(state);
		return states[state];
	}
}
