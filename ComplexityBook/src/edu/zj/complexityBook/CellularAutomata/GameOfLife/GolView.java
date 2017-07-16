package edu.zj.complexityBook.CellularAutomata.GameOfLife;

import java.math.BigInteger;

import edu.zj.utils.Grid.GridView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GolView extends GridView<GolCell.State,BigInteger,GolData> {

	public GolView(int rowCount, int columnCount, double latticeSize) {
		super(rowCount, columnCount, latticeSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(GolCell.State data,int row,int column) {
		if (data== GolCell.State.alive) {
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
