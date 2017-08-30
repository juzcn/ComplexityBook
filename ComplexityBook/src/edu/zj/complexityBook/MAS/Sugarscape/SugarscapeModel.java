package edu.zj.complexityBook.MAS.Sugarscape;

import edu.zj.complexityBook.CA.CAGrid;
import edu.zj.complexityBook.MAS.Grid.GridWorld;
import edu.zj.utils.Grid.Model.BoundCondition.Condition;
import edu.zj.utils.Grid.Model.Grid;
import edu.zj.utils.Grid.Model.Grid.NeighbType;
import edu.zj.utils.Grid.Model.GridPos;

public class SugarscapeModel extends GridWorld<SugarscapeModel, Integer,SugarscapeAgent>  {
	private Grid<Integer> initials;
	private SugarscapeConfig.ModelParams params;

	public SugarscapeModel(SugarscapeConfig.ModelParams params) {
		this.setTitle("Sugarscape模型（糖域模型）");
		this.params = params;
		Grid<Integer> grid= Grid.newGrid(params.getMapFileName(),Integer.class);
		grid.setBoundCondition(Condition.PERIODIC, params.getMaximum_vision());
		grid.setNeighbType(NeighbType.四邻居);
		this.setEnvironment(grid);
		this.setRandomSeed(params.getRandomSeed());
		
		initials = new Grid<>(grid.getRowSize(), grid.getColumnSize());
		System.out.println("rowSize "+grid.getRowSize());
		System.out.println("columnSize "+grid.getColumnSize());
		for (int i=0;i<grid.getRowSize();i++) {
			for (int j=0;j<grid.getColumnSize();j++) {
				initials.set(i, j, grid.get(i, j));
			}
		}
		int row, column, metabolicRate, vision, deathAge, wealth;
		for (int i = 0; i < params.getAgentCount(); i++) {
			while (true) {
				row = getRandom().nextInt(50);
				column = getRandom().nextInt(50);
				GridPos pos=new GridPos(row,column);
				if (getAgentList(pos).size() == 0) {
					metabolicRate = getRandom().nextInt(params.getMaximum_metabolic_rate()) + 1;
					vision = getRandom().nextInt(params.getMaximum_vision()) + 1;
					deathAge = params.getMinimum_death_age()
							+ getRandom().nextInt(params.getMaximum_death_age() - params.getMinimum_death_age() + 1);
					wealth = params.getMinimum_wealth()
							+ getRandom().nextInt(params.getMaximum_wealth() - params.getMinimum_wealth() + 1);
					addAgent(new SugarscapeAgent(this, pos, metabolicRate, vision, deathAge, wealth));
					break;
				}
			}
		}
		this.setShuffle(params.isShuffle());
		System.out.println("Sugarscape Setup ！");
	}

	@Override
	public void stepRun() {
		super.stepRun();
		// environment rule
		int cell, initial;
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				cell = getEnvironment().get(i, j);
//				System.out.println(initials);
				initial = initials.get(i, j);
				if (cell == initial)
					continue;
				if (cell + params.getGrowthRate() > initial) {
					getEnvironment().set(i, j, initial);
					continue;
				}
				getEnvironment().set(i, j, cell + params.getGrowthRate());
			}
		}
		System.out.println("Agent number " + this.getAgentList().size());
	}


}
