package edu.zj.complexityBook.CA.GameOfLife;

import java.util.List;

import edu.zj.complexityBook.CA.CAGrid;
import edu.zj.complexityBook.CA.CAModel;
import edu.zj.utils.Grid.Model.Grid.NeighbType;
import edu.zj.utils.Grid.Model.GridNeighb;

public class GolModel extends CAModel<GolModel> {
	private final CARuleTable ruleTable;

	public GolModel(GolConfig.ModelParams params) {
		super(params.getRowCount(), params.getColumnCount());
		setTitle("…˙√¸”Œœ∑");
		setRandomSeed(params.getRandomSeed());

		if (params.getRuleNumber() == null) {
			ruleTable = CARuleTable.gameOfLife();
			params.setNeighbType(ruleTable.getType());
		} else {
			if (params.getNeighbType() == NeighbType.∞À¡⁄æ”) {
				ruleTable = new CARuleTable(params.getRuleNumber());

			} else {
				ruleTable = new CARuleTable(params.getRuleNumber().intValue());
			}
		}
		CAGrid grid;
		if (params.getFileName() == null || params.getFileName().equals("")) {
			int row, column;
			grid = new CAGrid(params.getRowCount(), params.getColumnCount(),2);
			grid.setAll(0);
			for (int i = 0; i < params.getLiveCellAccount(); i++) {
				while (true) {
					row = getRandom().nextInt(params.getRowCount());
					column = getRandom().nextInt(params.getColumnCount());
					// System.out.println("grid="+grid);
					if (grid.get(row, column) == 0) {
						grid.set(row, column, 1);
						break;
					}
				}
			}
		} else {
			grid = (CAGrid) CAGrid.newGridXML(params.getFileName(),Integer.class);
		}
		grid.setNeighbType(params.getNeighbType());
		this.setGrid(grid);
	}

	public CARuleTable getRuleTable() {
		return ruleTable;
	}

	@Override
	protected Integer evaluate(int row, int column) {
		List<GridNeighb<Integer>> neighbs;
		int numAlives = 0;
		neighbs = getGrid().neighbs(row, column);
		for (GridNeighb<Integer> c:neighbs) {
			if (c.getValue()==1) {
				numAlives++;
			}
		}
		return getRuleTable().byRule(this.getGrid().get(row, column), numAlives);
	}

}
