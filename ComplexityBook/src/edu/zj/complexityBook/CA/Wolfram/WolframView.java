package edu.zj.complexityBook.CA.Wolfram;

import edu.zj.complexityBook.CA.Wolfram.WolframCell.State;
import edu.zj.utils.Grid.Model.ArrayGrid;
import edu.zj.utils.Grid.View.GridDisplayCanvas;
import javafx.scene.paint.Color;

//public class WolframView extends CAGridView<State,Integer,WolframData> {
	public class WolframView extends GridDisplayCanvas<WolframCell.State,ArrayGrid<WolframCell.State>> {

	public WolframView(int columnCount, double latticeSize,ArrayGrid<WolframCell.State> caGrid) {
		super(1, columnCount, latticeSize,Color.BLACK,caGrid);
	}

	@Override
	public Color cellFill(State data) {
		if (data== WolframCell.State.ON) return Color.BLACK;
		return Color.WHITE;
	}

}
