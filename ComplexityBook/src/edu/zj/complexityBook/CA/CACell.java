package edu.zj.complexityBook.CA;

import java.util.ArrayList;
import java.util.List;

import edu.zj.utils.Grid.Model.GridPos;

public abstract class CACell<S, M extends CAModel<S, ? extends CACell<S, M>>> {
	private final GridPos pos;
	private final M model;
	protected S nextState;

	public S getNextState() {
		return nextState;
	}

	public void setNextState(S nextState) {
		this.nextState = nextState;
	}

	public CACell(M model, GridPos pos) {
		this.model = model;
		this.pos = pos;
	}

	public S getState() {
		return this.getModel().getCaGrid().get(pos);
	}

	public List<CACell<S, M>> neighbs4() {
		List<GridPos> neighbs = model.getCaGrid().neighbs4(this.pos);
		List<CACell<S, M>> list = new ArrayList<>();
		CACell<S, M> cell;
		for (GridPos p : neighbs) {
			cell = model.getCellMap().get(p);
			if (cell != null)
				list.add(cell);
		}
		return list;
	}

	public void setState(S state) {
		this.getModel().getCaGrid().set(pos, state);
	}

	public abstract void evaluate();

	public final void update() {
		if (nextState != null) {
			model.getCaGrid().set(pos, nextState);
			nextState = null;
		}
	}

	public M getModel() {
		return model;
	}

	public GridPos getPos() {
		return pos;
	}

}
