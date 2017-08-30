package edu.zj.utils.Grid.View;

import edu.zj.complexityBook.UI.View;
import edu.zj.utils.Grid.Model.Grid;
import javafx.scene.paint.Color;

public abstract class GridDisplay<V> extends View {
	private Grid<V> grid;
	private final Color borderColor;
	private final double cellSize;
	private final Grid<V> viewPort;

	public GridDisplay(int rowCount, int columnCount, double cellSize, Color borderColor, Grid<V> grid, int beginRow,
			int beginColumn) {
		this.cellSize = cellSize;
		this.borderColor = borderColor;
		this.grid = grid;
		this.viewPort = new Grid<>(rowCount, columnCount);
		updateViewPort();

	}

	protected void updateViewPort() {
		for (int i = 0; i < grid.getRowSize(); i++) {
			for (int j = 0; j < grid.getColumnSize(); j++) {
				this.viewPort.set(i, j, this.grid.get(i,  j));
			}
		}

	}

	public GridDisplay(int rowCount, int columnCount, double cellSize, Color borderColor, Grid<V> grid) {
		this(rowCount, columnCount, cellSize, borderColor, grid, 0, 0);
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public double getCellSize() {
		return cellSize;
	}


	protected abstract void draw(int row, int column, V data);

	public void show() {
		for (int i = 0; i < grid.getRowSize(); i++) {
			for (int j = 0; j < grid.getColumnSize(); j++) {
				draw(i, j, viewPort.get(i, j));
			}
		}
	}

	public void redraw() {
		V value;
		for (int i = 0; i < grid.getRowSize(); i++) {
			for (int j = 0; j < grid.getColumnSize(); j++) {
				value = grid.get(i, j);
				if (!viewPort.get(i, j).equals(value)) {
					redraw(i, j, value);
					// System.out.println("Value changed at " + i + "-" + j);
					// viewPort.set(i, j, value);
					// draw(i, j, value);
				}
			}
		}
	}

	public void redraw(int row, int column, V value) {
		viewPort.set(row, column, value);
		draw(row, column, value);

	}

	public Grid<V> getGrid() {
		return grid;
	}
}
