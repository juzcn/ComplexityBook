package edu.zj.complexityBook.MAS.VirtualAnts;

import edu.zj.complexityBook.MAS.Grid.GridWorld;
import edu.zj.utils.Grid.Model.Grid;
import edu.zj.utils.Grid.Model.Grid.Direction;
import edu.zj.utils.Grid.Model.GridPos;

public class AntWorld extends GridWorld<AntWorld, Integer,AntAgent> {
	public AntWorld(AntsConfig.ModelParams params) {
		this.setTitle("–Èƒ‚¬Ï“œœµÕ≥");
		Grid<Integer> grid=new Grid<>(params.getRowCount(),params.getColumnCount());
		for (int i = 0; i < grid.getRowSize(); i++) {
			for (int j = 0; j < grid.getColumnSize(); j++) {
				grid.set(i, j, params.getInitial());
			}
		}
		this.setEnvironment(grid);
		for (int i = 0; i < params.getAgentCount(); i++) {
			AntAgent a=new AntAgent(this, new GridPos(params.getRowCount()/2, params.getColumnCount()/2));
			a.setPrev(Direction.RIGHT);
			addAgent(a);
		}
		this.setShuffle(params.isShuffle());
	}
	
}
