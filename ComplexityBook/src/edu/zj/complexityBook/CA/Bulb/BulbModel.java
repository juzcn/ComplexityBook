package edu.zj.complexityBook.CA.Bulb;

import edu.zj.complexityBook.CA.CAGrid;
import edu.zj.complexityBook.CA.CAModel;
import edu.zj.complexityBook.UI.Model;
import edu.zj.utils.Grid.Model.Grid;

public class BulbModel extends Model {
	private final Grid<Bulb> grid;
	private final boolean nextState[][];

	public BulbModel(BulbConfig.ModelParams params) {
		this.setRandomSeed(params.getRandomSeed());
		setTitle("BulbÄ£ÐÍ");
		grid = new Grid<>(params.getRowCount(), params.getColumnCount());
		// random state for bulb
		for (int i = 0; i < params.getRowCount(); i++) {
			for (int j = 0; j < params.getColumnCount(); j++) {
				grid.set(i, j, new Bulb(getRandom().nextBoolean(), getRandom().nextInt(params.getR())));
			}
		}
		nextState = new boolean[params.getRowCount()][params.getColumnCount()];
		// random connection & random rule
		int row1, column1, row2, column2;
		for (int i = 0; i < params.getRowCount(); i++) {
			for (int j = 0; j < params.getColumnCount(); j++) {
				while (true) {
					row1 = getRandom().nextInt(params.getRowCount());
					column1 = getRandom().nextInt(params.getColumnCount());
					if (i == row1 && j == column1)
						continue;
					break;
				}
				while (true) {
					row2 = getRandom().nextInt(params.getRowCount());
					column2 = getRandom().nextInt(params.getColumnCount());
					if (i == row2 && j == column2)
						continue;
					if (row1 == row2 && column1 == column2)
						continue;
					break;
				}
				grid.get(i, j).setInput1(grid.get(row1, column1));
				grid.get(i, j).setInput2(grid.get(row2, column2));
			}
		}

	}

	@Override
	public void stepRun() {
		step++;
		for (int i = 0; i < grid.getRowSize(); i++) {
			for (int j = 0; j < grid.getColumnSize(); j++) {
				nextState[i][j] = grid.get(i, j).getNextState();
			}
		}
		for (int i = 0; i < grid.getRowSize(); i++) {
			for (int j = 0; j < grid.getColumnSize(); j++) {
				grid.get(i, j).setState(nextState[i][j]);
			}
		}

	}
	public int getRowCount() {
		return grid.getRowSize();
	}
	public int getColumnCount() {
		return grid.getColumnSize();
	}

	public Grid<Bulb> getGrid() {
		return grid;
	}
}
