package edu.zj.utils.Grid.View;

import edu.zj.utils.Grid.Model.Grid;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class GridDisplayPane<V> extends GridDisplay<V> {

	public GridDisplayPane(int rowCount, int columnCount, double cellSize, Color borderColor, Grid<V> grid) {
		this(rowCount, columnCount, cellSize, borderColor, grid, 0, 0);
	}

	public GridDisplayPane(int rowCount, int columnCount, double cellSize, Color borderColor, Grid<V> grid, int beginRow,
			int beginColumn) {
		super(rowCount, columnCount, cellSize, borderColor, grid, beginRow, beginColumn);
		setNode(new GridPane());
		Rectangle rectangle;
		for (int i = 0; i < this.getGrid().getRowSize(); i++) {
			for (int j = 0; j < this.getGrid().getColumnSize(); j++) {
				rectangle = new Rectangle(cellSize, cellSize);
				if (borderColor != null) {
					rectangle.setStroke(borderColor);
					rectangle.setStrokeWidth(1);
				}
				((GridPane) getNode()).add(rectangle, j, i);
			}
		}

	}

	public void draw(int row, int column, V value) {
		Rectangle r = getNodeByRowColumnIndex(row, column);
		r.setFill(cellFill(value));
	}

	public abstract Color cellFill(V data);

	public Rectangle getNodeByRowColumnIndex(final int row, final int column) {
		Node result = null;
		ObservableList<Node> childrens = ((GridPane) getNode()).getChildren();

		for (Node node : childrens) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return (Rectangle) result;
	}

}
