package edu.zj.complexityBook.CA.ForestFire;

import java.util.List;

import edu.zj.complexityBook.CA.CAGrid;
import edu.zj.complexityBook.CA.CAModel;
import edu.zj.utils.Grid.Model.Grid.NeighbType;
import edu.zj.utils.Grid.Model.GridNeighb;

public class ForestFireModel extends CAModel<ForestFireModel> {
	double initialDensity;
	double initialOnFire;
	double growth;

	public double getGrowth() {
		return growth;
	}

	public double getGrowthOnBurned() {
		return growthOnBurned;
	}

	public double getOnFire() {
		return onFire;
	}

	double growthOnBurned;
	double onFire;

	public ForestFireModel(ForestFireConfig.ModelParams params) {
		super(params.getRowCount(), params.getColumnCount());
		this.setTitle("森林火灾模型");
		CAGrid grid = new CAGrid(params.getRowCount(), params.getColumnCount(),4);
		for (int i = 0; i < params.getRowCount(); i++) {
			for (int j = 0; j < params.getColumnCount(); j++) {

				if (getRandom().nextDouble() < params.getInitialDensityField()) {
					if (getRandom().nextDouble() < params.getInitialOnFireField()) {
						grid.set(i, j, 2);
					} else {
						grid.set(i, j, 1);
					}
				} else {
					grid.set(i, j, 0);
				}
			}

		}
		grid.setBoundCondition(0);
		grid.setNeighbType(NeighbType.四邻居);
		this.setGrid(grid);
		this.growth = params.getGrowthField();
		this.growthOnBurned = params.getGrowthOnBurnedField();
		this.onFire = params.getOnFireField();
	}

	@Override
	protected Integer evaluate(int row, int column) {
		Integer state = this.getGrid().get(row, column);
		if (state == 0) {

			if (getRandom().nextDouble() <= growth)
				return 1;
		}
		if (state == 1) {
			if (getRandom().nextDouble() <= onFire)
				return 2;
		}
		if (state == 2) {
			List<GridNeighb<Integer>> pos = this.getGrid().neighbs(row, column);
			for (GridNeighb<Integer> p : pos) {
				if (p.getValue() == 1) {
					setNextState(p.getPos(), 2);
				}
			}
			return 3;
		}
		if (state == 3) {
			if (getRandom().nextDouble() <= growthOnBurned)
				return 1;
		}
		return null;
	}

}
