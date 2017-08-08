package edu.zj.complexityBook.CA.GameOfLife;

import edu.zj.complexityBook.CA.GameOfLife.GolCell.State;
import edu.zj.utils.Grid.Model.MapGrid;
import edu.zj.utils.Grid.View.GridDisplayPane;
import javafx.scene.paint.Color;

public class GolView extends GridDisplayPane<GolCell.State, MapGrid<GolCell.State>> {


	public GolView(GolModel model,GolConfig.ViewParams params) {
		super(model.getRowCount(), model.getColumnCount(), params.getCellSize(), params.getBorderColor(),model.getCaGrid());
		this.setTitle("生命游戏变化");
	}

	@Override
	public Color cellFill(State data) {
		if (data == GolCell.State.alive)
			return Color.BLACK;
		else
			return Color.WHITE;
	}
}
