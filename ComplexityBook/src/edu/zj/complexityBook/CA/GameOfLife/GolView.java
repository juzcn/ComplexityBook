package edu.zj.complexityBook.CA.GameOfLife;

import edu.zj.utils.Grid.Model.Grid;
import edu.zj.utils.Grid.View.GridDisplayPane;
import javafx.scene.paint.Color;

public class GolView extends GridDisplayPane<Integer> {


	public GolView(GolModel model,GolConfig.ViewParams params) {
		super(model.getRowCount(), model.getColumnCount(), params.getCellSize(), params.getBorderColor(),model.getGrid());
		this.setTitle("生命游戏变化");
	}

	@Override
	public Color cellFill(Integer data) {
		if (data == 1)
			return Color.BLACK;
		else
			return Color.WHITE;
	}
}
