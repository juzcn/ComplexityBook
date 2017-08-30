package edu.zj.complexityBook.MAS.Termites;

import edu.zj.complexityBook.CA.CAGrid;
import edu.zj.complexityBook.MAS.Grid.GridWorld;
import edu.zj.utils.Grid.Model.Grid;
import edu.zj.utils.Grid.Model.Grid.NeighbType;
import edu.zj.utils.Grid.Model.GridPos;

public class TermitesWorld extends GridWorld<TermitesWorld,Integer,TermiteAgent> {

	public TermitesWorld(TermitesConfig.ModelParams params) {
		this.setTitle("°×ÒÏÄ£ÐÍ");
		Grid<Integer> grid = new Grid<>(params.getRowCount(), params.getColumnCount());
		for (int i = 0; i < grid.getRowSize(); i++) {
			for (int j = 0; j < grid.getColumnSize(); j++) {
				grid.set(i, j, 0);
			}
		}
		for (int i = 0; i < grid.getRowSize(); i++) {
			for (int j = 0; j < grid.getColumnSize(); j++) {
				if (getRandom().nextDouble() < params.getChipPercent()) {
					grid.set(i, j, grid.get(i, j) + 1);
				}
			}
		}
		grid.setNeighbType(NeighbType.ËÄÁÚ¾Ó);
		this.setEnvironment(grid);
		int row, column;
		for (int i = 0; i < params.getAgentCount(); i++) {
			row = getRandom().nextInt(grid.getRowSize());
			column = getRandom().nextInt(grid.getColumnSize());
			addAgent(new TermiteAgent(this, new GridPos(row,column)));
		}

	}

}
