package edu.zj.complexityBook.CA.ForestFire;

import edu.zj.utils.Grid.View.GridDisplayCanvas;
import javafx.scene.paint.Color;

public class ForestFireView extends GridDisplayCanvas<Integer> {


	public ForestFireView(ForestFireModel model,ForestFireConfig.ViewParams params) {
		super(model.getRowCount(), model.getColumnCount(), params.getCellSize(), params.getBorderColor(), model.getGrid());
		this.setTitle("…≠¡÷ª‘÷—›ªØ");
	}

	@Override
	public Color cellFill(Integer data) {
		if (data==0) return Color.LIGHTBLUE;
		if (data==1) return Color.GREEN;
		if (data==2) return Color.RED;
		if (data==3) return Color.LIGHTYELLOW;
		return null;
//		return Color.gray(1-data/4.0);
	}
}
