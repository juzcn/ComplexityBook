package edu.zj.complexityBook.CA;

import edu.zj.utils.Grid.View.GridDisplayCanvas;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CADisplay extends GridDisplayCanvas<Integer>{


	public CADisplay(int rowCount, int columnCount, double cellSize, Color borderColor, CAGrid grid) {
		super(rowCount, columnCount, cellSize, borderColor, grid);
		Canvas canvas=(Canvas) getNode();
		canvas.setOnMouseClicked((e) -> {
			int row = (int) (e.getY() / this.getCellSize());
			int column = (int) (e.getX() / this.getCellSize());
			System.out.println("row =" + row + " column = " + column);
//			GridPos pos = new GridPos(row, column);
			CAGrid g=(CAGrid) this.getGrid();
			int c=g.get(row, column)+1;
			if (c==g.getNbStates()) c=0;
			g.set(row, column, c);
			redraw(row,column,c);
		});

	}

	@Override
	public Color cellFill(Integer data) {
		// TODO Auto-generated method stub
		return null;
	}


}
