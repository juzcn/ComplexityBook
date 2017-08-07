package edu.zj.complexityBook.CA.GameOfLife;

import edu.zj.complexityBook.CA.GameOfLife.GolCell.State;
import edu.zj.utils.Grid.Model.MapGrid;
import edu.zj.utils.Grid.View.GridDisplayPane;
import javafx.scene.paint.Color;

public class GolView extends GridDisplayPane<GolCell.State, MapGrid<GolCell.State>> {


	public GolView(int rowCount, int columnCount, double cellSize, Color borderColor, MapGrid<State> grid) {
		super(rowCount, columnCount, cellSize, borderColor, grid);
	}

	@Override
	public Color cellFill(State data) {
		if (data == GolCell.State.alive)
			return Color.BLACK;
		else
			return Color.WHITE;
	}
}
