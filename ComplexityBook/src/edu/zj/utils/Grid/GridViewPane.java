package edu.zj.utils.Grid;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class GridViewPane<V, G extends Grid<V>> extends GridPane {
	private G grid;
	private int rowCount, columnCount;
	private final Color borderColor;
	private final double cellSize;
	private int beginRow;
	private int beginColumn;
	private ArrayGrid<V> viewPort;

	public GridViewPane(int rowCount, int columnCount, double cellSize, Color borderColor, G grid, int beginRow,
			int beginColumn) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.cellSize = cellSize;
		this.borderColor = borderColor;
		this.grid = grid;
		this.beginRow = beginRow;
		this.beginColumn = beginColumn;
		this.viewPort = new ArrayGrid<>(rowCount, columnCount);
		loadViewPoint();
	}

	private int minRowCount;
	private int minColumnCount;

	private void loadViewPoint() {
		minRowCount = rowCount;
		minColumnCount = columnCount;
		if (grid.getRowSize() != 0) {
			int row = grid.getRowSize() - beginRow;
			if (row < rowCount) {
				minRowCount = row;
				if (minRowCount < 0)
					minRowCount = 0;
			}
		}
		if (grid.getColumnSize() != 0) {
			int column = grid.getColumnSize() - beginColumn;
			if (column < columnCount) {
				minColumnCount = column;
				if (minColumnCount < 0)
					minColumnCount = 0;
			}
		}

		for (int i = 0; i < minRowCount; i++) {
			for (int j = 0; j < minColumnCount; j++) {
				this.viewPort.set(i, j, grid.get(beginRow + i, beginColumn + j));
			}
		}

		Rectangle rectangle;
		for (int i = 0; i < minRowCount; i++) {
			for (int j = 0; j < minColumnCount; j++) {
				rectangle = new Rectangle(cellSize, cellSize);
				if (borderColor != null) {
					rectangle.setStroke(borderColor);
					rectangle.setStrokeWidth(1);
				}
				this.add(rectangle, j, i);
			}
		}

	}

	public GridViewPane(int rowCount, int columnCount, double cellSize, Color borderColor, G grid) {
		this(rowCount, columnCount, cellSize, borderColor, grid, 0, 0);
	}

	public void setBegin(int beginRow, int beginColumn) {
		this.beginRow = beginRow;
		this.beginColumn = beginColumn;
		loadViewPoint();
	}

	public abstract Color defined(V data);

	public void show() {
		for (int i = 0; i < minRowCount; i++) {
			for (int j = 0; j < minColumnCount; j++) {
				draw(i, j, viewPort.get(i, j));
			}
		}
	}

	public void redraw() {
		V value;
		for (int i = 0; i < minRowCount; i++) {
			for (int j = 0; j < minColumnCount; j++) {
				value = grid.get(i + beginRow, j + beginColumn);
				if (!viewPort.get(i, j).equals(value)) {
					System.out.println("Value changed at " + i + "-" + j);
					viewPort.set(i, j, value);
					draw(i, j, value);
				}
			}
		}
	}

	public final int getRowCount() {
		return rowCount;
	}

	public final int getColumnCount() {
		return columnCount;
	}

	public Rectangle getNodeByRowColumnIndex(final int row, final int column) {
		Node result = null;
		ObservableList<Node> childrens = this.getChildren();

		for (Node node : childrens) {
			if (getRowIndex(node) == row && getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return (Rectangle) result;
	}

	public void draw(int row, int column, V value) {
		Rectangle r = getNodeByRowColumnIndex(row, column);
		r.setFill(defined(value));
	}
}
