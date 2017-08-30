package edu.zj.complexityBook.CA.Bulb;

import edu.zj.utils.Grid.View.GridDisplayCanvas;
import javafx.scene.paint.Color;

public class BulbView extends GridDisplayCanvas<Bulb> {


	public BulbView(BulbModel model,BulbConfig.ViewParams params) {
		super(model.getRowCount(), model.getColumnCount(), params.getCellSize(), params.getBorderColor(), model.getGrid());
		setTitle("BulbÄ£ÐÍÑÝ»¯");
	}

	@Override
	public Color cellFill(Bulb data) {
		if (data.isState()) return Color.WHITE;
		return Color.BLACK;
	}
	public void redraw() {
		super.show();
	}

}
