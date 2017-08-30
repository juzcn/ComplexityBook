package edu.zj.complexityBook.CA.Sandpile;



import edu.zj.utils.Grid.View.GridDisplayCanvas;
import javafx.scene.paint.Color;

public class SandPileView extends GridDisplayCanvas<Integer> {


	public SandPileView(SandpileModel model,SandpileConfig.ViewParams params) {
		super(model.getRowCount(), model.getColumnCount(), params.getCellSize(), params.getBorderColor(), model.getGrid());
		setTitle("SandpileÄ£ÐÍÑÝ»¯");
	}

	@Override
	public Color cellFill(Integer data) {
		if (data>4) data=4;
		if (data==0) return Color.WHITE;
		if (data==1) return Color.BLUE;
		if (data==2) return Color.BLUE.darker();
		if (data==3) return Color.BLUE.darker().darker();
		return Color.RED;
//		return Color.gray(1-data/4.0);
	}
}
