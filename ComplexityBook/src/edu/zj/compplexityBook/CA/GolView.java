package edu.zj.compplexityBook.CA;

import java.math.BigInteger;

import edu.zj.compplexityBook.CA.GolData.State;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GolView extends CAGridView<GolData.State,BigInteger,GolData> {

	public GolView(int rowCount, int columnCount, double latticeSize) {
		super(rowCount, columnCount, latticeSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(State data,int row,int column) {
		if (data== State.alive) {
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
