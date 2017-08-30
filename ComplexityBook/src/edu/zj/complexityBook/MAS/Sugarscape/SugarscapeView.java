package edu.zj.complexityBook.MAS.Sugarscape;

import edu.zj.complexityBook.MAS.Grid.GridWorldDisplay;
import edu.zj.utils.Grid.Model.GridPos;
import javafx.scene.paint.Color;

public class SugarscapeView extends GridWorldDisplay<SugarscapeModel, Integer,SugarscapeAgent>
		{
	public SugarscapeView(SugarscapeModel model, SugarscapeConfig.ViewParams params) {
		super(model.getRowCount(), model.getColumnCount(), params.getCellSize(), params.getBorderColor(), model);
		this.setTitle("SugarscapeœµÕ≥—›ªØ");
	}

	@Override
	public void drawAgent(GridPos pos, SugarscapeAgent ga) {
		getAgentGC().setGlobalAlpha(1);
		getAgentGC().setFill(Color.BLUE);
		getAgentGC().fillOval(pos.getColumn() * getCellSize() + getCellSize() / 4, pos.getRow() * getCellSize() + getCellSize() / 4, getCellSize() / 2, getCellSize() / 2);
	}

	@Override
	public Color cellFill(Integer data) {
		if (data == 0)
			return Color.WHITE;
		if (data == 1)
			return Color.rgb(252, 230, 108);
		if (data == 2)
			return Color.rgb(249, 198, 131);
		if (data == 3)
			return Color.rgb(255, 192, 73);
		return Color.rgb(243, 122, 75);
	}

}
