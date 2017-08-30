package edu.zj.complexityBook.MAS.Schelling;

import java.io.Serializable;

import edu.zj.complexityBook.MAS.Grid.GridWorldDisplay;
import edu.zj.utils.Grid.Model.GridPos;
import javafx.scene.paint.Color;

public class SchellingView extends GridWorldDisplay<SchellingWorld, Integer,SchellingAgent> {

	public SchellingView(SchellingWorld world, SchellingConfig.ViewParams params) {
		super(world.getRowCount(), world.getColumnCount(), params.getCellSize(), params.getBorderColor(), world);
		this.setTitle("SchellingÄ£ÐÍÑÝ±ä");
		this.setRedrawGrid(false);
	}

	@Override
	public void drawAgent(GridPos pos, SchellingAgent ga) {
		getAgentGC().setGlobalAlpha(1);
		if (ga.getState() == SchellingAgent.State.red) {
			getAgentGC().setFill(Color.RED);
			getAgentGC().fillOval(pos.getColumn() * getCellSize() + getCellSize() / 4,
					pos.getRow() * getCellSize() + getCellSize() / 4, getCellSize() / 2, getCellSize() / 2);
		} else {
			getAgentGC().setFill(Color.BLUE);
			getAgentGC().fillOval(pos.getColumn() * getCellSize() + getCellSize() / 4,
					pos.getRow() * getCellSize() + getCellSize() / 4, getCellSize() / 2, getCellSize() / 2);
		}
	}

	@Override
	public Color cellFill(Integer data) {
		return Color.WHITE;
	}
}
