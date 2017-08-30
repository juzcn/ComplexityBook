package edu.zj.complexityBook.MAS.Grid;

import java.util.ArrayList;
import java.util.List;

import edu.zj.complexityBook.MAS.Agent;
import edu.zj.utils.Grid.Model.Grid;
import edu.zj.utils.Grid.Model.GridNeighb;
import edu.zj.utils.Grid.Model.GridPos;

public abstract class GridAgent<W extends GridWorld<W, V,  A>, V,A extends GridAgent<W, V,  A>>
		extends Agent<W, Grid<V>, A> {
	private GridPos pos;

	public GridAgent(W world, GridPos pos) {
		super(world);
		this.pos = pos;
	}

	public void moveTo(GridPos pos) {
		GridPos np = world.getEnvironment().transform(pos);
		if (np != null)
			this.pos = np;
		else {
			die();
		}
	}

	public List<A> neighb() {
		List<GridNeighb<V>> neighbs = world.getEnvironment().neighbs(pos);
		List<A> list = new ArrayList<>();
		for (GridNeighb<V> p : neighbs) {
			list.addAll(world.getAgentList(p.getPos()));
		}
		return list;
	}

	public List<GridPos> empties() {
		List<GridNeighb<V>> neighbs = world.getEnvironment().neighbs(pos);
		List<GridPos> list = new ArrayList<>();
		for (GridNeighb<V> p : neighbs) {
			if (!world.hasAgent(p.getPos()))
				list.add(p.getPos());
		}
		return list;
	}

	public GridPos getPos() {
		return pos;
	}

	public void setPos(GridPos pos) {
		this.pos = pos;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+pos;
	}
}
