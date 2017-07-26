package edu.zj.complexityBook.CellularAutomata.GameOfLife;

import edu.zj.complexityBook.CellularAutomata.GameOfLife.GolCell.State;
import edu.zj.utils.Grid.GridViewPane;
import edu.zj.utils.Grid.MapGrid;
import javafx.scene.paint.Color;

public class GolView extends GridViewPane<GolCell.State, MapGrid<GolCell.State>> {


	public GolView(int rowCount, int columnCount, double cellSize, Color borderColor, MapGrid<State> grid) {
		super(rowCount, columnCount, cellSize, borderColor, grid);
	}

	@Override
	public Color defined(State data) {
		if (data == GolCell.State.alive)
			return Color.BLACK;
		else
			return Color.WHITE;
	}
}
