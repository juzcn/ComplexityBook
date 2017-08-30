package edu.zj.utils.Grid.View;

import edu.zj.utils.Grid.Model.Grid;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class GridDisplayCanvas<V> extends GridDisplay<V> {
	public GridDisplayCanvas(int rowCount, int columnCount, double cellSize, Color borderColor, Grid<V> grid) {
		this(rowCount, columnCount, cellSize, borderColor, grid, 0, 0);
	}

	public GridDisplayCanvas(int rowCount, int columnCount, double cellSize, Color borderColor, Grid<V> grid, int beginRow,
			int beginColumn) {
		super(rowCount, columnCount, cellSize, borderColor, grid, beginRow, beginColumn);
		Canvas c=new Canvas(grid.getColumnSize() * cellSize, grid.getRowSize() * cellSize);
		setNode(c);
		gridGC = c.getGraphicsContext2D();
	}

	private  GraphicsContext gridGC;

	public GraphicsContext getGridGC() {
		return gridGC;
	}

	public abstract Color cellFill(V data);

	public void draw(int row, int column, V data) {
		gridGC.setFill(cellFill(data));
		gridGC.fillRect(column * getCellSize(), row * getCellSize(), getCellSize(), getCellSize());
		if (getBorderColor() != null) {
			gridGC.setStroke(getBorderColor());
			gridGC.setLineWidth(1);
			gridGC.strokeRect(column * getCellSize(), row * getCellSize(), getCellSize(), getCellSize());
		}
	}

	public void setGridGC(GraphicsContext gridGC) {
		this.gridGC = gridGC;
	}
}
