package edu.zj.complexityBook.CellularAutomata.Wolfram;

import edu.zj.utils.Grid.GridView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//public class WolframView extends CAGridView<State,Integer,WolframData> {
	public class WolframView extends GridView<WolframCell.State,Integer,WolframData> {

	public WolframView(int columnCount, double latticeSize) {
		super(1, columnCount, latticeSize,Color.BLACK);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(WolframCell.State data, int row, int column) {
		if (data== WolframCell.State.ON) {
//			System.out.println("Black");
			Rectangle r = getNodeByRowColumnIndex(row, column);
			r.setFill(Color.BLACK);
		} else {
//			System.out.println("White");
			Rectangle r = getNodeByRowColumnIndex(row, column);
			r.setFill(Color.WHITE);
			
		}
		
		
	}

}
