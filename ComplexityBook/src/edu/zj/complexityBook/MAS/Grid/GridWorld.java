package edu.zj.complexityBook.MAS.Grid;

import java.util.ArrayList;
import java.util.List;

import edu.zj.complexityBook.MAS.World;
import edu.zj.utils.Grid.Model.Grid;
import edu.zj.utils.Grid.Model.GridPos;

public class GridWorld<W extends GridWorld<W,V, A>, V, A extends GridAgent<W,V, A>> extends World<W, Grid<V>, A> {
	public List<A> getAgentList(GridPos pos) {
		List<A> list = new ArrayList<>();
		for (A a : agentList) {
			if (a.getPos().equals(pos)) {
				list.add(a);
			}
		}
		return list;
	}

	public boolean hasAgent(GridPos pos) {
		for (A a : agentList) {
			if (a.getPos().equals(pos)) {
				return true;
			}
		}
		return false;
	}

	public int getRowCount() {
		return getEnvironment().getRowSize();
	}

	public int getColumnCount() {
		return getEnvironment().getColumnSize();
	}
}
