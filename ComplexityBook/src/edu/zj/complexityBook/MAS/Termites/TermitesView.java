package edu.zj.complexityBook.MAS.Termites;

import edu.zj.utils.Grid.View.GridDisplayCanvas;
import javafx.scene.paint.Color;

public class TermitesView extends GridDisplayCanvas<Integer> {


	public TermitesView(TermitesWorld world,TermitesConfig.ViewParams params) {
		super(world.getRowCount(), world.getColumnCount(), params.getCellSize(), params.getBorderColor(), world.getEnvironment());
		this.setTitle("°×ÒÏÄ£ÐÍÑÝ±ä");
	}

	@Override
	public Color cellFill(Integer data) {
		double gray = data / 10d;
		if (gray > 1.0d)
			gray = 1.0d;
		gray = 1.0d - gray;
		return Color.gray(gray);

	}

}
